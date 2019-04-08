package com.huayu.eframe.management.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.management.constant.SecurityConstant;
import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Administrator on 2018/7/18.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginRequest
{
    @EFrameRequest(required = true,length = 512)
    private String login;

    @EFrameRequest(required = true,length = 512)
    private String password;

    @JsonIgnore
    private String code;

    @JsonIgnore
    private String type;

    @EFrameRequest(bean = "_e_validLang",length = 16)
    private String lang;


    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getLang()
    {
        return lang;
    }

    public void setLang(String lang)
    {
        this.lang = lang;
    }
}
