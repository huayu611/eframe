package com.huayu.eframe.management.request;


import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.common.restful.PagingRequest;

/**
 * Created by Administrator on 2018/7/22.
 */
public class QueryStaffRequest
{
    @EFrameRequest(length = 64)
    private String login;

    @EFrameRequest(length = 64)
    private String name;

    @EFrameRequest(bean = "_dictionary(role_status)")
    private String status;

    private PagingRequest page;


    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
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


}
