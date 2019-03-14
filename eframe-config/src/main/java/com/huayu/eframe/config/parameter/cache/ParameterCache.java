package com.huayu.eframe.config.parameter.cache;

import com.huayu.eframe.config.parameter.bo.Parameter;

import java.util.List;

/**
 * Created by Leo on 2018/9/9.
 */
public interface ParameterCache
{
    String CACHE = "SYS_PARAMETER";

    String getParameterValue(String parameterCode);

    Parameter getParameterByParameterCode(String parameterCode);

    List<Parameter> getAll();
}
