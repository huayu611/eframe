package com.huayu.eframe.server.service.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Created by Leo on 2018/9/25.
 */
@Configuration
public class ExceptionConfigurature
{
    @Bean("ExceptionMessageResource")
    public MessageSource getExceptionResource()
    {
        ReloadableResourceBundleMessageSource surce =  new ReloadableResourceBundleMessageSource();
        surce.setBasename("classpath:META-INF/exception/*_eframe_exception");
        surce.setDefaultEncoding("UTF-8");
        return surce;
    }

}
