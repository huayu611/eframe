package com.huayu.eframe.server.flow.restful;

/**
 * Created by Leo on 2018/11/24.
 */
public class PageObject
{
    private PagingResponse pagingResponse;

    private Object response;


    public PagingResponse getPagingResponse()
    {
        return pagingResponse;
    }

    public void setPagingResponse(PagingResponse pagingResponse)
    {
        this.pagingResponse = pagingResponse;
    }

    public Object getResponse()
    {
        return response;
    }

    public void setResponse(Object response)
    {
        this.response = response;
    }
}
