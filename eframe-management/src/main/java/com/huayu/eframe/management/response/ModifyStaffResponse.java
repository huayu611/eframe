package com.huayu.eframe.management.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.management.single.bo.StaffDetail;

/**
 * Created by Leo on 2019/1/16.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ModifyStaffResponse
{
    private StaffDetail staff;

    public StaffDetail getStaff()
    {
        return staff;
    }

    public void setStaff(StaffDetail staff)
    {
        this.staff = staff;
    }
}
