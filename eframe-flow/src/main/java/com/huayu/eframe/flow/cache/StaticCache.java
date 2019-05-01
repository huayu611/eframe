package com.huayu.eframe.flow.cache;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.tool.basic.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/12.
 */
public class StaticCache
{
    private static Map<String, Map<String, EFrameRequest>> eframeRequestAnnoStaticCache;

    private static Object eframeRequestAnnoStaticCacheLock = new Object();

    public static Map<String, EFrameRequest> getAnnoRequest(Class classKey)
    {
        String key = classKey.getName();
        if (null == eframeRequestAnnoStaticCache)
        {
            return null;
        }
        return eframeRequestAnnoStaticCache.get(key);
    }

    public static void putAnnoRequest(Class classKey, String field, EFrameRequest eframeRequest)
    {
        String key = classKey.getName();
        synchronized (eframeRequestAnnoStaticCacheLock)
        {
            if (null == eframeRequestAnnoStaticCache)
            {
                eframeRequestAnnoStaticCache = new HashMap<>();
            }
            Map<String, EFrameRequest> keycache = eframeRequestAnnoStaticCache.get(key);
            if (null == keycache)
            {
                eframeRequestAnnoStaticCache.put(key, new HashMap<>());
            }
            if (StringUtils.isNullOrEmpty(field) || null == eframeRequest)
            {
                return;
            }
            eframeRequestAnnoStaticCache.get(key).put(field, eframeRequest);

        }
    }


}
