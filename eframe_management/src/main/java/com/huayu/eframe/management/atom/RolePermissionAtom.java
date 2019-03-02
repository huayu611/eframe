package com.huayu.eframe.management.atom;

import com.huayu.eframe.management.bo.RolePermission;

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
