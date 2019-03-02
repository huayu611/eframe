package com.huayu.eframe.config.flow.addparameter;

import com.huayu.eframe.flow.annotation.EFrameRequest;

public class AddParameterRequest
{
    @EFrameRequest(required = true)
    private String parameterCode;

    @EFrameRequest(required = true)
    private String parameterValue;

    @EFrameRequest(required = true)
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