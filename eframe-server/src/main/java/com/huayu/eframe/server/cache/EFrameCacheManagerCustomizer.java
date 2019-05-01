package com.huayu.eframe.server.cache;

import com.huayu.eframe.server.log.LogDebug;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class EFrameCacheManagerCustomizer implements CacheManagerCustomizer
{

    private static final LogDebug debug = new LogDebug(EFrameCacheManagerCustomizer.class);

    private CacheManager cacheManager;

    @Override
    public void customize(CacheManager cacheManager)
    {
        this.cacheManager = cacheManager;
    }
}
