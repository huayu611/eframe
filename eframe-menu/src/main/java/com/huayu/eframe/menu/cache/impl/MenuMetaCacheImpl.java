package com.huayu.eframe.menu.cache.impl;

import com.huayu.eframe.menu.atom.MenuAtom;
import com.huayu.eframe.menu.bo.Menu;
import com.huayu.eframe.menu.cache.MenuMetaCache;
import com.huayu.eframe.server.cache.frame.AbstractFrameCache;
import com.huayu.eframe.server.cache.frame.DefaultIndex;
import com.huayu.eframe.server.cache.frame.Index;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2019/8/5.
 */
@Service
public class MenuMetaCacheImpl extends AbstractFrameCache<Menu> implements MenuMetaCache
{
    @Autowired
    private MenuAtom menuAtom;

    public MenuMetaCacheImpl()
    {
        registerIndex(new MenuMetaCacheMenuCodeIndex());
        registerIndex(new MenuMetaCacheMenuPathIndex());
        registerIndex(new MenuMetaCacheMenuIdIndex());
        registerIndex(new MenuMetaCacheParentIdIndex());
    }

    static class MenuMetaCacheMenuCodeIndex implements Index<Menu>
    {
        @Override
        public String getIndex(Menu menu)
        {
            return menu.getCode();
        }
    }

    static class MenuMetaCacheMenuIdIndex implements Index<Menu>
    {
        @Override
        public String getIndex(Menu menu)
        {
            return StringUtils.getString(menu.getMenuId());
        }
    }

    static class MenuMetaCacheParentIdIndex implements Index<Menu>
    {
        @Override
        public String getIndex(Menu menu)
        {
            return StringUtils.getString(menu.getParentMenu());
        }
    }

    static class MenuMetaCacheMenuPathIndex implements Index<Menu>
    {
        @Override
        public String getIndex(Menu menu)
        {
            return menu.getMenuPath();
        }
    }

    @Override
    public Menu queryMenuByCode(String code)
    {
        Menu menu = new Menu();
        menu.setCode(code);
        List<Menu> menus = getResultByIndex(MenuMetaCacheMenuCodeIndex.class, menu);
        return CollectionUtils.getFirstElement(menus);
    }

    @Override
    public Menu queryMenuById(Long id)
    {
        Menu menu = new Menu();
        menu.setMenuId(id);
        List<Menu> menus = getResultByIndex(MenuMetaCacheMenuIdIndex.class, menu);
        return CollectionUtils.getFirstElement(menus);
    }

    @Override
    public Menu queryMenuByPath(String path)
    {
        Menu menu = new Menu();
        menu.setMenuPath(path);
        List<Menu> menus = getResultByIndex(MenuMetaCacheMenuPathIndex.class, menu);
        return CollectionUtils.getFirstElement(menus);
    }

    @Override
    public List<Menu> queryMenuByParent(Long id)
    {
        Menu menu = new Menu();
        menu.setParentMenu(id);
        List<Menu> menus = getResultByIndex(MenuMetaCacheParentIdIndex.class, menu);
        return menus;
    }

    @Override
    public List<Menu> queryAllMenu()
    {
        Menu menu = new Menu();
        List<Menu> menus = getResultByIndex(DefaultIndex.class, menu);
        return menus;
    }

    @Override
    public List<Menu> load()
    {
        return menuAtom.queryAllMenu();
    }

    @Override
    public String cacheName()
    {
        return CACHE_NAME;
    }
}
