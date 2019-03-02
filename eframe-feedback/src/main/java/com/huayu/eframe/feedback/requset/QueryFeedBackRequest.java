package com.huayu.eframe.feedback.requset;

import com.huayu.eframe.server.common.restful.PagingRequest;

/**
 * Created by Leo on 2018/11/30.
 */
public class QueryFeedBackRequest
{
    private PagingRequest pagingRequest;

    private String dateDuration;

private String content;

    public PagingRequest getPagingRequest()
    {
        return pagingRequest;
    }

    public void setPagingRequest(PagingRequest pagingRequest)
    {
        this.pagingRequest = pagingRequest;
    }

    public String getDateDuration()
    {
        return dateDuration;
    }

    public void setDateDuration(String dateDuration)
    {
        this.dateDuration = dateDuration;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}
