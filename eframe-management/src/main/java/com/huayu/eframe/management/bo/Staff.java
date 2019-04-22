package com.huayu.eframe.management.bo;

import com.huayu.eframe.server.common.standard.ValidTime;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2018/7/14.
 */
@Entity
@Table(name="SYS_STAFF")
public class Staff implements ValidTime
{
    @Id
    @Column(name="STAFF_ID", length=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="LOGIN_NAME", length=32)
    private String loginName;

    @Column(name="STAFF_NAME", length=64)
    private String staffname;

    @Column(name="LANG", length=8)
    private String lang;

    @Column(name="EMAIL")
    private String email;

    @Column(name="TEL_NUM")
    private String telNumber;

    @Column(name="STAFF_PASSWORD", length=100)
    private String password;

    @Column(name="SALT", length=16)
    private String salt;

    @Column(name="LAST_LOGIN_TIME")
    private Date lastLoginTime;

    @Column(name="CREATE_TIME")
    private Date createTime;

    @Column(name="EFFECTIVE_TIME")
    private Date effectiveTime;

    @Column(name="EXPIRE_TIME")
    private Date expireTime;

    @Column(name="STATUS", length=2)
    private String status;

    @Column(name="LAST_UPDATE_TIME")
    private Date lastUpdateTime;

//    @Column(name="REMARK")
    /**
     * it's not use in v1.1 and the column will move to staffext bo
     */
    @Deprecated
    private String remark;

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getLastLoginTime()
    {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getEffectiveTime()
    {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime)
    {
        this.effectiveTime = effectiveTime;
    }

    public Date getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(Date expireTime)
    {
        this.expireTime = expireTime;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getStaffname()
    {
        return staffname;
    }

    public void setStaffname(String staffname)
    {
        this.staffname = staffname;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getLang()
    {
        return lang;
    }

    public void setLang(String lang)
    {
        this.lang = lang;
    }

    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTelNumber()
    {
        return telNumber;
    }

    public void setTelNumber(String telNumber)
    {
        this.telNumber = telNumber;
    }

    /**
     * it's not use in v1.1 and the column will move to staffext bo
     */
    @Deprecated
    public String getRemark()
    {
        return remark;
    }

    /**
     * it's not use in v1.1 and the column will move to staffext bo
     * @param remark  not use in v1.1
     */
    @Deprecated
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}
