package com.huayu.eframe.server.cache.frame;

import com.huayu.eframe.server.tool.util.CollectionUtils;
import com.huayu.eframe.server.tool.util.MapUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Leo on 2019/10/18.
 */
@Service
public class AbstractFrameType
{
    private List<AbstractFrameCache> ret = null;

    public AbstractFrameType(List<AbstractFrameCache> abstractFrameCaches)
    {
        ret = abstractFrameCaches;
    }

    public Map<String,Object> getAllCache()
    {
        Map<String,Object> queryResult = new HashMap<>();
        CollectionUtils.iterator(ret,(c,v,i)->{
            List allRecord = v.getCacheElement();
            queryResult.put(v.cacheName(),allRecord);

        });
        return queryResult;
    }

    public Object getAllCacheByName(String name)
    {
        List queryResult = new ArrayList();
        CollectionUtils.iterator(ret,(c,v,i)->{
            if(name.equals(v.cacheName()))
            {
                queryResult.add(v.getCacheElement());
            }
        });
        return CollectionUtils.getFirstElement(queryResult);
    }
}
