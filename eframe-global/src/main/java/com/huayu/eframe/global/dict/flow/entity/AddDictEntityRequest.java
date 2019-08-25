package com.huayu.eframe.global.dict.flow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/8/24.
 */
public class AddDictEntityRequest
{
    @EFrameRequest(required = true,length = 255)
    private String dictCode;

    @EFrameRequest(bean = "_e_set_random_value(dict_)")
    @JsonIgnore
    private String dictInnerCode;

    @EFrameRequest(required = true,length = 255)
    private String dictName;

    public String getDictCode()
    {
        return dictCode;
    }

    public void setDictCode(String dictCode)
    {
        this.dictCode = dictCode;
    }

    public String getDictInnerCode()
    {
        return dictInnerCode;
    }

    public void setDictInnerCode(String dictInnerCode)
    {
        this.dictInnerCode = dictInnerCode;
    }

    public String getDictName()
    {
        return dictName;
    }

    public void setDictName(String dictName)
    {
        this.dictName = dictName;
    }
}
