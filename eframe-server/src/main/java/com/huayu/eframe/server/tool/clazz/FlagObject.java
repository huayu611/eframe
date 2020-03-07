package com.huayu.eframe.server.tool.clazz;

/**
 * 工具类。适用于final类，可以用于lamda时对象无法final情况
 * Created by Leo on 2020/3/7.
 */
public class FlagObject<T>
{
    private T value;

    public FlagObject()
    {

    }

    public FlagObject(T t)
    {
        this.value = t;
    }

    public T getValue()
    {
        return value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }

    public boolean isExist()
    {
        return null != value;
    }
}
