package com.huayu.eframe.server.security.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.server.security.service.single.bo.PermissionDetail;

/**
 * Created by Leo on 2018/10/2.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QueryPermissionResponse
{
    private PermissionDetail detail;


    public PermissionDetail getDetail()
    {
        return detail;
    }

    public void setDetail(PermissionDetail detail)
    {
        this.detail = detail;
    }
}