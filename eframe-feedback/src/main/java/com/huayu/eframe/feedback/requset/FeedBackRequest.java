package com.huayu.eframe.feedback.requset;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.common.restful.PagingRequest;

/**
 * Created by Leo on 2018/11/24.
 */
public class FeedBackRequest
{
    private Long id;

    @EFrameRequest(required = true)
    private String ip;

    @EFrameRequest(required = true)
    private String mobile;

    @EFrameRequest(required = true)
    private String content;

    private String email;

    private Long parentId;

    private String sex;

    private String name;

    private String deleteIds;

    private PagingRequest page;

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

    public PagingRequest getPage()
    {
        return page;
    }

    public void setPage(PagingRequest page)
    {
        this.page = page;
    }

    public String getDeleteIds()
    {
        return deleteIds;
    }

    public void setDeleteIds(String deleteIds)
    {
        this.deleteIds = deleteIds;
    }
}
