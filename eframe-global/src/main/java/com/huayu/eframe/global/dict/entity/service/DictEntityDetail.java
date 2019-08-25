package com.huayu.eframe.global.dict.entity.service;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by Leo on 2019/8/24.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DictEntityDetail
{
    private String dictCode;

    private String dictInnerCode;

    private String dictName;

    private List<DictParamDetail> params;



    public String getDictCode()
    {
        return dictCode;
    }

    public void setDictCode(String dictCode)
    {
        this.dictCode = dictCode;
    }

    public List<DictParamDetail> getParams()
    {
        return params;
    }

    public void setParams(List<DictParamDetail> params)
    {
        this.params = params;
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
