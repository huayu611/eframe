package com.huayu.eframe.server.security.menu.flow.delmenu;

import com.huayu.eframe.server.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/2/12.
 */
public class DeleteMenuRequest
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
