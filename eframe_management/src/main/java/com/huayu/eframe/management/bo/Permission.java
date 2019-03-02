package com.huayu.eframe.management.bo;

import com.huayu.eframe.server.common.standard.ValidTime;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2018/7/14.
 */
@Entity
@Table(name="SYS_PERMISSION")
public class Permission implements ValidTime
{
    @Id
    @Column(name="PERMISSION_ID", length=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="PERMISSION_URL", length=128)
    private String url;

    @Column(name="PERMISSION_NAME", length=32)
    private String permissionName;

    @Column(name="PERMISSION_TYPE", length=1)
    private String permissionType;

    @Column(name="PERMISSION_CODE", length=32)
    private String permissionCode;

    @Column(name="PERMISSION_METHOD", length=16)
    private String method;

    @Column(name="WHITE", length=1)
    private String white;

    @Column(name="CREATE_TIME")
    private Date createTime;

    @Column(name="EFFECTIVE_TIME")
    private Date effectiveTime;

    @Column(name="EXPIRE_TIME")
    private Date expireTime;

    @Column(name="STATUS", length=2)
    private String status;

    @Column(name="LAST_UPDATE_TIME")
    private Date lastUpdateTime;

    public String getWhite()
    {
        return white;
    }
    public void setWhite(String white)
    {
        this.white = white;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getPermissionName()
    {
        return permissionName;
    }

    public void setPermissionName(String permissionName)
    {
        this.permissionName = permissionName;
    }

    public String getPermissionType()
    {
        return permissionType;
    }

    public void setPermissionType(String permissionType)
    {
        this.permissionType = permissionType;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
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

    public String getPermissionCode()
    {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode)
    {
        this.permissionCode = permissionCode;
    }
}
