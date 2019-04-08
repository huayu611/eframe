package com.huayu.eframe.management.atom.impl;

import com.huayu.eframe.management.atom.PermissionAtom;
import com.huayu.eframe.management.bo.Permission;
import com.huayu.eframe.management.repository.PermissionRepository;
import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.log.LogDebug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Leo on 2018/9/9.
 */
@Service
public class PermissionAtomImpl implements PermissionAtom
{
    private static final LogDebug debug = new LogDebug(PermissionAtomImpl.class);
    @Autowired
    private PermissionRepository permissionRepository;
    @Override
    public Permission insert(Permission permission)
    {
        return permissionRepository.save(permission);
    }

    @Override
    public List<Permission> getAll()
    {
        return permissionRepository.findAll();
    }

    @Override
    public Permission update(Permission permission)
    {
        return permissionRepository.save(permission);
    }

    @Override
    public void delete(Permission permission)
    {
        permissionRepository.delete(permission);
    }

    @Override
    public Permission getPermissionByID(Long id)
    {
        Optional<Permission> permsissionOptional = permissionRepository.findById(id);
        return permsissionOptional.isPresent() ? permsissionOptional.get() : null;
    }

    @Override
    public List<Permission> getPermissionByCode(String code,Date now)
    {
        Permission condition = new Permission();
        condition.setPermissionCode(code);
        return queryValidPermissionByCondition(condition,now);
    }

    @Override
    public List<Permission> queryPermission(Permission permission,Date now)
    {

        List<Permission> permissionList = queryValidPermissionByCondition(permission,now);
        return permissionList;
    }

    @Override
    public Page<Permission> queryValidPermissionByPage(FramePaging fp, Date now, Permission condition)
    {

        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        PageRequest pageRequest = PageRequest.of(fp.getPage(),fp.getSize(),sort);

        Specification<Permission> querySpecific = new Specification<Permission>(){

            @Override
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
            {
                List<Predicate> predicates = new ArrayList<>();
                if(null != now)
                {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("expireTime").as(Date.class),now));
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("effectiveTime").as(Date.class),now));
                }
                if(null != condition.getPermissionName())
                {
                    String permissionName = condition.getPermissionName();
                    permissionName = permissionName.replace(" ","%");
                    predicates.add(criteriaBuilder.like(root.get("permissionName").as(String.class), "%" +permissionName + "%"));
                }
                if(null != condition.getPermissionCode())
                {
                    predicates.add(criteriaBuilder.like(root.get("permissionCode").as(String.class), "%" + condition.getPermissionCode() + "%"));
                }
                predicates.add(criteriaBuilder.notEqual(root.get("status").as(String.class),"D"));
                debug.log(predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            };
        };
        Page<Permission> resultList = this.permissionRepository.findAll(querySpecific, pageRequest);
        debug.log(resultList.getContent().size());
        return resultList;
    }


    private List<Permission> queryValidPermissionByCondition(Permission condition,Date now)
    {
        Specification<Permission> querySpecific = new Specification<Permission>(){

            @Override
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
            {
                List<Predicate> predicates = new ArrayList<>();
                if(null != now)
                {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("expireTime").as(Date.class),now));
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("effectiveTime").as(Date.class),now));
                }
                if(null != condition.getPermissionCode())
                {
                    predicates.add(criteriaBuilder.equal(root.get("permissionCode").as(String.class), condition.getPermissionCode()));
                }
                predicates.add(criteriaBuilder.notEqual(root.get("status").as(String.class),"D"));
                debug.log(predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            };
        };
        List<Permission> resultList = this.permissionRepository.findAll(querySpecific);
        debug.log(resultList.size());
        return resultList;
    }
}
