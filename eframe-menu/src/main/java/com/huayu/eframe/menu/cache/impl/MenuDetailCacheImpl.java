package com.huayu.eframe.menu.cache.impl;

import com.huayu.eframe.menu.atom.MenuAtom;
import com.huayu.eframe.menu.bo.Menu;
import com.huayu.eframe.menu.cache.MenuDetailCache;
import com.huayu.eframe.menu.service.MenuDetail;
import com.huayu.eframe.server.cache.frame.AbstractFrameCache;
import com.huayu.eframe.server.cache.frame.DefaultIndex;
import com.huayu.eframe.server.cache.frame.Index;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Leo on 2019/8/21.
 */
@Service
public class MenuDetailCacheImpl extends AbstractFrameCache<MenuDetail> implements MenuDetailCache
{
    private final static Long TOP_MENU = Long.valueOf("0");

    public MenuDetailCacheImpl()
    {
        registerIndex(new MenuDetailCacheCodeIndex());
        registerIndex(new MenuDetailCachePathIndex());
    }

    @Autowired
    private MenuAtom menuAtom;

    static class MenuDetailCacheCodeIndex implements Index<MenuDetail>
    {
        @Override
        public String getIndex(MenuDetail menuDetail)
        {
            return menuDetail.getCode();
        }
    }
    static class MenuDetailCachePathIndex implements Index<MenuDetail>
    {
        @Override
        public String getIndex(MenuDetail menuDetail)
        {
            return menuDetail.getPath();
        }
    }
    @Override
    public MenuDetail queryMenuByCode(String menuCode)
    {

        MenuDetail menuDetail = new MenuDetail();
        menuDetail.setCode(menuCode);
        List<MenuDetail> allWebMenuTree = getResultByIndex(MenuDetailCacheCodeIndex.class,menuDetail);
        return CollectionUtils.getFirstElement(allWebMenuTree);
    }

    @Override
    public MenuDetail queryMenuByPath(String path)
    {
        MenuDetail menuDetail = new MenuDetail();
        menuDetail.setPath(path);
        List<MenuDetail> allWebMenuTree = getResultByIndex(MenuDetailCachePathIndex.class,menuDetail);
        return CollectionUtils.getFirstElement(allWebMenuTree);
    }

    @Override
    public List<MenuDetail> queryAllMenu()
    {
        MenuDetail menuDetail = new MenuDetail();
        List<MenuDetail> allWebMenuTree = getResultByIndex(DefaultIndex.class,menuDetail);
        return allWebMenuTree;
    }

    @Override
    public List<MenuDetail> load()
    {
        List<Menu> allMenu = menuAtom.queryAllMenu();
        Map<Long, List<Menu>> mapObject = keepInMap(allMenu);
        List<MenuDetail> resultTree = putTopElement(mapObject, Long.valueOf(0));
        return resultTree;
    }

    @Override
    public String cacheName()
    {
        return CACHE_NAME;
    }

    protected Map<Long,List<Menu>> keepInMap(List<Menu> menuList)
    {
        Map<Long,List<Menu>> menuMap = new HashMap<>();
        if(CollectionUtils.isEmpty(menuList))
        {
            return menuMap;
        }
        for(Menu menu : menuList)
        {
            List<Menu> checkObjectExist = menuMap.get(menu.getParentMenu());
            if(null == checkObjectExist)
            {
                checkObjectExist = new ArrayList<>();
                menuMap.put(menu.getParentMenu(),checkObjectExist);
            }
            menuMap.get(menu.getParentMenu()).add(menu);
        }
        return menuMap;
    }

    protected List<MenuDetail> putTopElement(Map<Long,List<Menu>> webMenus, Long id)
    {
        List<MenuDetail> newWebMenuTree = new ArrayList<>();
        List<Menu> currentMenu = webMenus.get(id);
        if(CollectionUtils.isEmpty(currentMenu))
        {
            return newWebMenuTree;
        }
        for(Menu webMenu : currentMenu)
        {
            MenuDetail menuDetail = new MenuDetail();
            menuDetail.setStatus(webMenu.getStatus());
            menuDetail.setCode(webMenu.getCode());
            menuDetail.setRange(webMenu.getRange());
            menuDetail.setComponent(webMenu.getComponent());
            menuDetail.setPath(webMenu.getMenuPath());
            menuDetail.setName(webMenu.getMenuName());
            menuDetail.setKey(webMenu.getCode());
            menuDetail.setIcon(webMenu.getIcon());
            menuDetail.setRedirect(webMenu.getRedirect());
            List<MenuDetail> children = putTopElement(webMenus,webMenu.getMenuId());
            CollectionUtils.iterator(children,(c,v,i)->{
                v.setParentMenu(webMenu.getCode());
            });
            menuDetail.setRoutes(children);
            newWebMenuTree.add(menuDetail);

        }
        return newWebMenuTree;
    }
}
