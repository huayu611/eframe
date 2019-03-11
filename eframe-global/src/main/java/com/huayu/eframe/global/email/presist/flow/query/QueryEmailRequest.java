package com.huayu.eframe.global.email.presist.flow.query;

import com.huayu.eframe.server.common.restful.PagingRequest;

/**
 * Created by Leo on 2019/3/10.
 */
public class QueryEmailRequest
{
    private PagingRequest page;

    private String name;

    private String emailCode;

    public PagingRequest getPage()
    {
        return page;
    }

    public void setPage(PagingRequest page)
    {
        this.page = page;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmailCode()
    {
        return emailCode;
    }

    public void setEmailCode(String emailCode)
    {
        this.emailCode = emailCode;
    }
}
