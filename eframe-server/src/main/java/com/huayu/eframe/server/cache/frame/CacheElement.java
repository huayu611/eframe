package com.huayu.eframe.server.cache.frame;

import com.huayu.eframe.server.tool.util.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class CacheElement<T>
{
    private Index<T> index;

    private Map<String, List<T>> value;

    private ReentrantLock lock = new ReentrantLock();

    public CacheElement(Index<T> index)
    {
        this.index = index;
        value = new HashMap<>();
    }

    public void putValue(T t)
    {
        lock.lock();
        String key = index.getIndex(t);

        List indexValue = value.get(key);
        if (null == indexValue)
        {
            indexValue = new ArrayList();
            value.put(key, indexValue);
        }
        indexValue.add(t);
        lock.unlock();
    }

    public List<T> getValue(T t)
    {
        String key = index.getIndex(t);
        return value.get(key);
    }

    public void cleanCache()
    {
        if (MapUtils.isNotEmpty(value))
        {
            value.clear();
        }
    }
}
