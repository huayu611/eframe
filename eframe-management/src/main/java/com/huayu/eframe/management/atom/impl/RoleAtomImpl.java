package com.huayu.eframe.management.atom.impl;

import com.huayu.eframe.management.atom.RoleAtom;
import com.huayu.eframe.management.bo.Role;
import com.huayu.eframe.management.repository.RoleRepository;
import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.DateUtils;
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
 * Created by Leo on 2018/9/15.
 */
@Service
public class RoleAtomImpl implements RoleAtom
{

    private static final LogDebug debug = new LogDebug(RoleAtomImpl.class);
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles()
    {
        return roleRepository.findAll();
    }

    @Override
    public Role insert(Role role)
    {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role)
    {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Role role)
    {
        roleRepository.delete(role);
    }


    @Override
    public Role queryRoleByID(Long id)
    {
        Optional<Role> roleOptional = roleRepository.findById(id);
        return roleOptional.isPresent() ? roleOptional.get() : null;
    }

    @Override
    public List<Role> queryRoles(Role role,Date now)
    {
        List<Role> roleList = queryRoleByCondition(role,now);
        return roleList;
    }

    @Override
    public List<Role> queryRolesByCode(String roleCode, Date now)
    {
        Role role = new Role();
        role.setRoleCode(roleCode);
        List<Role> roleList = queryRoleByCondition(role,now);
        return roleList;
    }


    @Override
    public Page<Role> queryValidRoleByPage(FramePaging fp, Date now, Role condition)
    {
        debug.log(DateUtils.dateToString(now));
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        PageRequest pageRequest = PageRequest.of(fp.getPage(),fp.getSize(),sort);

        Specification<Role> querySpecific = new Specification<Role>(){

            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
            {
                List<Predicate> predicates = new ArrayList<>();
                if(null != now)
                {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("expireTime").as(Date.class),now));
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("effectiveTime").as(Date.class),now));
                }
                if(null != condition.getRoleCode())
                {
                    predicates.add(criteriaBuilder.like(root.get("roleCode").as(String.class), "%" + condition.getRoleCode() + "%"));
                }
                if(null != condition.getName())
                {
                    String name = condition.getName();
                    name = name.replace(" ","%");
                    predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%"));
                }
                debug.log(predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            };
        };
        Page<Role> resultList = this.roleRepository.findAll(querySpecific, pageRequest);
        debug.log(resultList.getContent().size());
        return resultList;
    }


    private List<Role> queryRoleByCondition(Role condition,Date now)
    {
        Specification<Role> querySpecific = new Specification<Role>(){

            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
            {
                List<Predicate> predicates = new ArrayList<>();
                if(null != now)
                {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("expireTime").as(Date.class),now));
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("effectiveTime").as(Date.class),now));
                }
                if(null != condition.getRoleCode())
                {
                    predicates.add(criteriaBuilder.equal(root.get("roleCode").as(String.class), condition.getRoleCode()));
                }
                if(null != condition.getName())
                {
                    debug.log(condition.getName());
                    predicates.add(criteriaBuilder.equal(root.get("name").as(String.class), condition.getName()));
                }
                predicates.add(criteriaBuilder.notEqual(root.get("status").as(String.class), "D"));
                debug.log(predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            };
        };
        List<Role> resultList = this.roleRepository.findAll(querySpecific);
        debug.log(resultList.size());
        return resultList;
    }
}
