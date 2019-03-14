package com.huayu.eframe.config.parameter.util;

import com.huayu.eframe.config.parameter.cache.ParameterCache;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.StringUtils;

/**
 * Created by Leo on 2018/9/9.
 */
public class ParameterUtil
{
    public static String getParameterValueByCode(String code, String defaultValue)
    {
        String result = getParameterValueByCode(code);
        return StringUtils.isNullOrEmpty(result) ? defaultValue : result;

    }

    public static String getParameterValueByCode(String code)
    {
        ParameterCache cache = BeanPool.getServiceByClass(ParameterCache.class);
        String result = cache.getParameterValue(code);
        return result;
    }
}
