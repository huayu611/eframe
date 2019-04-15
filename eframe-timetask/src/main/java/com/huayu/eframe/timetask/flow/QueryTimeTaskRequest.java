package com.huayu.eframe.timetask.flow;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.common.restful.PagingRequest;

/**
 * Created by Leo on 2019/4/15.
 */
public class QueryTimeTaskRequest
{
    private PagingRequest page;

    @EFrameRequest(length = 64)
    private String timeTaskName;

    public PagingRequest getPage()
    {
        return page;
    }

    public void setPage(PagingRequest page)
    {
        this.page = page;
    }

    public String getTimeTaskName()
    {
        return timeTaskName;
    }

    public void setTimeTaskName(String timeTaskName)
    {
        this.timeTaskName = timeTaskName;
    }
}
