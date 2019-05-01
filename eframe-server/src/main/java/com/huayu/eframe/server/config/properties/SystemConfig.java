package com.huayu.eframe.server.config.properties;

import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.StringUtils;

/**
 * Created by Administrator on 2017/8/20.
 */
public class SystemConfig
{


    public static String getValue(String key)
    {
        PropertiesLoadService propertiesLoadService = BeanPool.getServiceByClass(PropertiesLoadService.class);
        Object obj = propertiesLoadService.getValue(key);
        return StringUtils.getString(obj);
    }

    public static String getValue(String key, String defaultValue)
    {
        PropertiesLoadService propertiesLoadService = BeanPool.getServiceByClass(PropertiesLoadService.class);
        Object obj = propertiesLoadService.getValue(key);
        if (null == obj)
        {
            return defaultValue;
        }
        return StringUtils.getString(obj);
    }

    public static String getValueByService(String key, String defaultValue, PropertiesLoadService propertiesLoadService)
    {
        if (null == propertiesLoadService)
        {
            return defaultValue;
        }
        Object obj = propertiesLoadService.getValue(key);
        if (null == obj)
        {
            return defaultValue;
        }
        return StringUtils.getString(obj);
    }
}
