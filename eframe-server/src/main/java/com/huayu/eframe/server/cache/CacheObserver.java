package com.huayu.eframe.server.cache;

import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import com.huayu.eframe.server.tool.util.MapUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Leo on 2018/9/8.
 */
@Service
public class CacheObserver
{

    private static final LogDebug debug = new LogDebug(CacheObserver.class);

    private List<Cache> allCache;

    private Map<String,Cache> allCacheMap;

    public CacheObserver(List<Cache> cacheList)
    {
        if(CollectionUtils.isNotEmpty(cacheList))
        {
            allCache = cacheList;
        }
        else
        {
            allCache = new ArrayList<>();
        }
        debug.log(allCache.size());

        allCacheMap = new ConcurrentHashMap<>();
        for(Cache cache : cacheList)
        {
            allCacheMap.put(cache.cacheName(),cache);
        }
    }

    public List<Cache> getAllCache()
    {
        return allCache;
    }

    public void refreshAllCache()
    {
        if(CollectionUtils.isNotEmpty(allCache))
        {
            Iterator<Cache> iteratorCache = allCache.iterator();
            while(iteratorCache.hasNext())
            {
                Cache cache = iteratorCache.next();
                cache.refresh();
            }
        }
    }

    public void refreshCache(String cacheName)
    {
        if(MapUtils.isNotEmpty(allCacheMap))
        {
            Cache cache = allCacheMap.get(cacheName);
            if(null != cache)
            {
                cache.refresh();
            }
        }
    }


}
