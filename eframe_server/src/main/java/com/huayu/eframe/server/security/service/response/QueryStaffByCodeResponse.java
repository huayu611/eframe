package com.huayu.eframe.server.security.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.server.security.service.single.bo.StaffDetail;

/**
 * Created by Administrator on 2018/7/22.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QueryStaffByCodeResponse
{
    private StaffDetail detail;

    public StaffDetail getDetail()
    {
        return detail;
    }

    public void setDetail(StaffDetail detail)
    {
        this.detail = detail;
    }
}
