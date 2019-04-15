package com.huayu.eframe.timetask.entity.service;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Created by Leo on 2019/4/15.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TimeTaskDetail
{
    private String timeTaskCode;

    private String timeTaskName;

    private Date effectiveTime;

    private Date expireTime;

    private String status;

    private Integer cycle;

    private String unit;

    private String timeTaskType;

    private String service;

    private Date nextTime;

    private Integer count;

    public String getTimeTaskCode()
    {
        return timeTaskCode;
    }

    public void setTimeTaskCode(String timeTaskCode)
    {
        this.timeTaskCode = timeTaskCode;
    }

    public String getTimeTaskName()
    {
        return timeTaskName;
    }

    public void setTimeTaskName(String timeTaskName)
    {
        this.timeTaskName = timeTaskName;
    }

    public Date getEffectiveTime()
    {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime)
    {
        this.effectiveTime = effectiveTime;
    }

    public Date getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(Date expireTime)
    {
        this.expireTime = expireTime;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Integer getCycle()
    {
        return cycle;
    }

    public void setCycle(Integer cycle)
    {
        this.cycle = cycle;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getTimeTaskType()
    {
        return timeTaskType;
    }

    public String getService()
    {
        return service;
    }

    public void setService(String service)
    {
        this.service = service;
    }

    public void setTimeTaskType(String timeTaskType)
    {
        this.timeTaskType = timeTaskType;
    }

    public Date getNextTime()
    {
        return nextTime;
    }

    public void setNextTime(Date nextTime)
    {
        this.nextTime = nextTime;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }
}
