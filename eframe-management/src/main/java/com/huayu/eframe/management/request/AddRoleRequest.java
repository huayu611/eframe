package com.huayu.eframe.management.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huayu.eframe.management.constant.SecurityConstant;
import com.huayu.eframe.flow.annotation.EFrameRequest;

import java.util.Date;

/**
 * Created by Administrator on 2018/8/4.
 */
public class AddRoleRequest
//        implements EffectiveExpireDateTime
{
    @EFrameRequest(required = true)
    private String name;

    @EFrameRequest(bean = "_e_eframe_rolecode")
    private String code;

    private String parent;

    @EFrameRequest(enumValue = {SecurityConstant.STATUS.NORMAL,SecurityConstant.STATUS.LOCKED}, defaultStrValue = SecurityConstant.STATUS.NORMAL)
    private String status;

    @EFrameRequest(bean = "_e_defaultNow")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private Date eff;

    @EFrameRequest(bean = "_e_defaultExpireTime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private Date exp;

    private String permissions;

    private String remark;

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

    public String getPermissions()
    {
        return permissions;
    }

    public void setPermissions(String permissions)
    {
        this.permissions = permissions;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}
