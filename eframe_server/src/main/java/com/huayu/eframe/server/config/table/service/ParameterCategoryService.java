package com.huayu.eframe.server.config.table.service;

import com.huayu.eframe.server.config.table.bo.ParameterCategory;

public interface ParameterCategoryService
{
    ParameterCategory getParameterCategory(String categoryCode);

    void deleteParameterCategory(String categoryCode);

    ParameterCategory updateParameterCategory(ParameterCategory description);

    ParameterCategory addParameterCategory(ParameterCategory description);
}
