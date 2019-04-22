package com.huayu.eframe.management.flow.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.management.single.bo.StaffDetail;

/**
 * Created by Administrator on 2018/7/18.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginResponse
{
    private String token;

    private String expireTime;


    //当且仅到固定返回 token周期时长时返回，
    // 由于不能确切计算失效时间，所以不建议强验证该字段，
    // 可以用expireTime来设置过期时间，与服务器保持一致
    private Long duration;

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

    public Long getDuration()
    {
        return duration;
    }

    public void setDuration(Long duration)
    {
        this.duration = duration;
    }
}
