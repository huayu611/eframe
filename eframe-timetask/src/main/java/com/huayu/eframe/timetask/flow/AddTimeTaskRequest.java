package com.huayu.eframe.timetask.flow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huayu.eframe.flow.annotation.EFrameRequest;

import java.util.Date;

/**
 * Created by Leo on 2019/4/15.
 */
public class AddTimeTaskRequest
{

    @EFrameRequest(bean = "_e_set_random_value(timetask_)")
    @JsonIgnore
    private String timeTaskCode;

    @EFrameRequest(required = true,length = 64)
    private String timeTaskName;

    @EFrameRequest(bean = "_e_defaultNow")
    private Date effectiveTime;

    @EFrameRequest(bean = "_e_defaultExpireTime")
    private Date expireTime;

    @EFrameRequest(defaultStrValue = "1",bean = "_dictionary(time-task-status)")
    private String status;

    private Integer cycle;

    @EFrameRequest(bean = "_dictionary(time-task-unit)")
    private String unit;

    @EFrameRequest(required = true,length = 128)
    private String service;

    @EFrameRequest(required = true,bean = "_dictionary(time-task-type)")
    private String timeTaskType;

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

    public void setTimeTaskType(String timeTaskType)
    {
        this.timeTaskType = timeTaskType;
    }

    public String getService()
    {
        return service;
    }

    public void setService(String service)
    {
        this.service = service;
    }
}
