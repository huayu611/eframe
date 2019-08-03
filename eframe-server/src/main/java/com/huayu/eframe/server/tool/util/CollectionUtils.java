package com.huayu.eframe.server.tool.util;

import com.huayu.eframe.server.tool.basic.Null;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class CollectionUtils
{

    public static boolean isEmpty(Collection<?> coll)
    {
        return Null.isNull(coll) || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> coll)
    {
        return !isEmpty(coll);
    }

    public static <T> List<T> createArrayList()
    {
        return new ArrayList<T>(128);
    }

    public static <T> T getFirstElement(Collection<T> coll)
    {
        if (isNotEmpty(coll))
        {
            return (T) coll.toArray()[0];
        }
        return null;
    }

    public static <T> List<T> coverArrayToList(T[] t)
    {
        List<T> result = new ArrayList<>();
        if(t.length == 0)
        {
            return result;
        }
        for(T ti : t)
        {
            result.add(ti);
        }
        return result;
    }

    public static int getSize(Collection coll)
    {
        return isEmpty(coll) ? 0 : coll.size();
    }

    public static <Type> void iterator(Collection<Type> coll, CollectionIteratorProcess<Type> process)
    {
        if (isEmpty(coll))
        {
            return;
        }
        Iterator<Type> iterator = coll.iterator();
        int i = 0;
        while (iterator.hasNext())
        {
            Type currentValue = iterator.next();
            process.process(coll, currentValue, i);
            i++;
        }
    }
}
