package com.huayu.eframe.config.parameter.flow.common;

import com.huayu.eframe.config.parameter.service.ParameterDetail;

/**
 * Created by Leo on 2019/2/28.
 */
public class ParameterResponse
{
    private ParameterDetail detail;

    public ParameterDetail getDetail()
    {
        return detail;
    }

    public void setDetail(ParameterDetail detail)
    {
        this.detail = detail;
    }
}
