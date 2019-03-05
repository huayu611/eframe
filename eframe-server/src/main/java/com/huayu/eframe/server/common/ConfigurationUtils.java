package com.huayu.eframe.server.common;

import com.huayu.eframe.server.config.properties.SystemConfig;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Leo on 2019/2/3.
 */
public class ConfigurationUtils
{
    private static final String MULTI_VALUE_ATTRIBUTE_DELIMITERS = ",; ";


    private final static String DEFAULT_HTTP_ERROR_STATUS_PARAM = "sys_resultful_error_default_http_status";
    private final static String DEFAULT_HTTP_ERROR_STATUS_DEFAULT = "400";

    private final static String RECORD_OPERATOR_LOG_PARAMETER = "sys_record_operator_log_method";
    private final static String DEFAULT_OPERATOR_LOG_METHOD = "PUT,POST,DELETE";


    private static final String SYSTEM_ENV_LANGUAGE = "sys_staff_lang_default";
    private static final String DEFAULT_SYSTEM_ENV_LANGUAGE = "zh";


    public static String getDefaultHttpErrorStatus()
    {
        String defaultValue = SystemConfig.getValue(DEFAULT_HTTP_ERROR_STATUS_PARAM, DEFAULT_HTTP_ERROR_STATUS_DEFAULT);
        return defaultValue;
    }

    public static List<String> getRecordLogMethod()
    {
        List<String> result = new ArrayList<>();
        String defaultValue = SystemConfig.getValue(RECORD_OPERATOR_LOG_PARAMETER, DEFAULT_OPERATOR_LOG_METHOD);
        String[] nameArr = StringUtils.tokenizeToStringArray(defaultValue, MULTI_VALUE_ATTRIBUTE_DELIMITERS);
        if(nameArr.length<=0)
        {
            return new ArrayList<>();
        }
        for(String value : nameArr)
        {
            result.add(value);
        }
        return result;
    }

    public static Locale getDefaultLocal()
    {
        String langConfig = SystemConfig.getValue(SYSTEM_ENV_LANGUAGE, DEFAULT_SYSTEM_ENV_LANGUAGE);
        if(LanguageCode.existLanguageCode(langConfig))
        {
            return new Locale(langConfig);
        }
        return new Locale(DEFAULT_SYSTEM_ENV_LANGUAGE);
    }

}
