package com.huayu.eframe.management.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2018/12/23.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ModifyPasswordRequest
{
    @EFrameRequest(length = 64)
    private String oldPassword;

    @EFrameRequest(required = true, length = 64)
    private String newPassword;

    @EFrameRequest(required = true, length = 64)
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
