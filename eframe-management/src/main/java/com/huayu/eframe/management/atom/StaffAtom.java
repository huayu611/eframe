package com.huayu.eframe.management.atom;

import com.huayu.eframe.management.bo.Staff;
import com.huayu.eframe.server.common.FramePaging;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by Leo on 2018/9/16.
 */
public interface StaffAtom
{
    Staff insert(Staff staff);

    Staff update(Staff staff);

    void delete(Staff staff);

    List<Staff> queryStaff(Staff staff);

    List<Staff> queryStaffByLogin(String login, Date now);

    Page<Staff> queryValidStaffByPage(FramePaging fp, Date now, Staff condition);
}
