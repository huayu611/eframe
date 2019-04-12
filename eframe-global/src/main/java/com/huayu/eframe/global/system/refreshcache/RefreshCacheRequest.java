package com.huayu.eframe.global.system.refreshcache;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/4/10.
 */
public class RefreshCacheRequest
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
