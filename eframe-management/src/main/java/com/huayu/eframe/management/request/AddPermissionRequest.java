package com.huayu.eframe.management.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huayu.eframe.management.constant.SecurityConstant;
import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.common.restful.EffectiveExpireDateTime;

import java.util.Date;

/**
 * Created by Administrator on 2018/8/4.
 */
public class AddPermissionRequest implements EffectiveExpireDateTime
{
    @EFrameRequest(required = true,length=128)
    private String url;

    @EFrameRequest(required = true,length=64)
    private String name;

    @EFrameRequest(defaultStrValue = "1")
    @JsonIgnore
    private String type;

    @EFrameRequest(bean = "_e_eframe_permissioncode")
    @JsonIgnore
    private String code;

    @EFrameRequest(bean = "_dictionary(permission_method)")
    private String method;

    @EFrameRequest(bean = "_dictionary(permission_white)")
    private String white;

    @EFrameRequest(bean = "_dictionary(permission_status)")
    private String status;

    @EFrameRequest(bean = "_e_defaultNow")
    private Date eff;

    @EFrameRequest(bean="_e_defaultExpireTime")
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

    @Override
    public Date getEff()
    {
        return eff;
    }

    public void setEff(Date eff)
    {
        this.eff = eff;
    }

    @Override
    public Date getExp()
    {
        return exp;
    }

    public void setExp(Date exp)
    {
        this.exp = exp;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }
}
