package com.huayu.eframe.global.dict.flow.entity;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.global.dict.entity.service.DictEntityDetail;
import com.huayu.eframe.global.dict.entity.service.DictEntityService;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.RestfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/24.
 */
@Service
public class QueryDictEntityBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "QueryDictEntityBusiness_RESULT";

    private final static String PAGE_OBJECT = "QueryDictEntityBusiness_PAGE";

    @Autowired
    private DictEntityService dictEntityService;

    @Override
    public void before(BusinessParameter param)
    {
        QueryDictEntityRequest request = param.getRequest();
        tidyPage(request.getPagingRequest());
    }

    @Override
    public void execute(BusinessParameter param)
    {
        QueryDictEntityRequest request = param.getRequest();
        DictEntityDetail dictEntityDetail = buildDictEntityDetail(request);
        PageObject result = dictEntityService.queryDictEntity(request.getPagingRequest(),dictEntityDetail);
        param.addParameter(RESULT, result.getResponse());
        param.addParameter(PAGE_OBJECT, result);
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

    private DictEntityDetail buildDictEntityDetail(QueryDictEntityRequest queryDictEntityRequest)
    {
        DictEntityDetail dictEntityDetail = new DictEntityDetail();
        dictEntityDetail.setDictCode(queryDictEntityRequest.getDictCode());
        dictEntityDetail.setDictName(queryDictEntityRequest.getDictName());
        return dictEntityDetail;
    }
}
