package com.huayu.eframe.management.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.management.single.bo.StaffDetail;

import java.util.List;

/**
 * Created by Administrator on 2018/7/22.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QueryStaffResponse
{
    private List<StaffDetail> staffs;

    public List<StaffDetail> getStaffs()
    {
        return staffs;
    }

    public void setStaffs(List<StaffDetail> staffs)
    {
        this.staffs = staffs;
    }
}
