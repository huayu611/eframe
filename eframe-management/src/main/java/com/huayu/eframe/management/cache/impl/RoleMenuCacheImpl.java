package com.huayu.eframe.management.cache.impl;

import com.huayu.eframe.management.atom.RoleMenuAtom;
import com.huayu.eframe.management.bo.Role;
import com.huayu.eframe.management.bo.RoleMenu;
import com.huayu.eframe.management.cache.RoleMenuCache;
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
public class RoleMenuCacheImpl extends AbstractFrameCache<RoleMenu> implements RoleMenuCache
{
    @Autowired
    private RoleMenuAtom roleMenuAtom;

    static class RoleMenuCacheRoleId implements Index<RoleMenu>
    {
        @Override
        public String getIndex(RoleMenu roleMenu)
        {
            return StringUtils.getString(roleMenu.getRole().getId());
        }
    }

    @Override
    public List<RoleMenu> queryRoleMenuByRole(Role role)
    {
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRole(role);
        List<RoleMenu> roleMenus = getResultByIndex(RoleMenuCacheRoleId.class, roleMenu);
        return roleMenus;
    }

    @Override
    public List<RoleMenu> queryAllRoleMenu()
    {
        RoleMenu roleMenu = new RoleMenu();
        List<RoleMenu> roleMenus = getResultByIndex(DefaultIndex.class, roleMenu);
        return roleMenus;
    }

    @Override
    public List<RoleMenu> load()
    {
        return roleMenuAtom.queryAllRoleMenu();
    }

    @Override
    public String cacheName()
    {
        return CACHE_NAME;
    }
}
