package com.huayu.eframe.server.common;

import com.huayu.eframe.server.common.restful.PagingRequest;

/**
 * Created by Leo on 2018/11/24.
 */
public class FramePaging
{
    private Integer page;

    private Integer size;

    public FramePaging()
    {

    }

    public FramePaging(PagingRequest pagingRequest)
    {
        if(null != pagingRequest)
        {
            page = pagingRequest.getPage();
            size = pagingRequest.getSize();
        }
    }

    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

    public Integer getSize()
    {
        return size;
    }

    public void setSize(Integer size)
    {
        this.size = size;
    }
}
