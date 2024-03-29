package com.huayu.eframe.management.cache;

import com.huayu.eframe.management.bo.Staff;

import java.util.List;

/**
 * Created by Leo on 2018/9/16.
 */
public interface StaffCache
{
    String CACHE_NAME = "SYS_STAFF";

    Staff getStaffByLoginName(String loginName);

    Staff getStaffById(Long id);

    List<Staff> getAllStaff();
}
