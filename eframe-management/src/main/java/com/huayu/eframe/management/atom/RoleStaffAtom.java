package com.huayu.eframe.management.atom;

import com.huayu.eframe.management.bo.RoleStaff;

import java.util.List;

/**
 * Created by Leo on 2018/9/16.
 */
public interface RoleStaffAtom
{
    RoleStaff insert(RoleStaff roleStaff);

    RoleStaff update(RoleStaff roleStaff);

    void deleteRoleStaff(RoleStaff roleStaff);

    void delete(List<RoleStaff> roleStaff);

    List<RoleStaff> queryRoleStaff(RoleStaff roleStaff);

    List<RoleStaff> queryRoleStaffByBothId(Long staffId, Long roleId);

    List<RoleStaff> queryRoleStaffByStaffId(Long staffId);
}
