package com.huayu.eframe.server.security.service.single;

import com.huayu.eframe.server.flow.restful.PageObject;
import com.huayu.eframe.server.flow.restful.PagingRequest;
import com.huayu.eframe.server.security.service.bo.Permission;
import com.huayu.eframe.server.security.service.request.QueryPermissionRequest;
import com.huayu.eframe.server.security.service.single.bo.PermissionDetail;
import com.huayu.eframe.server.security.service.request.AddPermissionRequest;
import com.huayu.eframe.server.security.service.request.ModifyPermissionRequest;
import com.huayu.eframe.server.security.service.response.QueryPermissionResponse;

import java.util.List;

/**
 * Created by Administrator on 2018/7/13.
 */
public interface PermissionService
{
    @Deprecated
    //重构中需要删除，service层不需要返回原子对象
    Permission getPermissionByCode(String code);

    PermissionDetail addPermission(PermissionDetail addPermissionRequest);

    void deletePermissionByCode(String code);

    PermissionDetail updatePermission(PermissionDetail request);

    PermissionDetail queryPermissionByCode(String roleCode);

    PageObject queryPermissionByPage(PermissionDetail permissionDetail,PagingRequest pagingRequest);

    List<PermissionDetail> queryAllPermission();

}
