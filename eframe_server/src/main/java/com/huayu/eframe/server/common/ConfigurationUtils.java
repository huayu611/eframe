package com.huayu.eframe.server.common;

import com.huayu.eframe.server.config.properties.SystemConfig;

/**
 * Created by Leo on 2019/2/3.
 */
public class ConfigurationUtils
{
    private final static String DEFAULT_HTTP_ERROR_STATUS_PARAM = "sys_resultful_error_default_http_status";
    private final static String DEFAULT_HTTP_ERROR_STATUS_DEFAULT = "400";

    public static String getDefaultHttpErrorStatus()
    {
        String defaultValue = SystemConfig.getValue(DEFAULT_HTTP_ERROR_STATUS_PARAM, DEFAULT_HTTP_ERROR_STATUS_DEFAULT);
        return defaultValue;
    }
}
