package com.huayu.eframe.server.security.service.flow.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.server.security.service.single.bo.StaffDetail;

/**
 * Created by Administrator on 2018/7/18.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginResponse
{
    private String token;

    private String expireTime;

    private StaffDetail staff;

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(String expireTime)
    {
        this.expireTime = expireTime;
    }

    public StaffDetail getStaff()
    {
        return staff;
    }

    public void setStaff(StaffDetail staff)
    {
        this.staff = staff;
    }
}
