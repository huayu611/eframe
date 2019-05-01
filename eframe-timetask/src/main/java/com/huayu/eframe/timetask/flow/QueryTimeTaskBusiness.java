package com.huayu.eframe.timetask.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.RestfulResponse;
import com.huayu.eframe.timetask.entity.service.TimeTaskDetail;
import com.huayu.eframe.timetask.entity.service.TimeTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/4/15.
 */
@Service("com.huayu.eframe.timetask.flow.QueryTimeTaskBusiness")
public class QueryTimeTaskBusiness extends AbstractExecuteBusiness
{

    private final static String RESULT = "QueryTimeTaskBusiness_RESULT";

    private final static String PAGE_OBJECT = "QueryTimeTaskBusiness_PAGE";


    @Autowired
    private TimeTaskService timeTaskService;

    @Override
    public void before(BusinessParameter param)
    {
        QueryTimeTaskRequest request = param.getRequest();
        tidyPage(request.getPage());
    }


    @Override
    public void execute(BusinessParameter param)
    {
        QueryTimeTaskRequest request = param.getRequest();
        TimeTaskDetail timeTaskDetail = buildTimeTaskDetail(request);
        PageObject pageResult = timeTaskService.queryTimeTaskByPage(timeTaskDetail, request.getPage());
        param.addParameter(RESULT, pageResult.getResponse());
        param.addParameter(PAGE_OBJECT, pageResult);
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

    @Override
    protected boolean isNeedTransaction()
    {
        return false;
    }

    private TimeTaskDetail buildTimeTaskDetail(QueryTimeTaskRequest queryTimeTaskRequest)
    {
        TimeTaskDetail timeTaskDetail = new TimeTaskDetail();
        timeTaskDetail.setTimeTaskName(queryTimeTaskRequest.getTimeTaskName());
        return timeTaskDetail;
    }
}
