package com.huayu.eframe.management.cache.impl;

import com.huayu.eframe.management.atom.PermissionAtom;
import com.huayu.eframe.management.bo.Permission;
import com.huayu.eframe.management.cache.PermissionCache;
import com.huayu.eframe.server.cache.frame.AbstractFrameCache;
import com.huayu.eframe.server.cache.frame.Index;
import com.huayu.eframe.server.common.standard.StandardFacade;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2018/9/9.
 */
@Service
public class PermissionCacheImpl extends AbstractFrameCache<Permission> implements PermissionCache
{
    @Autowired
    private PermissionAtom permissionAtom;


    private PermissionCacheImpl()
    {
        registerIndex(new PermissionIdIndex());
        registerIndex(new PermissionCodeIndex());
        registerIndex(new PermissionWhiteUrlIndex());
    }

    static class PermissionIdIndex implements Index<Permission>
    {

        @Override
        public String getIndex(Permission permission)
        {
            return StringUtils.getString(permission.getId());
        }
    }

    static class PermissionCodeIndex implements Index<Permission>
    {

        @Override
        public String getIndex(Permission permission)
        {
            return permission.getPermissionCode();
        }
    }

    static class PermissionWhiteUrlIndex implements Index<Permission>
    {

        @Override
        public String getIndex(Permission permission)
        {
            return permission.getWhite() + "&" + permission.getMethod();
        }
    }


    @Override
    public Permission getPermissionById(Long permissionId)
    {
        Permission permission = new Permission();
        permission.setId(permissionId);
        List<Permission> result = getResultByIndex(PermissionIdIndex.class, permission);
        return CollectionUtils.getFirstElement(result);
    }

    @Override
    public List<Permission> getWhitePermission(String method)
    {
        Permission permission = new Permission();
        permission.setWhite("Y");
        permission.setMethod(method);
        List<Permission> result = getResultByIndex(PermissionWhiteUrlIndex.class, permission);
        List<Permission> resultValid = StandardFacade.getValidItem(result);
        return resultValid;

    }

    @Override
    public Permission getPermissionByCode(String code)
    {
        Permission permission = new Permission();
        permission.setPermissionCode(code);
        List<Permission> result = getResultByIndex(PermissionCodeIndex.class, permission);
        List<Permission> resultValid = StandardFacade.getValidItem(result);
        return CollectionUtils.getFirstElement(resultValid);
    }

    @Override
    public List<Permission> load()
    {
        return permissionAtom.getAll();
    }

    @Override
    public String cacheName()
    {
        return "SYS_PERMISSION";
    }
}
