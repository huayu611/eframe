package com.huayu.eframe.server.config.table.flow.common;

import com.huayu.eframe.server.config.table.service.ParameterDetail;

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
