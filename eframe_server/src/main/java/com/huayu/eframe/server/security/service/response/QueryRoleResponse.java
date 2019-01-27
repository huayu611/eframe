package com.huayu.eframe.server.security.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.server.security.service.single.bo.RoleDetail;

/**
 * Created by Leo on 2018/10/2.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QueryRoleResponse
{
    private RoleDetail detail;

    public RoleDetail getDetail()
    {
        return detail;
    }

    public void setDetail(RoleDetail detail)
    {
        this.detail = detail;
    }
}
