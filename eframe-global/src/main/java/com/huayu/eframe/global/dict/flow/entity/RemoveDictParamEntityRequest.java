package com.huayu.eframe.global.dict.flow.entity;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/8/25.
 */
public class RemoveDictParamEntityRequest
{
    @EFrameRequest(required = true,length = 255)
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
