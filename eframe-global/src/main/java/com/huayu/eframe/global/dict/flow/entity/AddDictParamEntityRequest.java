package com.huayu.eframe.global.dict.flow.entity;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/8/25.
 */
public class AddDictParamEntityRequest
{
    @EFrameRequest(required = true,length = 32)
    private String key;

    @EFrameRequest(required = true,length = 1024)
    private String value;

    @EFrameRequest(defaultIntegerValue = 1)
    private Integer order;

    @EFrameRequest(required = true,length = 255)
    private String dictInnerCode;

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public Integer getOrder()
    {
        return order;
    }

    public void setOrder(Integer order)
    {
        this.order = order;
    }

    public String getDictInnerCode()
    {
        return dictInnerCode;
    }

    public void setDictInnerCode(String dictInnerCode)
    {
        this.dictInnerCode = dictInnerCode;
    }
}
