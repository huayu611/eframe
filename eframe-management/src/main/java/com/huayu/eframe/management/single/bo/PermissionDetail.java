package com.huayu.eframe.management.single.bo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Created by Administrator on 2018/8/6.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PermissionDetail
{
    private String url;

    private String name;

    private String code;

    private String type;

    private String typeName;

    private String method;

    private String status;

    private String statusName;

    private String white;

    private String whiteName;

    private Date eff;

    private Date exp;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
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

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getWhite()
    {
        return white;
    }

    public void setWhite(String white)
    {
        this.white = white;
    }

    public Date getEff()
    {
        return eff;
    }

    public void setEff(Date eff)
    {
        this.eff = eff;
    }

    public Date getExp()
    {
        return exp;
    }

    public void setExp(Date exp)
    {
        this.exp = exp;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getWhiteName()
    {
        return whiteName;
    }

    public void setWhiteName(String whiteName)
    {
        this.whiteName = whiteName;
    }

    public String getStatusName()
    {
        return statusName;
    }

    public void setStatusName(String statusName)
    {
        this.statusName = statusName;
    }
}
