package com.huayu.eframe.server.config.table.common;

import com.huayu.eframe.server.common.i18n.table.bo.Languages;

public class Description
{
    private String descrition;

    private Languages languages;


    public String getDescrition()
    {
        return descrition;
    }

    public void setDescrition(String descrition)
    {
        this.descrition = descrition;
    }

    public Languages getLanguages()
    {
        return languages;
    }

    public void setLanguages(Languages languages)
    {
        this.languages = languages;
    }
}
