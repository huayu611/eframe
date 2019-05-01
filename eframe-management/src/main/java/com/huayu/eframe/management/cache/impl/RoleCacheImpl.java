package com.huayu.eframe.management.cache.impl;

import com.huayu.eframe.management.atom.RoleAtom;
import com.huayu.eframe.management.bo.Role;
import com.huayu.eframe.management.cache.RoleCache;
import com.huayu.eframe.server.cache.frame.AbstractFrameCache;
import com.huayu.eframe.server.cache.frame.DefaultIndex;
import com.huayu.eframe.server.cache.frame.Index;
import com.huayu.eframe.server.common.standard.StandardFacade;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2018/9/15.
 */
@Service
public class RoleCacheImpl extends AbstractFrameCache<Role> implements RoleCache
{
    @Autowired
    private RoleAtom roleAtom;

    @Override
    public List<Role> load()
    {
        return roleAtom.getAllRoles();
    }

    @Override
    public String cacheName()
    {
        return CACHE_NAME;
    }

    static class RoleCodeIndex implements Index<Role>
    {

        @Override
        public String getIndex(Role role)
        {
            return role.getRoleCode();
        }
    }

    static class RoleIdIndex implements Index<Role>
    {

        @Override
        public String getIndex(Role role)
        {
            return StringUtils.getString(role.getId());
        }
    }

    public RoleCacheImpl()
    {
        registerIndex(new RoleCodeIndex());
        registerIndex(new RoleIdIndex());
    }

    @Override
    public Role getRoleByRoleCode(String roleCode)
    {
        Role role = new Role();
        role.setRoleCode(roleCode);
        List<Role> roles = getResultByIndex(RoleCodeIndex.class, role);
        List<Role> resultValid = StandardFacade.getValidItem(roles);
        return CollectionUtils.getFirstElement(resultValid);
    }

    @Override
    public Role getRoleById(Long roleID)
    {
        Role role = new Role();
        role.setId(roleID);
        List<Role> roles = getResultByIndex(RoleIdIndex.class, role);
        return CollectionUtils.getFirstElement(roles);
    }

    @Override
    public List<Role> getAllRole()
    {
        Role role = new Role();
        List<Role> roles = getResultByIndex(DefaultIndex.class, role);
        return roles;
    }
}
