package com.huayu.eframe.config.parameter.service;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Leo on 2019/2/28.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ParameterDetail
{
    private String parameterCode;

    private String parameterValue;

    private String parameterName;

    public String getParameterCode()
    {
        return parameterCode;
    }

    public void setParameterCode(String parameterCode)
    {
        this.parameterCode = parameterCode;
    }

    public String getParameterValue()
    {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue)
    {
        this.parameterValue = parameterValue;
    }

    public String getParameterName()
    {
        return parameterName;
    }

    public void setParameterName(String parameterName)
    {
        this.parameterName = parameterName;
    }
}
