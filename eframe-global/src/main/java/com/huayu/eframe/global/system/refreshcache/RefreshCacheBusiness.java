package com.huayu.eframe.global.system.refreshcache;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.cache.CacheObserver;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/4/10.
 */
@Service
public class RefreshCacheBusiness extends AbstractExecuteBusiness
{
    @Autowired
    private CacheObserver cacheObserver;

    @Override
    public void execute(BusinessParameter param)
    {
        RefreshCacheRequest refreshCacheRequest = param.getRequest();
        String cacheName = refreshCacheRequest.getCacheName();
        if(StringUtils.isNullOrEmpty(cacheName))
        {
            cacheObserver.refreshAllCache();
        }
        else
        {
            cacheObserver.refreshCache(cacheName);
        }
    }

    @Override
    protected boolean isNeedTransaction()
    {
        return false;
    }
}
