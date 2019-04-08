package com.huayu.eframe.global.system.log.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.flow.presist.service.LogDetail;
import com.huayu.eframe.flow.presist.service.LogService;
import com.huayu.eframe.global.system.log.message.QueryOperatorLogRequest;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.RestfulResponse;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        param.addParameter(RESULT, pageObject.getResponse());
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
        if(!StringUtils.equalStringNoCareUpperAndLower("ALL",queryOperatorLogRequest.getMethod()))
        {
            logDetail.setMethod(queryOperatorLogRequest.getMethod());
        }

        return logDetail;
    }


    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }

    @Override
    protected void tidyResponse(RestfulResponse response, BusinessParameter param)
    {
        PageObject pageObject =  param.getParameter(PAGE_OBJECT);
        tidyPagingResponse(pageObject,response);
    }
}
