package com.huayu.eframe.server.security.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.server.security.service.single.bo.RoleDetail;

/**
 * Created by Leo on 2019/1/17.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RoleResponse
{
    private RoleDetail role;

    public RoleDetail getRole()
    {
        return role;
    }

    public void setRole(RoleDetail role)
    {
        this.role = role;
    }
}
