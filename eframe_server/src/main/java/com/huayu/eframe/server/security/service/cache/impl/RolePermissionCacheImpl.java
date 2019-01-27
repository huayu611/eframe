package com.huayu.eframe.server.security.service.cache.impl;

import com.huayu.eframe.server.cache.frame.AbstractFrameCache;
import com.huayu.eframe.server.cache.frame.DefaultIndex;
import com.huayu.eframe.server.cache.frame.Index;
import com.huayu.eframe.server.security.service.bo.RolePermission;
import com.huayu.eframe.server.security.service.cache.RolePermissionCache;
import com.huayu.eframe.server.security.service.repository.RolePermissionRepository;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2018/9/18.
 */
@Service
public class RolePermissionCacheImpl extends AbstractFrameCache<RolePermission> implements RolePermissionCache
{
    @Autowired
    private RolePermissionRepository rolePermissionRepository;


    static class RolePermissionRoleIdIndex implements Index<RolePermission>
    {

        @Override
        public String getIndex(RolePermission rolePermission)
        {
            return StringUtils.getString(rolePermission.getRoleId());
        }
    }

    static class RolePermissionPermissionIdIndex implements Index<RolePermission>
    {
        @Override
        public String getIndex(RolePermission rolePermission)
        {
            return StringUtils.getString(rolePermission.getPermissionId());
        }
    }

    static class RolePermissionPermissionAndRoleIdIndex implements Index<RolePermission>
    {
        @Override
        public String getIndex(RolePermission rolePermission)
        {
            return StringUtils.getString(rolePermission.getRoleId()) + "_"+StringUtils.getString(rolePermission.getPermissionId());
        }
    }

    static class RolePermissionIdIndex implements Index<RolePermission>
    {
        @Override
        public String getIndex(RolePermission rolePermission)
        {
            return StringUtils.getString(rolePermission.getId());
        }
    }
    public RolePermissionCacheImpl()
    {
        registerIndex(new RolePermissionRoleIdIndex());
        registerIndex(new RolePermissionPermissionIdIndex());
        registerIndex(new RolePermissionIdIndex());
        registerIndex(new RolePermissionPermissionAndRoleIdIndex());
    }

    @Override
    public List<RolePermission> getAllRolePermission()
    {
        RolePermission rolePermission = new RolePermission();

        List<RolePermission> rolePermissions = getResultByIndex(DefaultIndex.class,rolePermission);
        return rolePermissions;
    }

    @Override
    public List<RolePermission> getRolePermissionByRoleId(Long roleId)
    {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        List<RolePermission> rolePermissions = getResultByIndex(RolePermissionRoleIdIndex.class,rolePermission);
        return rolePermissions;
    }

    @Override
    public List<RolePermission> getRolePermissionByPermissionId(Long permissionId)
    {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermissionId(permissionId);
        List<RolePermission> rolePermissions = getResultByIndex(RolePermissionPermissionIdIndex.class,rolePermission);
        return rolePermissions;
    }


    @Override
    public RolePermission getRolePermissionByRoleAndPermission(Long permissionID,Long roleId)
    {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionID);
        List<RolePermission> rolePermissions = getResultByIndex(RolePermissionPermissionAndRoleIdIndex.class,rolePermission);
        return CollectionUtils.getFirstElement(rolePermissions);
    }

    @Override
    public RolePermission getRolePermissionById(Long id)
    {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setId(id);
        List<RolePermission> rolePermissions = getResultByIndex(RolePermissionIdIndex.class,rolePermission);
        return CollectionUtils.getFirstElement(rolePermissions);
    }

    @Override
    public List<RolePermission> load()
    {
        return rolePermissionRepository.findAll();
    }

    @Override
    public String cacheName()
    {
        return "SYS_ROLE_PERMISSION";
    }
}
