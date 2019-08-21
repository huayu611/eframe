package com.huayu.eframe.server.cache.frame;


import com.huayu.eframe.server.cache.Cache;
import com.huayu.eframe.server.tool.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Leo on 2018/9/2.
 */
public abstract class AbstractFrameCache<T> implements Cache<T>
{
    private List<Index> indexList;

    private Map<String, Index> innerListMapping;

    private Map<Index, CacheElement> memberRecord;

    private boolean loaded = false;

    private ReentrantLock lock = new ReentrantLock();


    public AbstractFrameCache()
    {
        indexList = new ArrayList<>();
        innerListMapping = new HashMap<>();
        registerIndex(new DefaultIndex());
    }

    protected void registerIndex(Index index)
    {
        try
        {
            lock.lock();

            indexList.add(index);
            innerListMapping.put(index.getClass().getName(), index);

            createMap();

            memberRecord.put(index, new CacheElement(index));
        }
        finally
        {
            lock.unlock();
        }


    }

    private void read()
    {
        try
        {
            lock.lock();
            if(!loaded)
            {
                List<T> allRecord = load();
                if (CollectionUtils.isEmpty(indexList))
                {
                    return;
                }
                for (Index index : indexList)
                {

                    if (CollectionUtils.isEmpty(allRecord))
                    {
                        continue;
                    }

                    CacheElement cacheElement = memberRecord.get(index);
                    for (T t : allRecord)
                    {
                        cacheElement.putValue(t);
                    }
                }
                loaded = true;
            }

        }
        finally
        {
            lock.unlock();
        }
    }

    private void createMap()
    {
        if (null == memberRecord)
        {
            memberRecord = new HashMap<>();
        }
    }

    protected List<T> getResultByIndex(Class indexClass, T t)
    {
        if (!loaded)
        {
            read();
        }

        Index index = innerListMapping.get(indexClass.getName());

        if (null == index)
        {
            return null;
        }

        CacheElement cacheElement = memberRecord.get(index);
        if (null == cacheElement)
        {
            return null;
        }

        return cacheElement.getValue(t);
    }

    @Override
    public void refresh()
    {
        try
        {
            lock.lock();
            loaded = false;
            Set<Map.Entry<Index, CacheElement>> entrySet = memberRecord.entrySet();
            Iterator<Map.Entry<Index, CacheElement>> iteratorMap = entrySet.iterator();
            while (iteratorMap.hasNext())
            {
                Map.Entry<Index, CacheElement> elements = iteratorMap.next();
                CacheElement element = elements.getValue();
                element.cleanCache();
            }
        }
        finally
        {
            lock.unlock();
        }
    }

}
