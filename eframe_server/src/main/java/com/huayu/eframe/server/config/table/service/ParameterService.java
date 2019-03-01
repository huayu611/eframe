package com.huayu.eframe.server.config.table.service;

import com.huayu.eframe.server.config.table.bo.Parameter;

import java.util.List;

public interface ParameterService
{

    Parameter getParameterByCode(String code);

    List<ParameterDetail> getAllParameter();

    public ParameterDetail updateParameter(ParameterDetail parameter);

    ParameterDetail addParameter(ParameterDetail parameter);

    String deleteParameter(String parameterCode);

}
