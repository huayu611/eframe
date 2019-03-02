package com.huayu.eframe.config.service;

import com.huayu.eframe.config.bo.Parameter;

import java.util.List;

public interface ParameterService
{

    Parameter getParameterByCode(String code);

    List<ParameterDetail> getAllParameter();

    public ParameterDetail updateParameter(ParameterDetail parameter);

    ParameterDetail addParameter(ParameterDetail parameter);

    String deleteParameter(String parameterCode);

}
