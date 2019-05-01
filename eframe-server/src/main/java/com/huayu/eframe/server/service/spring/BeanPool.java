package com.huayu.eframe.server.service.spring;

public class BeanPool
{
    public static boolean containBean(String bean)
    {
        return BeanInpouring.getApplicationContext().containsBean(bean);
    }

    public static <T> T getService(String bean)
    {
        if (containBean(bean))
        {
            return (T) BeanInpouring.getApplicationContext().getBean(bean);
        }
        return null;
    }

    public static <T> T getService(Class clazz)
    {
        return (T) BeanInpouring.getApplicationContext().getBean(clazz);
    }


    public static <T> T getServiceByClass(Class<T> clazz)
    {
        if (null == BeanInpouring.getApplicationContext())
        {
            return null;
        }
        return BeanInpouring.getApplicationContext().getBean(clazz);
    }
}
