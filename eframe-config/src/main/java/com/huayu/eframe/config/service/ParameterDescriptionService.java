package com.huayu.eframe.config.service;

import com.huayu.eframe.config.bo.ParameterDescription;

public interface ParameterDescriptionService
{

    ParameterDescription getParameterDescription(String parameterCode);

    void deleteParameterDescription(String parameterCode);

    ParameterDescription updateParameterDescription(ParameterDescription description);

    ParameterDescription addParameterDescription(ParameterDescription description);
}
