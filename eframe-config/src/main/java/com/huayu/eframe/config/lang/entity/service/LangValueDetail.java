package com.huayu.eframe.config.lang.entity.service;

import com.huayu.eframe.config.lang.entity.bo.LangValue;
import com.huayu.eframe.config.lang.entity.bo.LangText;

/**
 * Created by Leo on 2019/8/3.
 */
public class LangValueDetail
{

    public LangValueDetail()
    {

    }
    public LangValueDetail(LangValue lang)
    {
        if(null != lang.getLangDefine())
        {
            this.langKey = lang.getLangDefine().getCode();
        }

        this.value = lang.getValue();
        this.primaryKey = lang.getPrimaryCode();
    }

    public LangValueDetail(LangText lang)
    {
        if(null != lang.getLangDefine())
        {
            this.langKey = lang.getLangDefine().getCode();
        }

        this.value = lang.getValue();
        this.primaryKey = lang.getPrimaryCode();
    }

    private String langKey;

    private String value;

    private String primaryKey;

    public String getLangKey()
    {
        return langKey;
    }

    public void setLangKey(String langKey)
    {
        this.langKey = langKey;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getPrimaryKey()
    {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey)
    {
        this.primaryKey = primaryKey;
    }
}
