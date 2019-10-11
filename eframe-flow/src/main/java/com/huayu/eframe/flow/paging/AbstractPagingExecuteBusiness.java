package com.huayu.eframe.flow.paging;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.RestfulResponse;

/**
 * Created by Leo on 2019/10/10.
 */
public abstract class AbstractPagingExecuteBusiness extends AbstractExecuteBusiness
{
    public final static String RESULT = "AbstractPagingExecuteBusiness_RESULT";

    public final static String PAGE_OBJECT = "AbstractPagingExecuteBusiness_PAGE";

    @Override
    public void before(BusinessParameter param)
    {
        Object request = param.getRequest();
        if(request instanceof PagingAdapterRequest)
        {
            PagingAdapterRequest pagingAdapterRequest = (PagingAdapterRequest)request;
            tidyPage(pagingAdapterRequest.getPage());
        }

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
}
