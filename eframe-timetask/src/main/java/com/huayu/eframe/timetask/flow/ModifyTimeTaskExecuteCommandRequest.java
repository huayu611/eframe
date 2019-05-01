package com.huayu.eframe.timetask.flow;

import com.huayu.eframe.flow.annotation.EFrameRequest;

import java.util.Date;

/**
 * Created by Leo on 2019/4/15.
 */
public class ModifyTimeTaskExecuteCommandRequest
{
    @EFrameRequest(required = true, length = 128)
    private String timeTaskCode;

    @EFrameRequest(required = true)
    private Date nextTime;

    public String getTimeTaskCode()
    {
        return timeTaskCode;
    }

    public void setTimeTaskCode(String timeTaskCode)
    {
        this.timeTaskCode = timeTaskCode;
    }

    public Date getNextTime()
    {
        return nextTime;
    }

    public void setNextTime(Date nextTime)
    {
        this.nextTime = nextTime;
    }
}
