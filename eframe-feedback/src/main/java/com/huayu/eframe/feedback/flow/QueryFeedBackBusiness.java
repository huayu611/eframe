package com.huayu.eframe.feedback.flow;

import com.huayu.eframe.feedback.requset.QueryFeedBackRequest;
import com.huayu.eframe.feedback.service.FeedBackService;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.PagingRequest;
import com.huayu.eframe.server.common.restful.RestfulResponse;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Leo on 2018/11/24.
 */
@Service
public class QueryFeedBackBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(QueryFeedBackBusiness.class);


    private final static String PAGING_OBJECT = "PAGING_OBJECT";

    private final static String NULL_VALUE = "null";

    private final static String YYYYMMDD_FORMAT = "yyyy-MM-dd";

    @Override
    public void before(BusinessParameter param)
    {
        QueryFeedBackRequest queryFeedBackRequest =  param.getRequest();
        PagingRequest pagingRequest = queryFeedBackRequest.getPagingRequest();
        Integer page = pagingRequest.getPage();
        Integer size = pagingRequest.getSize();
        if(null == page || page.intValue() <1)
        {
            pagingRequest.setPage(1);
        }
        if(null == size || size.intValue() <1)
        {
            pagingRequest.setSize(1);
        }
        pagingRequest.setPage(pagingRequest.getPage()-1);
    }

    @Autowired
    private FeedBackService feedBackService;

    @Override
    public void execute(BusinessParameter param)
    {
        QueryFeedBackRequest queryFeedBackRequest =  param.getRequest();

        String dateDuration = queryFeedBackRequest.getDateDuration();
        String content = queryFeedBackRequest.getContent();
        if(StringUtils.isAllNotExist(dateDuration,content))
        {
            PageObject pageObject = feedBackService.queryFeedBackByPage(queryFeedBackRequest.getPagingRequest());
            param.addParameter(PAGING_OBJECT,pageObject);
            return;
        }


        String[] dateDurationSplit = dateDuration.split("_");
        Date start = null;
        Date end = null;
        if(dateDurationSplit.length>0)
        {
            if(!StringUtils.equalStringNoCareUpperAndLower(NULL_VALUE,dateDurationSplit[0]))
            {
                start = DateUtils.stringToDate(dateDurationSplit[0], Constants.DEFAULT_FORMAT_DATE_YMD);
            }
        }
        if(dateDurationSplit.length>1)
        {
            if(!StringUtils.equalStringNoCareUpperAndLower(NULL_VALUE,dateDurationSplit[1]))
            {
                end = DateUtils.stringToDate(dateDurationSplit[1], Constants.DEFAULT_FORMAT_DATE_YMD);
            }
        }
        content = StringUtils.getString(content);

        PageObject pageObject = feedBackService.queryFeedBackByPageAndCondition(queryFeedBackRequest.getPagingRequest(),start,end,content);
        param.addParameter(PAGING_OBJECT,pageObject);
    }


    @Override
    protected Object tidyData(BusinessParameter param)
    {
        PageObject pageObject =  param.getParameter(PAGING_OBJECT);
        return null == pageObject ? null : pageObject.getResponse();
    }

    @Override
    protected void tidyResponse(RestfulResponse response, BusinessParameter param)
    {
        PageObject pageObject =  param.getParameter(PAGING_OBJECT);
        tidyPagingResponse(pageObject,response);
    }
}
