package com.huayu.eframe.global.system.log.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.server.log.presist.service.LogDetail;

import java.util.List;

/**
 * Created by Leo on 2019/2/18.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QueryOperatorLogResponse
{
    private List<LogDetail> details;

    public List<LogDetail> getDetails()
    {
        return details;
    }

    public void setDetails(List<LogDetail> details)
    {
        this.details = details;
    }
}
