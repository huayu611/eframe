package com.huayu.eframe.server.security.menu.service.impl;

import com.huayu.eframe.server.security.menu.bo.Menu;
import com.huayu.eframe.server.security.menu.bo.MenuTree;
import com.huayu.eframe.server.security.menu.repository.MenuRepository;
import com.huayu.eframe.server.security.menu.service.MenuService;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by Administrator on 2018/8/12.
 */

@Service
public class MenuServiceImpl implements MenuService
{
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> queryAllMenu()
    {
        List<Menu> allMenu = menuRepository.findAll();
        return allMenu;
    }

    @Override
    public MenuTree getMenuTree(String menuCode)
    {
        Menu conditionMenu = null;
        if(StringUtils.isEmpty(menuCode))
        {
            conditionMenu = queryMenuByCode(menuCode);
        }
        List<Menu> allMenu = queryAllMenu();
        if (CollectionUtils.isEmpty(allMenu))
        {
            return null;
        }

        Map<Long, List<Menu>> sonMenuMap = new HashMap<>();
        for (Menu menu : allMenu)
        {
            //parent menu
            List<Menu> parentMenu = sonMenuMap.get(menu.getParentMenu());
            if (null == parentMenu)
            {
                sonMenuMap.put(menu.getParentMenu(), new ArrayList<>());
            }
            sonMenuMap.get(menu.getParentMenu()).add(menu);

        }

        return mergeTree(conditionMenu,sonMenuMap);
    }

    private MenuTree mergeTree(Menu rootMenu , Map<Long, List<Menu>> sonMenuMap)
    {

        MenuTree menuTree = new MenuTree();
        if(null == rootMenu)
        {
            menuTree.setMenuLevel(0);
            menuTree.setSonMenu(createSonMenu(sonMenuMap, 0L));
            menuTree.setHasSonMenu(CollectionUtils.isNotEmpty(menuTree.getSonMenu()));
        }
        else
        {
            menuTree.setMenu(rootMenu);
            menuTree.setMenuLevel(rootMenu.getMenuLevel());
            menuTree.setSonMenu(createSonMenu(sonMenuMap, rootMenu.getMenuId()));
            menuTree.setHasSonMenu(CollectionUtils.isNotEmpty(menuTree.getSonMenu()));
        }
        return menuTree;
    }
    private List<MenuTree> createSonMenu(Map<Long, List<Menu>> sonMenuMap,Long parentId)
    {
        List<MenuTree> menuTreeList = new ArrayList<>();
        List<Menu> sonMenu = sonMenuMap.get(parentId);
        if(CollectionUtils.isEmpty(sonMenu))
        {
            return null;
        }
        for(Menu sonMenuSingle :sonMenu )
        {
            MenuTree mt = new MenuTree();
            mt.setMenuLevel(sonMenuSingle.getMenuLevel());
            mt.setMenu(sonMenuSingle);
            mt.setSonMenu(createSonMenu(sonMenuMap, sonMenuSingle.getMenuId()));
            mt.setHasSonMenu(CollectionUtils.isNotEmpty(mt.getSonMenu()));
            menuTreeList.add(mt);
        }
        return menuTreeList;
    }
    
    @Override
    public Long addMenu(Menu menu, Date now)
    {
        now = null == now ? DateUtils.getCurrentDate() : now;
        menu.setCreateTime(now);
        menu.setEffectiveTime(null == menu.getEffectiveTime()?now : menu.getEffectiveTime());
        menu.setLastUpdateTime(now);
        menu.setExpireTime(null == menu.getExpireTime()?DateUtils.getDefaultExpireDate() : menu.getExpireTime());
        Menu menuPresist = menuRepository.save(menu);
        return menuPresist.getMenuId();
    }

    public Menu queryMenuByCode(String menuCode)
    {
        Menu menu = new Menu();
        menu.setCode(menuCode);


        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<Menu> example = Example.of(menu ,matcher);

        List<Menu> staffRoleReturn = menuRepository.findAll(example);
        return CollectionUtils.getFirstElement(staffRoleReturn);
    }
}
