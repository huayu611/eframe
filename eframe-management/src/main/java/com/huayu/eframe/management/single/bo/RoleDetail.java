package com.huayu.eframe.management.single.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.menu.service.MenuDetail;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/8/6.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RoleDetail
{

    private List<PermissionDetail> permissions;

    private String name;

    private String code;

    private String parent;

    private String top;

    private Date eff;

    private Date exp;

    private String status;

    private String statusName;

    private String remark;

    private List<String> addMenus;

    private List<String> removeMenus;

    private List<String> currentMenus;

    private String roleMenus;

    private List<MenuDetail> menus;

    public List<MenuDetail> getMenus()
    {
        return menus;
    }

    public void setMenus(List<MenuDetail> menus)
    {
        this.menus = menus;
    }

    public String getRoleMenus()
    {
        return roleMenus;
    }

    public void setRoleMenus(String roleMenus)
    {
        this.roleMenus = roleMenus;
    }

    public List<PermissionDetail> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(List<PermissionDetail> permissions)
    {
        this.permissions = permissions;
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

    public String getParent()
    {
        return parent;
    }

    public void setParent(String parent)
    {
        this.parent = parent;
    }

    public String getTop()
    {
        return top;
    }

    public void setTop(String top)
    {
        this.top = top;
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

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getStatusName()
    {
        return statusName;
    }

    public void setStatusName(String statusName)
    {
        this.statusName = statusName;
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

    public List<String> getCurrentMenus()
    {
        return currentMenus;
    }

    public void setCurrentMenus(List<String> currentMenus)
    {
        this.currentMenus = currentMenus;
    }
}
