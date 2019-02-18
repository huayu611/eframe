package com.huayu.eframe.server.security.service.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.server.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.security.service.constant.SecurityConstant;

/**
 * Created by Administrator on 2018/7/18.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginRequest
{
    @EFrameRequest(required = true)
    private String login;

    @EFrameRequest(required = true)
    private String password;

    private String code;

    @EFrameRequest(enumValue = {SecurityConstant.LOGIN_TYPE.LOGIN_REFRESH_EXPIRE_TIME
    ,SecurityConstant.LOGIN_TYPE.LOGIN_ONCE,SecurityConstant.LOGIN_TYPE.LOGIN_FIX_EXPIRE_TIME})
    private String type;

    @EFrameRequest(bean = "_e_validLang")
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
