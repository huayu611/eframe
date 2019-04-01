package com.huayu.eframe.server.common.found;

import java.util.Date;

/**
 * 当前操作实体，里面包含id,code,类型，已经当前时间。
 * Created by Leo on 2019/4/1.
 */
public class CurrentEntity
{
    private Long id;

    private String code;

    private String type;

    private Date now;

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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Date getNow()
    {
        return now;
    }

    public void setNow(Date now)
    {
        this.now = now;
    }
}
