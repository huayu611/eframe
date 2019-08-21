package com.huayu.eframe.management.atom.impl;

import com.huayu.eframe.management.atom.RoleMenuAtom;
import com.huayu.eframe.management.bo.Role;
import com.huayu.eframe.management.bo.RoleMenu;
import com.huayu.eframe.management.repository.RoleMenuRepository;
import com.huayu.eframe.menu.bo.Menu;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2019/8/5.
 */
@Service
public class RoleMenuAtomImpl implements RoleMenuAtom
{
    @Autowired
    private RoleMenuRepository roleMenuRepository;

    @Override
    public RoleMenu addRoleMenu(RoleMenu roleMenu)
    {
        return roleMenuRepository.save(roleMenu);
    }

    @Override
    public List<RoleMenu> batchAddRoleMenu(List<RoleMenu> roleMenu)
    {
        return roleMenuRepository.saveAll(roleMenu);
    }

    @Override
    public void batchRemoveRoleMenu(List<RoleMenu> roleMenu)
    {
        roleMenuRepository.deleteInBatch(roleMenu);
    }

    @Override
    public RoleMenu removeRoleMenu(RoleMenu roleMenu)
    {
        return roleMenuRepository.save(roleMenu);
    }

    @Override
    public List<RoleMenu> queryRoleMenuByRole(Role role)
    {
        if(null == role)
        {
            return null;
        }
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRole(role);
        Specification<RoleMenu> specification = buildSpecification(roleMenu);
        return roleMenuRepository.findAll(specification);
    }

    @Override
    public List<RoleMenu> queryAllRoleMenu()
    {
        return roleMenuRepository.findAll();
    }

    @Override
    public List<RoleMenu> queryAllRoleMenuInRoles(List<Role> role)
    {

        Specification<RoleMenu> specification = buildSpecification(null,role);
        return roleMenuRepository.findAll(specification);
    }

    private Specification<RoleMenu> buildSpecification(RoleMenu condition)
    {
        return buildSpecification(condition,null);
    }


    private Specification<RoleMenu> buildSpecification(RoleMenu condition,List<Role> roles)
    {
        return (Specification<RoleMenu>) (root, criteriaQuery, criteriaBuilder) ->
        {
            List<Predicate> predicates = new ArrayList<>();
            if(null != condition && null != condition.getRole())
            {
                predicates.add(criteriaBuilder.equal(root.get("role").as(Role.class), condition.getRole() ));
            }
            if(null != condition && null != condition.getMenu())
            {
                predicates.add(criteriaBuilder.equal(root.get("menu").as(Menu.class), condition.getMenu() ));
            }
            if(CollectionUtils.isNotEmpty(roles))
            {
                Path<Object> roleRoot = root.get("role");
                CriteriaBuilder.In<Object> in = criteriaBuilder.in(roleRoot);
                for (Role id:roles){
                    in.value(id);
                }
                Predicate inPredicate = criteriaBuilder.and(in);
                predicates.add(inPredicate);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
