package com.huayu.eframe.management.flow.permission;

import com.huayu.eframe.management.request.QueryPermissionRequest;
import com.huayu.eframe.management.response.QueryPermissionListResponse;
import com.huayu.eframe.management.single.PermissionService;
import com.huayu.eframe.management.single.bo.PermissionDetail;
import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.flow.restful.PageObject;
import com.huayu.eframe.server.flow.restful.RestfulResponse;
import com.huayu.eframe.server.log.LogDebug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2018/10/3.
 */
@Service("QueryPermissionBusiness")
public class QueryPermissionBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(QueryPermissionBusiness.class);


    private final static String RESULT = "QueryPermissionBusiness_RESULT";

    private final static String PAGE_OBJECT = "QueryPermissionBusiness_PAGE_OBJECT";


    @Autowired
    private PermissionService permissionService;

    @Override
    public void before(BusinessParameter param)
    {
        QueryPermissionRequest request = param.getRequest();
        tidyPage(request.getPage());
    }

    @Override
    public void execute(BusinessParameter param)
    {
        debug.log("begin");
        QueryPermissionRequest request = param.getRequest();
        PermissionDetail queryPermissionDetail = buildPermissionDetail(request);
        PageObject pageObject = permissionService.queryPermissionByPage(queryPermissionDetail,request.getPage());
        param.addParameter(RESULT, pageObject.getResponse());
        param.addParameter(PAGE_OBJECT,pageObject);
        debug.log("end");
    }

    private PermissionDetail buildPermissionDetail(QueryPermissionRequest request)
    {
        PermissionDetail permissionDetail = new PermissionDetail();
        permissionDetail.setCode(request.getCode());
        permissionDetail.setMethod(request.getMethod());
        permissionDetail.setName(request.getName());
        permissionDetail.setStatus(request.getStatus());
        permissionDetail.setType(request.getType());
        permissionDetail.setWhite(request.getWhite());
        return permissionDetail;
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        QueryPermissionListResponse response = new QueryPermissionListResponse();
        List<PermissionDetail> permissionDetails =  param.getParameter(RESULT);
        response.setPermissions(permissionDetails);
        return response;
    }

    @Override
    protected void tidyResponse(RestfulResponse response, BusinessParameter param)
    {
        PageObject pageObject =  param.getParameter(PAGE_OBJECT);
        tidyPagingResponse(pageObject, response);
    }
}
