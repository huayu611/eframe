package com.huayu.eframe.config.lang.entity.service;

/**
 * Created by Leo on 2019/7/22.
 */
public class LangDefineDetail
{
    public LangDefineDetail(String lCode,String lName)
    {
        this.languageCode = lCode;
        this.languageName = lName;
    }

    public LangDefineDetail()
    {

    }

    private String languageCode;

    private String languageName;

    public String getLanguageCode()
    {
        return languageCode;
    }

    public void setLanguageCode(String languageCode)
    {
        this.languageCode = languageCode;
    }

    public String getLanguageName()
    {
        return languageName;
    }

    public void setLanguageName(String languageName)
    {
        this.languageName = languageName;
    }
}
