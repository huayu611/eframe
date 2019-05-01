package com.huayu.eframe.global.system.log.message;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.common.restful.PagingRequest;

import java.util.Date;

/**
 * Created by Leo on 2019/2/18.
 */
public class QueryOperatorLogRequest
{
    private PagingRequest page;

    @EFrameRequest(length = 128)
    private String operatorCode;

    @EFrameRequest(length = 128)
    private String operatorType;

    private Date inTime;

    private Date outTime;

    @EFrameRequest(length = 128)
    private String url;

    @EFrameRequest(bean = "_dictionary(permission_method)")
    private String method;

    public PagingRequest getPage()
    {
        return page;
    }

    public void setPage(PagingRequest page)
    {
        this.page = page;
    }

    public String getOperatorCode()
    {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode)
    {
        this.operatorCode = operatorCode;
    }

    public String getOperatorType()
    {
        return operatorType;
    }

    public void setOperatorType(String operatorType)
    {
        this.operatorType = operatorType;
    }

    public Date getInTime()
    {
        return inTime;
    }

    public void setInTime(Date inTime)
    {
        this.inTime = inTime;
    }

    public Date getOutTime()
    {
        return outTime;
    }

    public void setOutTime(Date outTime)
    {
        this.outTime = outTime;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }
}
