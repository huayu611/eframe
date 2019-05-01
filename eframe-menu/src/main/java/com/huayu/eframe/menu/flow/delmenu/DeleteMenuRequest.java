package com.huayu.eframe.menu.flow.delmenu;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/2/12.
 */
public class DeleteMenuRequest
{
    @EFrameRequest(required = true, length = 128)
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
