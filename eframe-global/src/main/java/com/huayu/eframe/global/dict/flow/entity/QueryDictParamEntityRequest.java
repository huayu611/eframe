package com.huayu.eframe.global.dict.flow.entity;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/8/25.
 */
public class QueryDictParamEntityRequest
{
    @EFrameRequest(required = true,length = 255)
    private String dictInnerCode;

    public String getDictInnerCode()
    {
        return dictInnerCode;
    }

    public void setDictInnerCode(String dictInnerCode)
    {
        this.dictInnerCode = dictInnerCode;
    }
}
