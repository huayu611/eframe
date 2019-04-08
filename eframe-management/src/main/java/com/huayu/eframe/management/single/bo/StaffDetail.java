package com.huayu.eframe.management.single.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/8/6.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StaffDetail
{
    private String name;

    private String login;

    private String status;

    private Date eff;

    private Date exp;

    private String lang;

    private String email;

    private String tel;

    private String remark;

    private List<RoleDetail> roles;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
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

    public String getLang()
    {
        return lang;
    }

    public void setLang(String lang)
    {
        this.lang = lang;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public List<RoleDetail> getRoles()
    {
        return roles;
    }

    public void setRoles(List<RoleDetail> roles)
    {
        this.roles = roles;
    }
}
