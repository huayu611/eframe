package com.huayu.eframe.server.security.service.single;

import com.huayu.eframe.server.flow.restful.PageObject;
import com.huayu.eframe.server.flow.restful.PagingRequest;
import com.huayu.eframe.server.security.service.bo.Role;
import com.huayu.eframe.server.security.service.request.QueryRoleRequest;
import com.huayu.eframe.server.security.service.single.bo.RoleDetail;
import com.huayu.eframe.server.security.service.request.AddRoleRequest;
import com.huayu.eframe.server.security.service.request.ModifyRoleRequest;
import com.huayu.eframe.server.security.service.response.QueryRoleResponse;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/7/13.
 */
public interface RoleService
{
    RoleDetail addRole(RoleDetail request,String permissions);

    String deleteRole(String roleCode);

    RoleDetail modifyRole(RoleDetail modifyRoleRequest,String permissions);

    RoleDetail queryRoleByCode(String roleCode);

    PageObject queryRoleByCondition(RoleDetail request, PagingRequest pagingRequest);
}
