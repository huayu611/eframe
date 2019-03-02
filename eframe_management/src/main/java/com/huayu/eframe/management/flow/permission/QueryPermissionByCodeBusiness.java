package com.huayu.eframe.management.flow.permission;

import com.huayu.eframe.management.request.QueryPermissionRequest;
import com.huayu.eframe.management.response.QueryPermissionResponse;
import com.huayu.eframe.management.single.PermissionService;
import com.huayu.eframe.management.single.bo.PermissionDetail;
import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2018/10/2.
 */
@Service
public class QueryPermissionByCodeBusiness  extends AbstractExecuteBusiness
{
    private final static String RESULT = "QueryPermissionComplextBusiness_RESULT";

    @Autowired
    private PermissionService permissionService;


    @Override
    public void before(BusinessParameter param)
    {
        QueryPermissionRequest request = param.getRequest();
        if(StringUtils.isNullOrEmpty(request.getCode()))
        {
            throw new IFPException(ErrorCode.PERMISSION_CODE_CANNOT_NULL,"Role code can not be null or empty!");
        }
    }
    @Override
    public void execute(BusinessParameter param)
    {
        QueryPermissionRequest request = param.getRequest();
        PermissionDetail result = permissionService.queryPermissionByCode(request.getCode());
        param.addParameter(RESULT, result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        QueryPermissionResponse response = new QueryPermissionResponse();
        PermissionDetail permissionDetail = param.getParameter(RESULT);
        response.setDetail(permissionDetail);
        return response;
    }
}
