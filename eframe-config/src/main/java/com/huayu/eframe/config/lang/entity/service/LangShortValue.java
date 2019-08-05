package com.huayu.eframe.config.lang.entity.service;

import com.huayu.eframe.config.lang.entity.bo.LangValue;

import java.util.Map;

public class LangShortValue
{

    public LangShortValue()
    {

    }

    public LangShortValue(LangValue lang)
    {
        this.foreignCode = lang.getLangCode();
        if(null != lang.getLangDefine())
        {
            this.languageCode = lang.getLangDefine().getCode();
        }
        this.value = lang.getValue();
        this.primaryCode = lang.getPrimaryCode();
    }

    private Map<String,LangValueDetail> values;

    private String foreignCode;

    private String languageCode;

    private String primaryCode;

    private String value;

    public String getLanguageCode()
    {
        return languageCode;
    }

    public void setLanguageCode(String languageCode)
    {
        this.languageCode = languageCode;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getForeignCode()
    {
        return foreignCode;
    }

    public void setForeignCode(String foreignCode)
    {
        this.foreignCode = foreignCode;
    }

    public String getPrimaryCode()
    {
        return primaryCode;
    }

    public void setPrimaryCode(String primaryCode)
    {
        this.primaryCode = primaryCode;
    }

    public Map<String, LangValueDetail> getValues()
    {
        return values;
    }

    public void setValues(Map<String, LangValueDetail> values)
    {
        this.values = values;
    }
}
