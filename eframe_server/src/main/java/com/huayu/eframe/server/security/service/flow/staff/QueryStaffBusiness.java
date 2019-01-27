package com.huayu.eframe.server.security.service.flow.staff;

import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.flow.restful.PageObject;
import com.huayu.eframe.server.flow.restful.PagingRequest;
import com.huayu.eframe.server.flow.restful.RestfulResponse;
import com.huayu.eframe.server.security.service.request.QueryStaffRequest;
import com.huayu.eframe.server.security.service.response.QueryStaffResponse;
import com.huayu.eframe.server.security.service.single.bo.StaffDetail;
import com.huayu.eframe.server.security.service.single.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2018/10/2.
 */
@Service
public class QueryStaffBusiness extends AbstractExecuteBusiness
{

    private final static String RESULT = "QueryStaffBusiness_RESULT";

    private final static String PAGE_OBJECT = "QueryStaffBusiness_PAGE_OBJECT";

    @Autowired
    private StaffService staffService;

    @Override
    public void before(BusinessParameter param)
    {
        QueryStaffRequest request = param.getRequest();
        tidyPage(request.getPage());
    }

    @Override
    public void execute(BusinessParameter param)
    {
        QueryStaffRequest request = param.getRequest();
        PagingRequest page = request.getPage();

        StaffDetail staffDetail = buildStaffDetail(request);
        PageObject pageObject = staffService.queryStaffListByPage(staffDetail,page);

        QueryStaffResponse response = new QueryStaffResponse();
        response.setStaffs((List<StaffDetail>)pageObject.getResponse());
        param.addParameter(RESULT, response);
        param.addParameter(PAGE_OBJECT,pageObject);
    }


    @Override
    protected Object tidyData(BusinessParameter param)
    {
        QueryStaffResponse response =  param.getParameter(RESULT);
        return response;
    }

    @Override
    protected void tidyResponse(RestfulResponse response, BusinessParameter param)
    {
        PageObject pageObject =  param.getParameter(PAGE_OBJECT);
        tidyPagingResponse(pageObject, response);
    }

    private StaffDetail buildStaffDetail(QueryStaffRequest request)
    {
        StaffDetail detail = new StaffDetail();
        detail.setName(request.getName());
        detail.setLogin(request.getLogin());
        return detail;
    }
}
