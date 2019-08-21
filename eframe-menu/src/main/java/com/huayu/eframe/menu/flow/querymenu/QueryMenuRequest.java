package com.huayu.eframe.menu.flow.querymenu;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Administrator on 2018/8/12.
 */
public class QueryMenuRequest
{
    @EFrameRequest(length = 128)
    private String code;

    @EFrameRequest(length = 512)
    private String path;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }
}
