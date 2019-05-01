package com.huayu.eframe.management.flow.role;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.management.request.QueryRoleRequest;
import com.huayu.eframe.management.single.RoleService;
import com.huayu.eframe.management.single.bo.RoleDetail;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.RestfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        PageObject pageObject = roleService.queryRoleByCondition(roleDetail, request.getPage());


        param.addParameter(RESULT, pageObject.getResponse());
        param.addParameter(PAGE_OBJECT, pageObject);
    }


    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }

    @Override
    protected void tidyResponse(RestfulResponse response, BusinessParameter param)
    {
        PageObject pageObject = param.getParameter(PAGE_OBJECT);
        tidyPagingResponse(pageObject, response);
    }

    private RoleDetail buildRoleDetail(QueryRoleRequest request)
    {
        RoleDetail roleDetail = new RoleDetail();
        roleDetail.setCode(request.getCode());
        roleDetail.setName(request.getName());
        roleDetail.setParent(request.getParent());
        roleDetail.setStatus(request.getStatus());
        return roleDetail;
    }
}
