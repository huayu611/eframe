package com.huayu.eframe.global.email.presist.flow.query;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.global.email.presist.service.EmailDetail;
import com.huayu.eframe.global.email.presist.service.EmailService;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.RestfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/3/10.
 */
@Service
public class QueryEmailBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "QueryEmailBusiness_RESULT";

    private final static String PAGE_OBJECT = "QueryEmailBusiness_PAGE";


    @Autowired
    private EmailService emailService;

    @Override
    public void before(BusinessParameter param)
    {
        QueryEmailRequest request = param.getRequest();
        tidyPage(request.getPage());
    }


    @Override
    public void execute(BusinessParameter param)
    {
        QueryEmailRequest request = param.getRequest();
        EmailDetail emailDetail = buildEmailDetail(request);
        PageObject pageResult = emailService.queryEmailByPage(request.getPage(),emailDetail);
        param.addParameter(RESULT, pageResult.getResponse());
        param.addParameter(PAGE_OBJECT,pageResult);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return   param.getParameter(RESULT);
    }

    @Override
    protected void tidyResponse(RestfulResponse response, BusinessParameter param)
    {
        PageObject pageObject =  param.getParameter(PAGE_OBJECT);
        tidyPagingResponse(pageObject,response);
    }

    private EmailDetail buildEmailDetail(QueryEmailRequest queryEmailRequest)
    {
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setEmailCode(queryEmailRequest.getEmailCode());
        emailDetail.setName(queryEmailRequest.getName());
        return emailDetail;
    }
}
