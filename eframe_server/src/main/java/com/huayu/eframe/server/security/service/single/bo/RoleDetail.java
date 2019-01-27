package com.huayu.eframe.server.security.service.single.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.server.security.service.single.bo.PermissionDetail;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/8/6.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class  RoleDetail
{

    private List<PermissionDetail> permissions;


    private String name;

    private String code;

    private String parent;

    private String top;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private Date eff;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private Date exp;

    private String status;

    private String remark;


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
}
