package com.huayu.eframe.config.parameter.flow.modify;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/3/1.
 */
public class ModifyParameterRequest
{
    @EFrameRequest(required = true, length = 64)
    private String parameterCode;

    @EFrameRequest(required = true, length = 64)
    private String parameterValue;

    @EFrameRequest(length = 64)
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
