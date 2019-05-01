package com.huayu.eframe.server.service.spring;

import org.springframework.context.ApplicationContext;

public class BeanInpouring
{

    private static ApplicationContext applicationContext = null;

    public static void setApplicationContext(ApplicationContext applicationContext)
    {
        BeanInpouring.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext()
    {
        return BeanInpouring.applicationContext;
    }
}
