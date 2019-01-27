package com.huayu.eframe.server.security.service.request;

import com.huayu.eframe.server.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2018/9/28.
 */
public class DeleteStaffRequest
{
    @EFrameRequest(required=true)
    private String login;

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }
}
