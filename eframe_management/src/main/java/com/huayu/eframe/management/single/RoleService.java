package com.huayu.eframe.management.single;

import com.huayu.eframe.management.single.bo.RoleDetail;
import com.huayu.eframe.server.flow.restful.PageObject;
import com.huayu.eframe.server.flow.restful.PagingRequest;

/**
 * Created by Administrator on 2018/7/13.
 */
public interface RoleService
{
    RoleDetail addRole(RoleDetail request, String permissions);

    String deleteRole(String roleCode);

    RoleDetail modifyRole(RoleDetail modifyRoleRequest, String permissions);

    RoleDetail queryRoleByCode(String roleCode);

    PageObject queryRoleByCondition(RoleDetail request, PagingRequest pagingRequest);
}
