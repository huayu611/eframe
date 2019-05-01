package com.huayu.eframe.flow.presist.bo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Leo on 2019/2/13.
 */
@Entity
@Table(name = "SYS_LOG")
public class LogEntity
{
    @Id
    @Column(name = "LOG_ID", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @Column(name = "LOG_CODE", length = 255)
    private String logCode;

    @Column(name = "URL", length = 255)
    private String url;

    @Column(name = "METHOD", length = 10)
    private String method;

    @Column(name = "REQUEST", length = 2048)
    private String request;

    @Column(name = "REQUEST_PARAMETER", length = 2048)
    private String requestParameter;

    @Column(name = "REQUEST_IP", length = 64)
    private String requestIp;

    @Column(name = "RESPONSE", length = 2048)
    private String response;

    @Column(name = "IN_TIME")
    private Date inTime;

    @Column(name = "IN_MILLIONSECOND")
    private Long inMillion;

    @Column(name = "OUT_MILLIONSECOND")
    private Long outMillion;

    @Column(name = "OUT_TIME")
    private Date outTime;

    @Column(name = "OPER_OBJ_TYPE", length = 2)
    private String operObjType;

    @Column(name = "OPER_OBJ_ID", length = 20)
    private Long operObjId;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "STATUS", length = 2)
    private String status;

    @Column(name = "ERRORSTACK", length = 2048)
    private String errorStack;

    public Long getLogId()
    {
        return logId;
    }

    public void setLogId(Long logId)
    {
        this.logId = logId;
    }

    public String getLogCode()
    {
        return logCode;
    }

    public void setLogCode(String logCode)
    {
        this.logCode = logCode;
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

    public String getRequest()
    {
        return request;
    }

    public void setRequest(String request)
    {
        this.request = request;
    }

    public String getResponse()
    {
        return response;
    }

    public void setResponse(String response)
    {
        this.response = response;
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

    public Long getOperObjId()
    {
        return operObjId;
    }

    public void setOperObjId(Long operObjId)
    {
        this.operObjId = operObjId;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
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
}
