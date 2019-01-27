package com.huayu.eframe.server.dict.reader;

import com.huayu.eframe.server.service.spring.BeanPool;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Created by Leo on 2019/1/7.
 */
public class DictLangService
{
    public static String getDictName(String code, Object[] param, Locale locale)
    {
        MessageSource ms = BeanPool.getService("DictConfigurature");
        return ms.getMessage(code,param,locale);
    }
}
