package com.huayu.eframe.management.atom;

import com.huayu.eframe.management.bo.Staff;
import com.huayu.eframe.management.bo.StaffExt;

/**
 * Created by Leo on 2019/4/22.
 */
public interface StaffExtAtom
{
    StaffExt addStaffExt(StaffExt staffExt);

    StaffExt updateStaffExt(StaffExt staffExt);

    StaffExt queryStaffExtByStaff(Staff staff);

    StaffExt queryStaffExtById(Long id);

    void removeStaffExt(StaffExt staffExt);
}
