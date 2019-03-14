package com.huayu.eframe.global.email.presist.flow.update;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/3/11.
 */
public class ModifyEmailRequest
{
    @EFrameRequest(required = true)
    private String emailCode;

    private String name;

    private String emailUserName;

    @EFrameRequest(bean = "_e_email_password_encrypt")
    private String emailPassword;

    private String protocol;

    private String host;

    private Integer port;

    private Long timeOut;

    private String auth;

    private String sender;

    private String subject;

    private String personal;


    public String getEmailCode()
    {
        return emailCode;
    }

    public void setEmailCode(String emailCode)
    {
        this.emailCode = emailCode;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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

    public Integer getPort()
    {
        return port;
    }

    public void setPort(Integer port)
    {
        this.port = port;
    }

    public Long getTimeOut()
    {
        return timeOut;
    }

    public void setTimeOut(Long timeOut)
    {
        this.timeOut = timeOut;
    }

    public String getAuth()
    {
        return auth;
    }

    public void setAuth(String auth)
    {
        this.auth = auth;
    }

    public String getSender()
    {
        return sender;
    }

    public void setSender(String sender)
    {
        this.sender = sender;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getPersonal()
    {
        return personal;
    }

    public void setPersonal(String personal)
    {
        this.personal = personal;
    }
}
