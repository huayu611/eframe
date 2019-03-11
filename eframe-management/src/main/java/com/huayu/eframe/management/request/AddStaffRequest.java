package com.huayu.eframe.management.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huayu.eframe.management.constant.SecurityConstant;
import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.common.restful.EffectiveExpireDateTime;

import java.util.Date;

/**
 * Created by Administrator on 2018/7/21.
 */
public class AddStaffRequest implements EffectiveExpireDateTime
{
    @EFrameRequest(required = true)
    private String name;

    @EFrameRequest(required = true)
    private String password;

    @EFrameRequest(required = true)
    private String login;

    @EFrameRequest(bean = "_e_validPhoneNumber")
    private String tel;

    //0:正常用户，1：锁定用户，锁定用户不能登陆和使用系统
   @EFrameRequest(bean = "_dictionary(staff_status)")
    private String status;

    @EFrameRequest(bean = "_e_defaultNow")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private Date eff;

    @EFrameRequest(bean = "_e_defaultExpireTime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private Date exp;

    @EFrameRequest(defaultStrValue = "zh",bean = "_e_validLang" )
    private String lang;

    @EFrameRequest(bean = "_e_validEmail")
    private String email;

    private String remark;

    private String roles;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
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

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getRoles()
    {
        return roles;
    }

    public void setRoles(String roles)
    {
        this.roles = roles;
    }
}
