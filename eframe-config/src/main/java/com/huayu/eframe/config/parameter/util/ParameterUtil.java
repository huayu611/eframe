package com.huayu.eframe.config.parameter.util;

import com.huayu.eframe.config.parameter.cache.ParameterCache;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.StringUtils;

/**
 * Created by Leo on 2018/9/9.
 */
public class ParameterUtil
{
    private static final LogDebug debug = new LogDebug(ParameterUtil.class);

    public static String getParameterValueByCode(String code, String defaultValue)
    {
        String result = getParameterValueByCode(code);
        debug.log(result);
        return StringUtils.isNullOrEmpty(result) ? defaultValue : result;

    }

    public static String getParameterValueByCode(String code)
    {
        ParameterCache cache = BeanPool.getServiceByClass(ParameterCache.class);
        String result = cache.getParameterValue(code);
        debug.log(result);
        return result;
    }
}
