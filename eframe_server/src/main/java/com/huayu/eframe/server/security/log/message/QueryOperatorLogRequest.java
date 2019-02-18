package com.huayu.eframe.server.security.log.message;

import com.huayu.eframe.server.flow.restful.PagingRequest;

import java.util.Date;

/**
 * Created by Leo on 2019/2/18.
 */
public class QueryOperatorLogRequest
{
    private PagingRequest page;

    private String operatorCode;

    private String operatorType;

    private Date inTime;

    private Date outTime;

    private String url;

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
}
