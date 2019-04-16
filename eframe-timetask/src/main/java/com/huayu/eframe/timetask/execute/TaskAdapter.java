package com.huayu.eframe.timetask.execute;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leo on 2019/4/15.
 */
public abstract class TaskAdapter implements Task
{

    private static Map<Task,String> storeWitheBeanMap = new HashMap<>();

    private Object obj = new Object();

    @Override
    public String getName()
    {
        return storeWitheBeanMap.get(this);
    }

    @Override
    public void setBeanName(String name)
    {
        synchronized (obj)
        {
            storeWitheBeanMap.put(this,name);
        }
    }



}
