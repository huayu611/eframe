package com.huayu.eframe.server.cache;

import com.huayu.eframe.server.service.spring.BeanPool;

public class CacheConfigurationExecute
{
    public static void putElementToCache(String cache, Object obj)
    {
        CacheProvider prover = BeanPool.getService("cacheProvider");
        prover.setElementToCache(cache, obj);
    }

    public static Object getElementFromCache(String cache)
    {
        CacheProvider prover = BeanPool.getService("cacheProvider");
        return prover.getElementToCache(cache);
    }

    public static boolean isExistCache(String cacheKey)
    {
        CacheProvider prover = BeanPool.getService("cacheProvider");
        return prover.isExistCache(cacheKey);
    }
}
