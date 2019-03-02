package com.huayu.eframe.config.flow.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.config.service.ParameterDetail;

import java.util.List;

/**
 * Created by Leo on 2019/2/28.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QueryParameterResponse
{
    private List<ParameterDetail> parameters;

    public List<ParameterDetail> getParameters()
    {
        return parameters;
    }

    public void setParameters(List<ParameterDetail> parameters)
    {
        this.parameters = parameters;
    }
}
