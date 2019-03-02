package com.huayu.eframe.server.common.restful;

/**
 * Created by Leo on 2018/10/15.
 */
public class PagingRequest
{
    private Integer page;

    private Integer size;

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
