package com.huayu.eframe.server.security.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.server.security.service.single.bo.PermissionDetail;

import java.util.List;

/**
 * Created by Leo on 2018/10/5.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QueryPermissionListResponse
{
    private List<PermissionDetail> permissions;

    public List<PermissionDetail> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(List<PermissionDetail> permissions)
    {
        this.permissions = permissions;
    }
}
