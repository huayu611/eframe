package com.huayu.eframe.global.dict.flow.entity;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/8/25.
 */
public class ModifyDictParamEntityRequest
{
    @EFrameRequest(length = 32)
    private String key;

    @EFrameRequest(length = 1024)
    private String value;

    private Integer order;

    @EFrameRequest(length = 255)
    private String dictInnerCode;

    @EFrameRequest(required = true,length = 255)
    private String code;

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

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }
}
