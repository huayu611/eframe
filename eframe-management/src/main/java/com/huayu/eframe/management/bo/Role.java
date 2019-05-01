package com.huayu.eframe.management.bo;

import com.huayu.eframe.server.common.standard.ValidTime;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by Administrator on 2018/7/14.
 */
@Entity
@Table(name = "SYS_ROLE")
public class Role implements ValidTime
{
    @Id
    @Column(name = "ROLE_ID", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROLE_NAME", length = 30)
    private String name;

    @Column(name = "ROLE_CODE", length = 30)
    private String roleCode;

    @Column(name = "PARENT_ROLE_ID", length = 20)
    private Long parentRoleId;

    @Column(name = "TOP_ROLE_ID")
    private Long topRoleId;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "EFFECTIVE_TIME")
    private Date effectiveTime;

    @Column(name = "EXPIRE_TIME")
    private Date expireTime;

    @Column(name = "STATUS", length = 2)
    private String status;

    @Column(name = "LAST_UPDATE_TIME")
    private Date lastUpdateTime;

    @Column(name = "REMARK", length = 2048)
    private String remark;


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getParentRoleId()
    {
        return parentRoleId;
    }

    public void setParentRoleId(Long parentRoleId)
    {
        this.parentRoleId = parentRoleId;
    }

    public Long getTopRoleId()
    {
        return topRoleId;
    }

    public void setTopRoleId(Long topRoleId)
    {
        this.topRoleId = topRoleId;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
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

    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getRoleCode()
    {
        return roleCode;
    }

    public void setRoleCode(String roleCode)
    {
        this.roleCode = roleCode;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}
