package com.huayu.eframe.server.service.exception;


import com.huayu.eframe.server.service.spring.BeanPool;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Created by Administrator on 2017/6/18.
 */
public class ExceptionCacheService
{
    public static String getErrorInfo(String errorCode,Object[] param,Locale locale)
    {
        MessageSource ms = BeanPool.getService("ExceptionMessageResource");
        return ms.getMessage(errorCode,param,locale);
    }
}
