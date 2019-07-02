package com.huayu.eframe.management.single.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String realName;

    private String nickName;

    @JsonIgnore
    private String identityId;

    private String qq;

    private String wechat;

    private String weibo;

    private String avatar;

    private String signature;

    private String otherTelNumber;

    private String alipay;

    private Date birthday;

    private String gender;

    private String hiddenIdentity;

    private String statusName;

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

    public String getHiddenIdentity()
    {
        return hiddenIdentity;
    }

    public void setHiddenIdentity(String hiddenIdentity)
    {
        this.hiddenIdentity = hiddenIdentity;
    }

    public String getStatusName()
    {
        return statusName;
    }

    public void setStatusName(String statusName)
    {
        this.statusName = statusName;
    }
}
