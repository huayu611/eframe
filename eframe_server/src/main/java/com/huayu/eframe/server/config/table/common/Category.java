package com.huayu.eframe.server.config.table.common;

import com.huayu.eframe.server.common.i18n.table.bo.Languages;

public class Category
{
    private String categoryCode;

    private Languages languages;

    private String categoryName;

    private String parentCategory;


    public String getCategoryCode()
    {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode)
    {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getParentCategory()
    {
        return parentCategory;
    }

    public void setParentCategory(String parentCategory)
    {
        this.parentCategory = parentCategory;
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
