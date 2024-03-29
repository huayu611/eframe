package com.huayu.eframe.management.flow.permission;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.management.request.ModifyPermissionRequest;
import com.huayu.eframe.management.single.PermissionService;
import com.huayu.eframe.management.single.bo.PermissionDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2018/10/1.
 */
@Service("com.huayu.eframe.management.flow.permission.ModifyPermissionBusiness")
public class ModifyPermissionBusiness extends AbstractExecuteBusiness
{
    private static final String RESPONSE = "AddPermissionBusiness_Response";

    @Autowired
    private PermissionService permissionService;

    @Override
    public void execute(BusinessParameter param)
    {
        ModifyPermissionRequest request = param.getRequest();
        PermissionDetail detail = buildPermissionDetail(request);
        PermissionDetail permissionDetail = permissionService.updatePermission(detail);
        param.addParameter(RESPONSE, permissionDetail);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        PermissionDetail permissionDetail = param.getParameter(RESPONSE);

        return permissionDetail;
    }

    private PermissionDetail buildPermissionDetail(ModifyPermissionRequest request)
    {
        PermissionDetail permissionDetail = new PermissionDetail();
        permissionDetail.setCode(request.getCode());
        permissionDetail.setExp(request.getExp());
        permissionDetail.setMethod(request.getMethod());
        permissionDetail.setName(request.getName());
        permissionDetail.setStatus(request.getStatus());
        permissionDetail.setType(request.getType());
        permissionDetail.setUrl(request.getUrl());
        permissionDetail.setWhite(request.getWhite());
        return permissionDetail;
    }
}
