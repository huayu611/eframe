package com.huayu.eframe.server.log.presist.service;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by Leo on 2019/2/13.
 */
public class LogDetail
{
    //为react冗余一个字段
    private String key;

    private String url;

    private String request;

    private String requestParameter;

    private String requestIp;

    private String method;

    private String response;

    private String status;

    private String code;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date outTime;

    private String operObjCode;

    private String operObjType;

    private String errorStack;

    private Long inMillion;

    private Long outMillion;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getRequest()
    {
        return request;
    }

    public void setRequest(String request)
    {
        this.request = request;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public String getResponse()
    {
        return response;
    }

    public void setResponse(String response)
    {
        this.response = response;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
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

    public String getOperObjType()
    {
        return operObjType;
    }

    public void setOperObjType(String operObjType)
    {
        this.operObjType = operObjType;
    }

    public String getOperObjCode()
    {
        return operObjCode;
    }

    public void setOperObjCode(String operObjCode)
    {
        this.operObjCode = operObjCode;
    }

    public String getErrorStack()
    {
        return errorStack;
    }

    public void setErrorStack(String errorStack)
    {
        this.errorStack = errorStack;
    }


    public String getRequestParameter()
    {
        return requestParameter;
    }

    public void setRequestParameter(String requestParameter)
    {
        this.requestParameter = requestParameter;
    }

    public String getRequestIp()
    {
        return requestIp;
    }

    public void setRequestIp(String requestIp)
    {
        this.requestIp = requestIp;
    }

    public Long getInMillion()
    {
        return inMillion;
    }

    public void setInMillion(Long inMillion)
    {
        this.inMillion = inMillion;
    }

    public Long getOutMillion()
    {
        return outMillion;
    }

    public void setOutMillion(Long outMillion)
    {
        this.outMillion = outMillion;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
}
