package com.huayu.eframe.management.single;

import com.huayu.eframe.management.bo.Permission;
import com.huayu.eframe.management.single.bo.PermissionDetail;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.PagingRequest;

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

    String deletePermissionByCode(String code);

    PermissionDetail updatePermission(PermissionDetail request);

    PermissionDetail queryPermissionByCode(String roleCode);

    PageObject queryPermissionByPage(PermissionDetail permissionDetail, PagingRequest pagingRequest);

    List<PermissionDetail> queryAllPermission();

}
