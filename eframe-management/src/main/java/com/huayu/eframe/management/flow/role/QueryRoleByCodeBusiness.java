package com.huayu.eframe.management.flow.role;

import com.huayu.eframe.management.request.QueryRoleRequest;
import com.huayu.eframe.management.response.QueryRoleResponse;
import com.huayu.eframe.management.single.RoleService;
import com.huayu.eframe.management.single.bo.RoleDetail;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2018/10/2.
 */
@Service
public class QueryRoleByCodeBusiness extends AbstractExecuteBusiness
{

    private final static String RESULT = "QueryRoleComplextBusiness_RESULT";

    @Autowired
    private RoleService roleService;


    @Override
    public void before(BusinessParameter param)
    {
        QueryRoleRequest request = param.getRequest();
        if(StringUtils.isNullOrEmpty(request.getCode()))
        {
            throw new IFPException(ErrorCode.ROLE_CODE_CANNOT_NULL,"Role code can not be null or empty!");
        }
    }


    @Override
    public void execute(BusinessParameter param)
    {
        QueryRoleRequest request = param.getRequest();
        RoleDetail result = roleService.queryRoleByCode(request.getCode());
        QueryRoleResponse queryRoleResponse = new QueryRoleResponse();
        queryRoleResponse.setDetail(result);
        param.addParameter(RESULT, queryRoleResponse);
    }

    @Override
    public Object getResult(BusinessParameter param)
    {
        QueryRoleResponse response = param.getParameter(RESULT);
        return buildSuccessRestfulResponse(response);
    }
}