package com.huayu.eframe.server.security.service.flow.role;

import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.flow.restful.PageObject;
import com.huayu.eframe.server.flow.restful.RestfulResponse;
import com.huayu.eframe.server.security.service.request.QueryRoleRequest;
import com.huayu.eframe.server.security.service.response.QueryRoleListResponse;
import com.huayu.eframe.server.security.service.single.bo.RoleDetail;
import com.huayu.eframe.server.security.service.single.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2018/10/2.
 */
@Service
public class QueryRoleBusiness extends AbstractExecuteBusiness
{

    private final static String RESULT = "QueryRoleBusiness_RESULT";

    private final static String PAGE_OBJECT = "QueryRoleBusiness_PAGE_OBJECT";

    @Autowired
    private RoleService roleService;

    @Override
    public void before(BusinessParameter param)
    {
        QueryRoleRequest request = param.getRequest();
        tidyPage(request.getPage());
    }

    @Override
    public void execute(BusinessParameter param)
    {
        QueryRoleRequest request = param.getRequest();
        RoleDetail roleDetail = buildRoleDetail(request);
        PageObject pageObject = roleService.queryRoleByCondition(roleDetail,request.getPage());

        QueryRoleListResponse response = new QueryRoleListResponse();
        response.setRoles((List<RoleDetail>)pageObject.getResponse());
        param.addParameter(RESULT, response);
        param.addParameter(PAGE_OBJECT,pageObject);
    }


    @Override
    protected Object tidyData(BusinessParameter param)
    {
        QueryRoleListResponse response =  param.getParameter(RESULT);
        return response;
    }

    @Override
    protected void tidyResponse(RestfulResponse response, BusinessParameter param)
    {
        PageObject pageObject =  param.getParameter(PAGE_OBJECT);
        tidyPagingResponse(pageObject,response);
    }

    private RoleDetail buildRoleDetail( QueryRoleRequest request)
    {
        RoleDetail roleDetail = new RoleDetail();
        roleDetail.setCode(request.getCode());
        roleDetail.setName(request.getName());
        roleDetail.setParent(request.getParent());
        roleDetail.setStatus(request.getStatus());
        return roleDetail;
    }
}
