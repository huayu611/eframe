package com.huayu.eframe.timetask.entity.bo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Leo on 2020/10/30.
 */
@Entity
@Table(name = "sys_async_order")
public class AsynchronousOrder
{
    @Id
    @Column(name = "ID", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_execute_time")
    private Date lastExecuteTime;

    @Column(name = "execute_times")
    private Integer executeTimes;

    @Column(name = "next_execute_time")
    private Date nextExecuteTimes;

    @Column(name = "execute_beans")
    private String executeBean;

    @Column(name = "last_execute_error")
    private String lastExecuteError;

    @Column(name = "param")
    private String param;

    @Column(name = "status")
    private String status;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getLastExecuteTime()
    {
        return lastExecuteTime;
    }

    public void setLastExecuteTime(Date lastExecuteTime)
    {
        this.lastExecuteTime = lastExecuteTime;
    }

    public Integer getExecuteTimes()
    {
        return executeTimes;
    }

    public void setExecuteTimes(Integer executeTimes)
    {
        this.executeTimes = executeTimes;
    }

    public Date getNextExecuteTimes()
    {
        return nextExecuteTimes;
    }

    public void setNextExecuteTimes(Date nextExecuteTimes)
    {
        this.nextExecuteTimes = nextExecuteTimes;
    }

    public String getExecuteBean()
    {
        return executeBean;
    }

    public void setExecuteBean(String executeBean)
    {
        this.executeBean = executeBean;
    }

    public String getLastExecuteError()
    {
        return lastExecuteError;
    }

    public void setLastExecuteError(String lastExecuteError)
    {
        this.lastExecuteError = lastExecuteError;
    }

    public String getParam()
    {
        return param;
    }

    public void setParam(String param)
    {
        this.param = param;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
