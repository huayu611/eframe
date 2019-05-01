package com.huayu.eframe.server.cache.frame;

/**
 * Created by Leo on 2018/9/2.
 */
public class DefaultIndex implements Index<Object>
{
    @Override
    public String getIndex(Object obj)
    {
        return "ALL";
    }
}
