package com.huayu.eframe.management.cache;

import com.huayu.eframe.management.bo.RolePermission;

import java.util.List;

/**
 * Created by Leo on 2018/9/18.
 */
public interface RolePermissionCache
{

    String CACHE_NAME = "SYS_ROLE_PERMISSION";

    List<RolePermission> getAllRolePermission();

    List<RolePermission> getRolePermissionByRoleId(Long roleId);

    List<RolePermission> getRolePermissionByPermissionId(Long permissionId);

    RolePermission getRolePermissionById(Long id);

    RolePermission getRolePermissionByRoleAndPermission(Long permissionID, Long roleId);
}
