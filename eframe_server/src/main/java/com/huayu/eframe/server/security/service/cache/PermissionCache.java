package com.huayu.eframe.server.security.service.cache;

import com.huayu.eframe.server.security.service.bo.Permission;

import java.util.List;

/**
 * Created by Leo on 2018/9/9.
 */
public interface PermissionCache
{

    String CACHE_NAME = "SYS_PERMISSION";

    Permission getPermissionById(Long permissionId);

    List<Permission> getWhitePermission(String method);

    Permission getPermissionByCode(String code);


}
