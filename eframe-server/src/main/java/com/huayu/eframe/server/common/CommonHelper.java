package com.huayu.eframe.server.common;

import com.huayu.eframe.server.config.properties.SystemConfig;

import java.util.Locale;

/**
 * Created by Leo on 2019/1/7.
 */
public class CommonHelper
{

    public static final String SYSTEM_ENV_LANGUAGE = "sys_staff_lang_default";

    public static final String DEFAULT_SYSTEM_ENV_LANGUAGE = "zh";


    public static Locale getDefaultLocal()
    {
        String langConfig = SystemConfig.getValue(SYSTEM_ENV_LANGUAGE, DEFAULT_SYSTEM_ENV_LANGUAGE);
        return new Locale(langConfig);
    }
}

