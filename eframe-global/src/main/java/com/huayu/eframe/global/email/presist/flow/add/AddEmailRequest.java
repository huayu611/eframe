package com.huayu.eframe.global.email.presist.flow.add;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/3/10.
 */
public class AddEmailRequest
{
    @EFrameRequest(required = true)
    private String emailCode;

    @EFrameRequest(required = true)
    private String name;

    @EFrameRequest(required = true)
    private String emailUserName;

    @EFrameRequest(required = true,bean = "_e_email_password_encrypt")
    private String emailPassword;

    @EFrameRequest(required = true)
    private String protocol;

    @EFrameRequest(required = true)
    private String host;

    @EFrameRequest(defaultIntegerValue = 25)
    private Integer port;

    @EFrameRequest(defaultLongValue = 3000l)
    private Long timeOut;

    private String auth;

    @EFrameRequest(required = true)
    private String sender;

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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}