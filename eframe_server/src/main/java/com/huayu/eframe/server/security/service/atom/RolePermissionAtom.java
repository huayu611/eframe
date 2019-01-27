package com.huayu.eframe.server.security.service.atom;

import com.huayu.eframe.server.security.service.bo.RolePermission;

import java.util.List;

/**
 * Created by Leo on 2018/9/16.
 */
public interface RolePermissionAtom
{
    RolePermission insert(RolePermission rolePermission);

    RolePermission update(RolePermission rolePermission);

    void delete(RolePermission rolePermission);

    List<RolePermission> queryRolePermission(RolePermission rolePermission);

    void delete(List<RolePermission> rolePermissions);
}
