package com.huayu.eframe.server.security.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.server.security.service.single.bo.PermissionDetail;

/**
 * Created by Leo on 2019/1/17.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PermissionResponse
{
    private PermissionDetail permission;

    public PermissionDetail getPermission()
    {
        return permission;
    }

    public void setPermission(PermissionDetail permission)
    {
        this.permission = permission;
    }
}
