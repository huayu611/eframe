package com.huayu.eframe.management.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huayu.eframe.management.constant.SecurityConstant;
import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.common.restful.EffectiveExpireDateTime;

import java.util.Date;

/**
 * Created by Leo on 2018/9/28.
 */
public class ModifyStaffInformationRequest implements EffectiveExpireDateTime
{
    @EFrameRequest(length = 64)
    private String name;

    @EFrameRequest(required = true,length = 64)
    private String login;

    //0:正常用户，1：锁定用户，锁定用户不能登陆和使用系统
    @EFrameRequest(bean = "_dictionary(staff_status)")
    private String status;

    @EFrameRequest(bean = "_e_validExpireTime")
    private Date exp;

    @EFrameRequest(bean = "_e_validLang",length = 16)
    private String lang;

    @EFrameRequest(bean = "_e_validEmail",length = 128)
    private String email;

    @EFrameRequest(length = 16)
    private String tel;

    @EFrameRequest(length = 512)
    private String remark;

    @EFrameRequest(length = 1024)
    private String roles;

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

    public String getRoles()
    {
        return roles;
    }

    public void setRoles(String roles)
    {
        this.roles = roles;
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
}
