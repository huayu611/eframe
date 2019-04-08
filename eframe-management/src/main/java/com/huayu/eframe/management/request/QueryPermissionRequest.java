package com.huayu.eframe.management.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.common.restful.PagingRequest;

/**
 * Created by Leo on 2018/10/2.
 */
public class QueryPermissionRequest
{
    @EFrameRequest(length = 512)
    private String code;

    @EFrameRequest(length=64)
    private String name;

    @JsonIgnore
    private String type;

    @EFrameRequest(bean = "_dictionary(permission_method)")
    private String method;

    @EFrameRequest(bean = "_dictionary(permission_white)")
    private String white;

    @EFrameRequest(bean = "_dictionary(permission_white)")
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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public String getWhite()
    {
        return white;
    }

    public void setWhite(String white)
    {
        this.white = white;
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
