package com.huayu.eframe.global.system.log.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.RestfulResponse;
import com.huayu.eframe.server.log.presist.service.LogDetail;
import com.huayu.eframe.server.log.presist.service.LogService;
import com.huayu.eframe.global.system.log.message.QueryOperatorLogRequest;
import com.huayu.eframe.global.system.log.message.QueryOperatorLogResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2019/2/18.
 */
@Service
public class QueryOperatorLogBusiness extends AbstractExecuteBusiness
{

    private final static String RESULT = "QueryOperatorLogBusiness_RESULT";

    private final static String PAGE_OBJECT = "QueryOperatorLogBusiness_PAGE";

    @Autowired
    private LogService logService;

    @Override
    public void before(BusinessParameter param)
    {
        QueryOperatorLogRequest request = param.getRequest();
        tidyPage(request.getPage());
    }

    @Override
    public void execute(BusinessParameter param)
    {
        QueryOperatorLogRequest request = param.getRequest();
        LogDetail logDetail = buildLogDetail(request);
        PageObject pageObject = logService.queryLogByPage(logDetail,request.getPage());

        QueryOperatorLogResponse response = new QueryOperatorLogResponse();
        response.setDetails((List<LogDetail>)pageObject.getResponse());
        param.addParameter(RESULT, response);
        param.addParameter(PAGE_OBJECT,pageObject);
    }

    private LogDetail buildLogDetail(QueryOperatorLogRequest queryOperatorLogRequest)
    {
        LogDetail logDetail = new LogDetail();
        logDetail.setUrl(queryOperatorLogRequest.getUrl());
        logDetail.setInTime(queryOperatorLogRequest.getInTime());
        logDetail.setOutTime(queryOperatorLogRequest.getOutTime());
        logDetail.setOperObjType(queryOperatorLogRequest.getOperatorType());
        logDetail.setOperObjCode(queryOperatorLogRequest.getOperatorCode());
        return logDetail;
    }


    @Override
    protected Object tidyData(BusinessParameter param)
    {
        QueryOperatorLogResponse response =  param.getParameter(RESULT);
        return response;
    }

    @Override
    protected void tidyResponse(RestfulResponse response, BusinessParameter param)
    {
        PageObject pageObject =  param.getParameter(PAGE_OBJECT);
        tidyPagingResponse(pageObject,response);
    }
}
