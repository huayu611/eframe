package com.huayu.eframe.timetask.entity.service;

import java.util.Date;

/**
 * Created by Leo on 2019/4/15.
 */
public class TimeTaskInstanceDetail
{
    private Date startTime;

    private Date endTime;

    private String status;

    private String statusName;

    private String errorStack;

    private String timeTaskCode;

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getErrorStack()
    {
        return errorStack;
    }

    public void setErrorStack(String errorStack)
    {
        this.errorStack = errorStack;
    }

    public String getTimeTaskCode()
    {
        return timeTaskCode;
    }

    public void setTimeTaskCode(String timeTaskCode)
    {
        this.timeTaskCode = timeTaskCode;
    }

    public String getStatusName()
    {
        return statusName;
    }

    public void setStatusName(String statusName)
    {
        this.statusName = statusName;
    }
}
