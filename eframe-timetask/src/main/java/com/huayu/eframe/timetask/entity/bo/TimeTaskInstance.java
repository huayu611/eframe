package com.huayu.eframe.timetask.entity.bo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Leo on 2019/4/15.
 */
@Entity
@Table(name="sys_timetask_instance")
public class TimeTaskInstance
{
    @Id
    @Column(name="ID", length=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="time_task_id", length=20)
    private Long timeTaskId;

    @Column(name="start_time")
    private Date startTime;

    @Column(name="end_time")
    private Date endTime;

    @Column(name="status")
    private String status;

    @Column(name="error_stack")
    private String errorStack;

    @Column(name="retry_time")
    private Integer retryTime;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getTimeTaskId()
    {
        return timeTaskId;
    }

    public void setTimeTaskId(Long timeTaskId)
    {
        this.timeTaskId = timeTaskId;
    }

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

    public Integer getRetryTime()
    {
        return retryTime;
    }

    public void setRetryTime(Integer retryTime)
    {
        this.retryTime = retryTime;
    }
}
