package com.huayu.eframe.timetask.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.RestfulResponse;
import com.huayu.eframe.timetask.entity.service.TimeTaskInstanceDetail;
import com.huayu.eframe.timetask.entity.service.TimeTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/4/15.
 */
@Service("om.huayu.eframe.timetask.flow.QueryTimeTaskInstanceBusiness")
public class QueryTimeTaskInstanceBusiness
        extends AbstractExecuteBusiness
{

    private final static String RESULT = "QueryTimeTaskInstanceBusiness_RESULT";

    private final static String PAGE_OBJECT = "QueryTimeTaskInstanceBusiness_PAGE";


    @Autowired
    private TimeTaskService timeTaskService;

    @Override
    public void before(BusinessParameter param)
    {
        QueryTimeTaskInstanceRequest request = param.getRequest();
        tidyPage(request.getPage());
    }


    @Override
    public void execute(BusinessParameter param)
    {
        QueryTimeTaskInstanceRequest request = param.getRequest();
        TimeTaskInstanceDetail timeTaskDetail = buildTimeTaskInstanceDetail(request);
        PageObject pageResult = timeTaskService.queryTimeTaskInstanceByPage(timeTaskDetail, request.getPage());
        param.addParameter(RESULT, null != pageResult ? pageResult.getResponse() : null);
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
        if (null == pageObject)
        {
            return;
        }
        tidyPagingResponse(pageObject, response);
    }

    @Override
    protected boolean isNeedTransaction()
    {
        return false;
    }

    private TimeTaskInstanceDetail buildTimeTaskInstanceDetail(QueryTimeTaskInstanceRequest queryTimeTaskRequest)
    {
        TimeTaskInstanceDetail timeTaskInstanceDetail = new TimeTaskInstanceDetail();
        timeTaskInstanceDetail.setTimeTaskCode(queryTimeTaskRequest.getTimeTaskCode());
        return timeTaskInstanceDetail;
    }
}

