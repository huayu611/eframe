package com.huayu.eframe.copinfo.server.flow.copbaseinfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.copinfo.server.serivce.bo.CopBaseInfoDetail;

/**
 * Created by Leo on 2019/1/18.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QueryCopInfoResponse
{
    private CopBaseInfoDetail detail;

    public CopBaseInfoDetail getDetail()
    {
        return detail;
    }

    public void setDetail(CopBaseInfoDetail detail)
    {
        this.detail = detail;
    }
}
