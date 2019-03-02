package com.huayu.eframe.management.flow.permission;

import com.huayu.eframe.management.request.AddPermissionRequest;
import com.huayu.eframe.management.response.PermissionResponse;
import com.huayu.eframe.management.single.PermissionService;
import com.huayu.eframe.management.single.bo.PermissionDetail;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.log.LogDebug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/8/4.
 */
@Service("AddPermissionBusiness")
public class AddPermissionBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(AddPermissionBusiness.class);

    private static final String RESPONSE = "AddPermissionBusiness_Response";
    @Autowired
    private PermissionService permissionService;

    @Override
    public void execute(BusinessParameter param)
    {
        AddPermissionRequest request = param.getRequest();
        PermissionDetail detail = buildPermissionDetail(request);
        PermissionDetail responseDetail = permissionService.addPermission(detail);
        param.addParameter(RESPONSE,responseDetail);
        debug.log("AddPermissionBusiness finish");
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        PermissionDetail permissionDetail = param.getParameter(RESPONSE);
        PermissionResponse permissionResponse = new PermissionResponse();
        permissionResponse.setPermission(permissionDetail);
        return permissionResponse;
    }

    private PermissionDetail buildPermissionDetail(AddPermissionRequest request)
    {
        PermissionDetail permissionDetail = new PermissionDetail();
        permissionDetail.setCode(request.getCode());
        permissionDetail.setEff(request.getEff());
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
