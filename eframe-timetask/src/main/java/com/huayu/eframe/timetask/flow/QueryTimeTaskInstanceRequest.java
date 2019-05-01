package com.huayu.eframe.timetask.flow;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.common.restful.PagingRequest;

/**
 * Created by Leo on 2019/4/15.
 */
public class QueryTimeTaskInstanceRequest
{
    @EFrameRequest(required = true, length = 128)
    private String timeTaskCode;

    private PagingRequest page;

    public String getTimeTaskCode()
    {
        return timeTaskCode;
    }

    public void setTimeTaskCode(String timeTaskCode)
    {
        this.timeTaskCode = timeTaskCode;
    }

    public PagingRequest getPage()
    {
        return page;
    }

    public void setPage(PagingRequest page)
    {
        this.page = page;
    }
}
