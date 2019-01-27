package com.huayu.eframe.server.common.email.bo;

import javax.persistence.*;

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

    @Column(name="EMAIL_USERNAME", length=64)
    private String emailStaffName;

    @Column(name="EMAIL_PASSWORD", length=255)
    private String emailPassword;

    @Column(name="EMAIL_PROTOCOL", length=16)
    private String protocol;
    @Column(name="EMAIL_HOST", length=64)
    private String host;
    @Column(name="EMAIL_PORT", length=8)
    private String port;
    @Column(name="AUTH", length=8)
    private String auth;
    @Column(name="EMAIL_SENDER", length=125)
    private String emailSender;


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

    public String getEmailStaffName()
    {
        return emailStaffName;
    }

    public void setEmailStaffName(String emailStaffName)
    {
        this.emailStaffName = emailStaffName;
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

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
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
}
