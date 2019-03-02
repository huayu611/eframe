package com.huayu.eframe.management.cache;

import com.huayu.eframe.management.bo.RoleStaff;

import java.util.List;

/**
 * Created by Leo on 2018/9/18.
 */
public interface RoleStaffCache
{
String CACHE_NAME = "SYS_ROLE_STAFF";

    List<RoleStaff> getAllRoleStaff();

    List<RoleStaff> getRoleStaffByRoleId(Long roleId);

    List<RoleStaff> getRoleStaffByStaffId(Long staffId);

}
