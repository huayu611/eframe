package com.huayu.eframe.management.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huayu.eframe.flow.annotation.EFrameRequest;

import java.util.Date;
import java.util.List;

/**
 * Created by Leo on 2018/10/2.
 */
public class ModifyRoleRequest
{
    @EFrameRequest(length = 64)
    private String name;

    @EFrameRequest(required = true)
    private String code;

    @JsonIgnore
    private String parent;

    @EFrameRequest(bean = "_dictionary(role_status)")
    private String status;

    private Date exp;

    @EFrameRequest(length = 2048)
    private String permissions;

    @EFrameRequest(length = 2048)
    private String addPermissions;

    @EFrameRequest(length = 2048)
    private String removePermissions;

    @EFrameRequest(length = 512)
    private String remark;

    @EFrameRequest(length = 2048)
    private String roleMenus;

    public String getRoleMenus()
    {
        return roleMenus;
    }

    public void setRoleMenus(String roleMenus)
    {
        this.roleMenus = roleMenus;
    }

    private List<String> addMenus;

    private List<String> removeMenus;


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

    public List<String> getAddMenus()
    {
        return addMenus;
    }

    public void setAddMenus(List<String> addMenus)
    {
        this.addMenus = addMenus;
    }

    public List<String> getRemoveMenus()
    {
        return removeMenus;
    }

    public void setRemoveMenus(List<String> removeMenus)
    {
        this.removeMenus = removeMenus;
    }
}
