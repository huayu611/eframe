package com.huayu.eframe.config.parameter.service;

import com.huayu.eframe.config.parameter.bo.ParameterDescription;

public interface ParameterDescriptionService
{

    ParameterDescription getParameterDescription(String parameterCode);

    void deleteParameterDescription(String parameterCode);

    ParameterDescription updateParameterDescription(ParameterDescription description);

    ParameterDescription addParameterDescription(ParameterDescription description);
}
