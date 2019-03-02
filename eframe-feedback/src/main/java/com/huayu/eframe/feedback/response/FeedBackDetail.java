package com.huayu.eframe.feedback.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

/**
 * Created by Leo on 2018/11/24.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FeedBackDetail
{
    private Long id;

    private String ip;

    private String mobile;

    private String content;

    private String email;

    private Long parentId;

    private String sex;

    private String createTime;

    private String name;

    private List<FeedBackDetail> sonList;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public List<FeedBackDetail> getSonList()
    {
        return sonList;
    }

    public void setSonList(List<FeedBackDetail> sonList)
    {
        this.sonList = sonList;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
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
