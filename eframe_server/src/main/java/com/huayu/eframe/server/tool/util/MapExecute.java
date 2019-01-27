package com.huayu.eframe.server.tool.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapExecute
{
    public static void execut(Map<Object, Object> map, Execute e)
    {
        if (MapUtils.isEmpty(map))
        {
            return;
        }

        Set<Map.Entry<Object, Object>> set = map.entrySet();
        Iterator<Map.Entry<Object, Object>> iterator = set.iterator();
        while (iterator.hasNext())
        {
            Map.Entry<Object, Object> entry = iterator.next();
            Object key = entry.getKey();
            Object value = entry.getKey();
            e.mapProcess(key, value);
        }
    }

}
