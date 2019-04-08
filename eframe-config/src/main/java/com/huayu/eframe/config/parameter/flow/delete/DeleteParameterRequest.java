package com.huayu.eframe.config.parameter.flow.delete;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/4/8.
 */
public class DeleteParameterRequest
{
    @EFrameRequest(required = true,length = 64)
    private String parameterCode;

    public String getParameterCode()
    {
        return parameterCode;
    }

    public void setParameterCode(String parameterCode)
    {
        this.parameterCode = parameterCode;
    }
}
