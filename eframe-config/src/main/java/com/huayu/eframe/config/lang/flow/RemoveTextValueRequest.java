package com.huayu.eframe.config.lang.flow;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/8/3.
 */
public class RemoveTextValueRequest
{
    @EFrameRequest(required = true,length = 255)
    private String foreignCode;

    public String getForeignCode()
    {
        return foreignCode;
    }

    public void setForeignCode(String foreignCode)
    {
        this.foreignCode = foreignCode;
    }
}
