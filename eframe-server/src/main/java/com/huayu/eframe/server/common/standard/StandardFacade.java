package com.huayu.eframe.server.common.standard;

import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Leo on 2018/9/18.
 */
public class StandardFacade
{

    private static final LogDebug debug = new LogDebug(StandardFacade.class);

    public static <T extends ValidTime> List<T> getValidItem(List<T> item)
    {

        if(CollectionUtils.isEmpty(item))
        {
            return item;
        }
        Date now = LocalAttribute.getNow();
        List<T> newItemList = new ArrayList<>();
        for(T t : item)
        {
           if(t.getEffectiveTime() == null || t.getExpireTime() == null)
           {
               continue;
           }
           if(t.getExpireTime().after(now))
           {
               newItemList.add(t);
           }
        }
        return newItemList;
    }

    public static <T> List<T>  getPagingList(List<T> item,int page,int size)
    {
        if(CollectionUtils.isEmpty(item))
        {
            return item;
        }
        int allCount = item.size();
        int allPage = allCount / size;
        int startPage = page>allPage + 1 ? allPage + 1 : page;
        int startNumber = (startPage-1) * size;
        int endNumber = startNumber + size > allCount ?allCount :startNumber + size ;
        List<T> result = item.subList(startNumber, endNumber);
        return result;
    }
}
