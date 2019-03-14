package com.huayu.eframe.config.parameter.service;

import com.huayu.eframe.config.parameter.bo.Parameter;

import java.util.List;

public interface ParameterService
{

    Parameter getParameterByCode(String code);

    List<ParameterDetail> getAllParameter();

    public ParameterDetail updateParameter(ParameterDetail parameter);

    ParameterDetail addParameter(ParameterDetail parameter);

    String deleteParameter(String parameterCode);

}
