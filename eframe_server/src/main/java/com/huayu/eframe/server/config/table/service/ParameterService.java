package com.huayu.eframe.server.config.table.service;

import com.huayu.eframe.server.config.table.bo.Parameter;

import java.util.List;

public interface ParameterService
{

    Parameter getParameterByCode(String code);

    List<Parameter> getAllParameter();

    public Parameter updateParameter(Parameter parameter);

    Parameter save(Parameter parameter);

}
