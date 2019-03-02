package com.huayu.eframe.server.service.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/7/7.
 */
@Service
public class SpringBootStartBeanPool implements ApplicationContextAware
{

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        BeanInpouring.setApplicationContext(applicationContext);
    }
}
