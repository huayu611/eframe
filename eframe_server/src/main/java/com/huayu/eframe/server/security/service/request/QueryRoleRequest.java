package com.huayu.eframe.server.security.service.request;

import com.huayu.eframe.server.flow.restful.PagingRequest;

/**
 * Created by Leo on 2018/10/2.
 */
public class QueryRoleRequest
{
    private String code;

    private String name;

    private String parent;

    private String status;

    private PagingRequest page;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getParent()
    {
        return parent;
    }

    public void setParent(String parent)
    {
        this.parent = parent;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public PagingRequest getPage()
    {
        return page;
    }

    public void setPage(PagingRequest page)
    {
        this.page = page;
    }
}
