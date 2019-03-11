package com.huayu.eframe.global.email.presist.bo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Leo on 2018/9/28.
 */
@Entity
@Table(name="SYS_EMAIL")
public class EmailBO
{
    @Id
    @Column(name="ID", length=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="EMAIL_CODE", length=125)
    private String emailCode;

    @Column(name="NAME", length=225)
    private String name;

    @Column(name="STATUS", length=2)
    private String status;

    @Column(name="EMAIL_USERNAME", length=64)
    private String emailUserName;

    @Column(name="EMAIL_PASSWORD", length=255)
    private String emailPassword;

    @Column(name="EMAIL_PROTOCOL", length=16)
    private String protocol;
    @Column(name="EMAIL_HOST", length=64)
    private String host;
    @Column(name="EMAIL_PORT", length=8)
    private int port;
    @Column(name="AUTH", length=8)
    private String auth;

    @Column(name="EMAIL_TIMEOUT", length=20)
    private Long timeOut;

    @Column(name="EMAIL_SENDER", length=125)
    private String emailSender;

    @Column(name="LAST_UPDATE_TIME")
    private Date lastUpdateTime;

    @Column(name="CREATE_OBJ_ID", length=20)
    private Long createObjId;

    @Column(name="CREATE_OBJ_TYPE", length=128)
    private String createObjType;

    @Column(name="LAST_UPDATE_OBJ_ID", length=20)
    private Long lastUpdateObjId;

    @Column(name="LAST_UPDATE_OBJ_TYPE", length=128)
    private String lastUpdateObjType;

    @Column(name="CREATE_TIME")
    private Date createTime;


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getEmailCode()
    {
        return emailCode;
    }

    public void setEmailCode(String emailCode)
    {
        this.emailCode = emailCode;
    }

    public String getEmailUserName()
    {
        return emailUserName;
    }

    public void setEmailUserName(String emailUserName)
    {
        this.emailUserName = emailUserName;
    }

    public String getEmailPassword()
    {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword)
    {
        this.emailPassword = emailPassword;
    }

    public String getProtocol()
    {
        return protocol;
    }

    public void setProtocol(String protocol)
    {
        this.protocol = protocol;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getCreateObjId()
    {
        return createObjId;
    }

    public void setCreateObjId(Long createObjId)
    {
        this.createObjId = createObjId;
    }

    public String getCreateObjType()
    {
        return createObjType;
    }

    public void setCreateObjType(String createObjType)
    {
        this.createObjType = createObjType;
    }

    public Long getLastUpdateObjId()
    {
        return lastUpdateObjId;
    }

    public void setLastUpdateObjId(Long lastUpdateObjId)
    {
        this.lastUpdateObjId = lastUpdateObjId;
    }

    public String getLastUpdateObjType()
    {
        return lastUpdateObjType;
    }

    public void setLastUpdateObjType(String lastUpdateObjType)
    {
        this.lastUpdateObjType = lastUpdateObjType;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getAuth()
    {
        return auth;
    }

    public void setAuth(String auth)
    {
        this.auth = auth;
    }

    public String getEmailSender()
    {
        return emailSender;
    }

    public void setEmailSender(String emailSender)
    {
        this.emailSender = emailSender;
    }

    public Long getTimeOut()
    {
        return timeOut;
    }

    public void setTimeOut(Long timeOut)
    {
        this.timeOut = timeOut;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
