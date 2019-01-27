package com.huayu.eframe.server.config.table.service;

import com.huayu.eframe.server.config.table.bo.ParameterDescription;

public interface ParameterDescriptionService
{

    ParameterDescription getParameterDescription(String parameterCode);

    void deleteParameterDescription(String parameterCode);

    ParameterDescription updateParameterDescription(ParameterDescription description);

    ParameterDescription addParameterDescription(ParameterDescription description);
}
