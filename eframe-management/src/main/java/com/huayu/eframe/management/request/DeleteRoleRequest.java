package com.huayu.eframe.management.request;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2018/9/29.
 */
public class DeleteRoleRequest
{
    @EFrameRequest(required = true, length = 512)
    private String codes;

    public String getCodes()
    {
        return codes;
    }

    public void setCodes(String codes)
    {
        this.codes = codes;
    }
}
