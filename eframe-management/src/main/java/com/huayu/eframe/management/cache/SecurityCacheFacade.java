package com.huayu.eframe.management.cache;

import com.huayu.eframe.server.context.LocalAttribute;

/**
 * Created by Leo on 2018/12/16.
 */
public class SecurityCacheFacade
{
    public static void refreshByLocalFlow()
    {
        LocalAttribute.addNeedRefreshCache(RoleCache.CACHE_NAME);
        LocalAttribute.addNeedRefreshCache(StaffCache.CACHE_NAME);
        LocalAttribute.addNeedRefreshCache(PermissionCache.CACHE_NAME);
        LocalAttribute.addNeedRefreshCache(RoleStaffCache.CACHE_NAME);
        LocalAttribute.addNeedRefreshCache(RolePermissionCache.CACHE_NAME);
    }
}
