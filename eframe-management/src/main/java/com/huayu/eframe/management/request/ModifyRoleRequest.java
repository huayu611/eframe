package com.huayu.eframe.management.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huayu.eframe.management.constant.SecurityConstant;
import com.huayu.eframe.flow.annotation.EFrameRequest;

import java.util.Date;

/**
 * Created by Leo on 2018/10/2.
 */
public class ModifyRoleRequest
{
    private String name;

    @EFrameRequest(required = true)
    private String code;

    private String parent;

    @EFrameRequest(enumValue = {SecurityConstant.STATUS.NORMAL,SecurityConstant.STATUS.LOCKED})
    private String status;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private Date exp;

    private String permissions;

    private String addPermissions;

    private String removePermissions;

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



    public String getPermissions()
    {
        return permissions;
    }

    public void setPermissions(String permissions)
    {
        this.permissions = permissions;
    }


    public String getAddPermissions()
    {
        return addPermissions;
    }

    public void setAddPermissions(String addPermissions)
    {
        this.addPermissions = addPermissions;
    }

    public String getRemovePermissions()
    {
        return removePermissions;
    }

    public void setRemovePermissions(String removePermissions)
    {
        this.removePermissions = removePermissions;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Date getExp()
    {
        return exp;
    }

    public void setExp(Date exp)
    {
        this.exp = exp;
    }
}
