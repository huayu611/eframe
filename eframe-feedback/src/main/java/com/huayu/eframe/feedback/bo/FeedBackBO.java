package com.huayu.eframe.feedback.bo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Leo on 2018/11/24.
 */
@Entity
@Table(name="SYS_FEEDBACK")
public class FeedBackBO
{
    @Id
    @Column(name="ID", length=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="FD_IP", length=32)
    private String fdIp;

    @Column(name="FD_PARENT_ID", length=32)
    private Long fdParentId;

    @Column(name="FD_USER_SEX", length=1)
    private String fdStaffSex;

    @Column(name="FD_USER_EMAIL", length=64)
    private String fdStaffEmail;

    @Column(name="FD_USER_PHONENUMBER", length=32)
    private String fdStaffPhone;

    @Column(name="FD_USER_CONTENT", length=1024)
    private String fdStaffContent;

    @Column(name="FD_CREATE_TIME")
    private Date createTime;

    @Column(name="FD_NAME", length=1024)
    private String name;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFdIp()
    {
        return fdIp;
    }

    public void setFdIp(String fdIp)
    {
        this.fdIp = fdIp;
    }

    public String getFdStaffSex()
    {
        return fdStaffSex;
    }

    public void setFdStaffSex(String fdStaffSex)
    {
        this.fdStaffSex = fdStaffSex;
    }

    public String getFdStaffEmail()
    {
        return fdStaffEmail;
    }

    public void setFdStaffEmail(String fdStaffEmail)
    {
        this.fdStaffEmail = fdStaffEmail;
    }

    public String getFdStaffPhone()
    {
        return fdStaffPhone;
    }

    public void setFdStaffPhone(String fdStaffPhone)
    {
        this.fdStaffPhone = fdStaffPhone;
    }

    public String getFdStaffContent()
    {
        return fdStaffContent;
    }

    public void setFdStaffContent(String fdStaffContent)
    {
        this.fdStaffContent = fdStaffContent;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Long getFdParentId()
    {
        return fdParentId;
    }

    public void setFdParentId(Long fdParentId)
    {
        this.fdParentId = fdParentId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
