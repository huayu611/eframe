package com.huayu.eframe.global.email.presist.flow.remove;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/3/11.
 */
public class RemoveEmailRequest
{
    @EFrameRequest(length = 64,required = true)
    private String emailCode;

    public String getEmailCode()
    {
        return emailCode;
    }

    public void setEmailCode(String emailCode)
    {
        this.emailCode = emailCode;
    }
}
