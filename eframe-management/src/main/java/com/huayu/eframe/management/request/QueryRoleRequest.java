package com.huayu.eframe.management.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.common.restful.PagingRequest;

/**
 * Created by Leo on 2018/10/2.
 */
public class QueryRoleRequest
{
    @EFrameRequest(length = 512)
    private String code;

    @EFrameRequest(length = 64)
    private String name;

    @JsonIgnore
    private String parent;

    @EFrameRequest(bean = "_dictionary(role_status)")
    private String status;

    @EFrameRequest(baseMeta = true,required = true)
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
