package com.huayu.eframe.config.service;

import com.huayu.eframe.config.bo.ParameterCategory;

public interface ParameterCategoryService
{
    ParameterCategory getParameterCategory(String categoryCode);

    void deleteParameterCategory(String categoryCode);

    ParameterCategory updateParameterCategory(ParameterCategory description);

    ParameterCategory addParameterCategory(ParameterCategory description);
}
