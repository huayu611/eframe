package com.huayu.eframe.server.security.service.request;

import com.huayu.eframe.server.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2018/12/23.
 */
public class ModifyPasswordRequest
{
    private String oldPassword;

    @EFrameRequest(required = true)
    private String newPassword;

    @EFrameRequest(required = true)
    private String login;


    public String getOldPassword()
    {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword)
    {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }
}
