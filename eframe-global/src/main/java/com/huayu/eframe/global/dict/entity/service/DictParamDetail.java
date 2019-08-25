package com.huayu.eframe.global.dict.entity.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Leo on 2019/8/24.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DictParamDetail
{
    private String key;

    private String value;

    private Integer order;

    private String code;

    private String dictCode;

    private String dictInnerCode;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getDictInnerCode()
    {
        return dictInnerCode;
    }

    public void setDictInnerCode(String dictInnerCode)
    {
        this.dictInnerCode = dictInnerCode;
    }

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

    public String getDictCode()
    {
        return dictCode;
    }

    public void setDictCode(String dictCode)
    {
        this.dictCode = dictCode;
    }
}
