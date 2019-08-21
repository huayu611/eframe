package com.huayu.eframe.menu.atom.impl;

import com.huayu.eframe.menu.atom.MenuAtom;
import com.huayu.eframe.menu.bo.Menu;
import com.huayu.eframe.menu.repository.MenuRepository;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by Leo on 2019/2/11.
 */
@Service
public class MenuAtomImpl implements MenuAtom
{
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Menu addMenu(Menu menu)
    {
        return menuRepository.save(menu);
    }

    @Override
    public Menu updateMenu(Menu menu)
    {
        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> queryMenu(Date now, Integer level)
    {
        Menu menu = new Menu();
        menu.setMenuLevel(level);
        return queryValidPermissionByPage(now, menu);
    }

    @Override
    public List<Menu> queryAllMenu()
    {
        Menu menu = new Menu();
        Specification<Menu> specificationMenu = buildSpecification(menu);
        return menuRepository.findAll(specificationMenu);
    }

    @Override
    public List<Menu> queryMenuByParent(Date now, Long parentMenu)
    {
        Menu menu = new Menu();
        menu.setParentMenu(parentMenu);
        return queryValidPermissionByPage(now, menu);
    }

    @Override
    public Menu queryMenuById(Long id)
    {
        Optional<Menu> menu = menuRepository.findById(id);
        return menu.isPresent() ? menu.get() : null;
    }

    @Override
    public Menu queryMenuByCode(Date now, String code)
    {
        Menu menu = new Menu();
        menu.setCode(code);
        List<Menu> resultMenu = queryValidPermissionByPage(now, menu);
        return CollectionUtils.getFirstElement(resultMenu);
    }

    @Override
    public void deleteMenu(Menu menu)
    {
        menuRepository.delete(menu);
    }

    private List<Menu> queryValidPermissionByPage(Date now, Menu condition)
    {
        Sort sort = new Sort(Sort.Direction.ASC, "range");
        Specification<Menu> querySpecific = buildSpecification(condition);
        List<Menu> resultList = this.menuRepository.findAll(querySpecific, sort);
        return resultList;
    }

    private Specification<Menu> buildSpecification(Menu condition)
    {
        Specification<Menu> querySpecific =  (root, criteriaQuery, criteriaBuilder) ->
        {
            List<Predicate> predicates = new ArrayList<>();
            if (null != condition && null != condition.getMenuName())
            {
                predicates.add(criteriaBuilder.like(root.get("menuName").as(String.class), "%" + condition.getMenuName() + "%"));
            }
            if (null != condition && null != condition.getCode())
            {
                predicates.add(criteriaBuilder.equal(root.get("code").as(String.class), condition.getCode()));
            }
            if (null != condition && null != condition.getMenuLevel())
            {
                predicates.add(criteriaBuilder.equal(root.get("menuLevel").as(Integer.class), condition.getMenuLevel()));
            }
            if (null != condition && null != condition.getParentMenu())
            {
                predicates.add(criteriaBuilder.equal(root.get("parentMenu").as(Long.class), condition.getParentMenu()));
            }
            predicates.add(criteriaBuilder.notEqual(root.get("status").as(String.class), "D"));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return querySpecific;
    }
}
