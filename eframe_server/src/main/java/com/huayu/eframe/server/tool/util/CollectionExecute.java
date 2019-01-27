package com.huayu.eframe.server.tool.util;

import java.util.Collection;

public class CollectionExecute
{
    public static void execut(Collection coll,Execute e)
    {
        if(CollectionUtils.isEmpty(coll))
        {
            return;
        }

        for(Object obj : coll)
        {
            e.collectionProcess(obj);
        }

    }

}
