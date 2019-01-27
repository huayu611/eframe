package com.huayu.eframe.server.config.table.flow.addparameter;

import com.huayu.eframe.server.common.i18n.table.bo.Languages;
import com.huayu.eframe.server.config.table.common.Category;
import com.huayu.eframe.server.config.table.common.Description;
import com.huayu.eframe.server.flow.annotation.EFrameRequest;

public class AddParameterRequest
{
    @EFrameRequest(required = true)
    private String paramCode;

    @EFrameRequest(required = true)
    private String paramValue;

    @EFrameRequest(required = true)
    private String name;

    private Languages languages;

    private Category category;

    private Description description;

    public String getParamCode()
    {
        return paramCode;
    }

    public void setParamCode(String paramCode)
    {
        this.paramCode = paramCode;
    }

    public String getParamValue()
    {
        return paramValue;
    }

    public void setParamValue(String paramValue)
    {
        this.paramValue = paramValue;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Languages getLanguages()
    {
        return languages;
    }

    public void setLanguages(Languages languages)
    {
        this.languages = languages;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public Description getDescription()
    {
        return description;
    }

    public void setDescription(Description description)
    {
        this.description = description;
    }
}
