package com.huayu.eframe.management.request;

import com.huayu.eframe.server.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2018/9/29.
 */
public class DeleteRoleRequest
{
    @EFrameRequest(required = true)
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
