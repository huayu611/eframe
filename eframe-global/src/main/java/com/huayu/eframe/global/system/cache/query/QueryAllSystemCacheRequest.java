package com.huayu.eframe.global.system.cache.query;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/10/18.
 */
public class QueryAllSystemCacheRequest
{
    @EFrameRequest(length = 128)
    private String cacheName;


    public String getCacheName()
    {
        return cacheName;
    }

    public void setCacheName(String cacheName)
    {
        this.cacheName = cacheName;
    }
}
