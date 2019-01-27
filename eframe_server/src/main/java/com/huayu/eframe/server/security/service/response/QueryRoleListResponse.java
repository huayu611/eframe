package com.huayu.eframe.server.security.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.server.security.service.single.bo.RoleDetail;

import java.util.List;

/**
 * Created by Leo on 2018/10/5.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QueryRoleListResponse
{
    private List<RoleDetail> roles;

    public List<RoleDetail> getRoles()
    {
        return roles;
    }

    public void setRoles(List<RoleDetail> roles)
    {
        this.roles = roles;
    }
}
