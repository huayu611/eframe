package com.huayu.eframe.management.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huayu.eframe.management.constant.SecurityConstant;
import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.common.restful.EffectiveExpireDateTime;

import java.util.Date;

/**
 * Created by Administrator on 2018/8/4.
 */
public class AddPermissionRequest implements EffectiveExpireDateTime
{
    @EFrameRequest(required = true)
    private String url;

    @EFrameRequest(required = true)
    private String name;

    @EFrameRequest(required = true)
    private String type;

    @EFrameRequest(bean = "_e_eframe_permissioncode")
    private String code;

    @EFrameRequest(required = true)
    private String method;

    @EFrameRequest(enumValue = {SecurityConstant.PERMISSION_WHITE.YES,SecurityConstant.PERMISSION_WHITE.NO},defaultStrValue = SecurityConstant.PERMISSION_WHITE.NO)
    private String white;

    @EFrameRequest(enumValue = {SecurityConstant.STATUS.NORMAL,SecurityConstant.STATUS.LOCKED}, defaultStrValue = SecurityConstant.STATUS.NORMAL)
    private String status;

    @EFrameRequest(bean = "_e_defaultNow")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private Date eff;

    @EFrameRequest(bean="_e_defaultExpireTime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
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
