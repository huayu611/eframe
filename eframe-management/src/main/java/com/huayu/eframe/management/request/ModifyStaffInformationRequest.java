package com.huayu.eframe.management.request;

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

    @EFrameRequest(required = true, length = 64)
    private String login;

    //0:正常用户，1：锁定用户，锁定用户不能登陆和使用系统
    @EFrameRequest(bean = "_dictionary(staff_status)")
    private String status;

    @EFrameRequest(bean = "_e_validExpireTime")
    private Date exp;

    @EFrameRequest(bean = "_e_validLang", length = 16)
    private String lang;

    @EFrameRequest(bean = "_e_validEmail", length = 128)
    private String email;

    @EFrameRequest(bean = "_e_validPhoneNumber")
    private String tel;

    @EFrameRequest(length = 512)
    private String remark;

    @EFrameRequest(length = 32)
    private String realName;

    @EFrameRequest(length = 64)
    private String nickName;

    @EFrameRequest(length = 28)
    private String identityId;

    @EFrameRequest(length = 15)
    private String qq;

    @EFrameRequest(length = 64)
    private String wechat;

    @EFrameRequest(length = 64)
    private String weibo;

    @EFrameRequest(length = 255)
    private String avatar;

    @EFrameRequest(length = 1024)
    private String signature;

    @EFrameRequest(bean = "_e_validPhoneNumber", length = 1024)
    private String otherTelNumber;

    @EFrameRequest(length = 64)
    private String alipay;

    private Date birthday;

    @EFrameRequest(length = 1024)
    private String roles;

    @EFrameRequest(bean = "_dictionary(global-gender)")
    private String gender;

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

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getIdentityId()
    {
        return identityId;
    }

    public void setIdentityId(String identityId)
    {
        this.identityId = identityId;
    }

    public String getQq()
    {
        return qq;
    }

    public void setQq(String qq)
    {
        this.qq = qq;
    }

    public String getWechat()
    {
        return wechat;
    }

    public void setWechat(String wechat)
    {
        this.wechat = wechat;
    }

    public String getWeibo()
    {
        return weibo;
    }

    public void setWeibo(String weibo)
    {
        this.weibo = weibo;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getSignature()
    {
        return signature;
    }

    public void setSignature(String signature)
    {
        this.signature = signature;
    }

    public String getOtherTelNumber()
    {
        return otherTelNumber;
    }

    public void setOtherTelNumber(String otherTelNumber)
    {
        this.otherTelNumber = otherTelNumber;
    }

    public String getAlipay()
    {
        return alipay;
    }

    public void setAlipay(String alipay)
    {
        this.alipay = alipay;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }
}
