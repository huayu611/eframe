package com.huayu.eframe.management.request;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2018/9/30.
 */
public class DeletePermissionRequest
{
    @EFrameRequest(required = true)
    private String code;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }
}
