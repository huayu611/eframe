package com.huayu.eframe.timetask.flow;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/4/15.
 */
public class DeleteTimeTaskRequest
{
    @EFrameRequest(required = true, length = 128)
    private String timeTaskCode;

    public String getTimeTaskCode()
    {
        return timeTaskCode;
    }

    public void setTimeTaskCode(String timeTaskCode)
    {
        this.timeTaskCode = timeTaskCode;
    }
}
