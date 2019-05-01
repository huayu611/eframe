package com.huayu.eframe.server.config.rest;

import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.StringUtils;

/**
 * Created by Leo on 2018/9/8.
 */

public class RestErrorCodeMappingFacade
{
    public static String getHttpCode(String errorCode, String defaultCode)
    {
        HttpErrorCodeMapping mapping = BeanPool.getServiceByClass(HttpErrorCodeMapping.class);
        Object result = mapping.getValue(errorCode);
        if (null == result)
        {
            return defaultCode;
        }
        return StringUtils.getString(result);
    }
}
