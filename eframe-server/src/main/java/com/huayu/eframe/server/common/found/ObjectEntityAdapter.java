package com.huayu.eframe.server.common.found;

import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/3/25.
 */
@Service
public class ObjectEntityAdapter implements ObjectEntity
{
    @Override
    public String getName()
    {
        return this.getClass().getName();
    }

    @Override
    public Object getEntity(String code)
    {
        return null;
    }

    @Override
    public Object getEntity(Long id)
    {
        return null;
    }

    @Override
    public String getCode(Long id)
    {
        return null;
    }

    @Override
    public Long getId(String code)
    {
        return null;
    }
}
