package com.huayu.eframe.server.security.service.cache;

import com.huayu.eframe.server.security.service.bo.Staff;

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
