package com.huayu.eframe.timetask.entity.bo;

import com.huayu.eframe.server.common.standard.ValidTime;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Leo on 2019/4/15.
 */
@Entity
@Table(name="SYS_TIMETASK")
public class TimeTaskBO implements ValidTime
{
    @Id
    @Column(name="ID", length=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @Column(name="service_bean")
    private String serviceBean;

    @Column(name="effective_time")
    private Date effectiveTime;

    @Column(name="expire_time")
    private Date expireTime;

    @Column(name="status")
    private String status;

    @Column(name="cycle")
    private Integer cycle;

    @Column(name="unit")
    private String unit;

    @Column(name="execute_count")
    private Integer count;

    @Column(name="next_execute_time")
    private Date nextTime;

    @Column(name="timetask_type")
    private String timeTaskType;

    @Column(name="LAST_UPDATE_TIME")
    private Date lastUpdateTime;

    @Column(name="CREATE_OBJ_ID", length=20)
    private Long createObjId;

    @Column(name="CREATE_OBJ_TYPE", length=128)
    private String createObjType;

    @Column(name="LAST_UPDATE_OBJ_ID", length=20)
    private Long lastUpdateObjId;

    @Column(name="LAST_UPDATE_OBJ_TYPE", length=128)
    private String lastUpdateObjType;

    @Column(name="CREATE_TIME")
    private Date createTime;

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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getServiceBean()
    {
        return serviceBean;
    }

    public void setServiceBean(String serviceBean)
    {
        this.serviceBean = serviceBean;
    }

    @Override
    public Date getEffectiveTime()
    {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime)
    {
        this.effectiveTime = effectiveTime;
    }

    @Override
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

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public Date getNextTime()
    {
        return nextTime;
    }

    public void setNextTime(Date nextTime)
    {
        this.nextTime = nextTime;
    }

    public String getTimeTaskType()
    {
        return timeTaskType;
    }

    public void setTimeTaskType(String timeTaskType)
    {
        this.timeTaskType = timeTaskType;
    }

    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getCreateObjId()
    {
        return createObjId;
    }

    public void setCreateObjId(Long createObjId)
    {
        this.createObjId = createObjId;
    }

    public String getCreateObjType()
    {
        return createObjType;
    }

    public void setCreateObjType(String createObjType)
    {
        this.createObjType = createObjType;
    }

    public Long getLastUpdateObjId()
    {
        return lastUpdateObjId;
    }

    public void setLastUpdateObjId(Long lastUpdateObjId)
    {
        this.lastUpdateObjId = lastUpdateObjId;
    }

    public String getLastUpdateObjType()
    {
        return lastUpdateObjType;
    }

    public void setLastUpdateObjType(String lastUpdateObjType)
    {
        this.lastUpdateObjType = lastUpdateObjType;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
}
