package com.huayu.eframe.server.config.table.flow.addparameter;

import com.huayu.eframe.server.common.i18n.table.bo.Languages;
import com.huayu.eframe.server.config.table.common.Category;
import com.huayu.eframe.server.config.table.common.Description;
import com.huayu.eframe.server.flow.annotation.EFrameRequest;

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
