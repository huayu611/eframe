package com.huayu.eframe.server.config.table.cache;

import com.huayu.eframe.server.config.table.bo.Parameter;

import java.util.List;

/**
 * Created by Leo on 2018/9/9.
 */
public interface ParameterCache
{
    String getParameterValue(String parameterCode);

    Parameter getParameterByParameterCode(String parameterCode);

    List<Parameter> getAll();
}
