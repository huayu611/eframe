package com.huayu.eframe.config.lang.utils;

import com.huayu.eframe.config.lang.entity.cache.LangDefineCache;
import com.huayu.eframe.config.lang.entity.cache.LangTextCache;
import com.huayu.eframe.config.lang.entity.cache.LangValueCache;
import com.huayu.eframe.config.lang.entity.service.LangDefineDetail;
import com.huayu.eframe.server.common.Constant;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.StringUtils;

/**
 * Created by Leo on 2019/8/5.
 */
public class MultiLanguageHelper
{

    public final static String DEFAULT_EMPTY = StringUtils.BLANK;

    private static String getRequestHeaderLanguage()
    {
        String language = LocalAttribute.getValue(Constant.HEAD_CUSTOMER_REQUEST_LANGUAGE);
        if(StringUtils.isNullOrEmpty(language))
        {
            return "zh";
        }
        return language;
    }

    public static String getLangValueByLangAndCodeWithSystemLanguage(String langCode)
    {
        String language = getRequestHeaderLanguage();
        return getLangValueByLangAndCode(langCode,language);
    }


    public static String getLangTextByLangAndCodeWithSystemLanguage(String langCode)
    {
        String language = getRequestHeaderLanguage();
        return getLangTextByLangAndCode(langCode,language);
    }


    public static String getLangValueByLangAndCodeWithSystemLanguage(String langCode,String defaultValue)
    {
        String language = getRequestHeaderLanguage();
        return getLangValueByLangAndCode(langCode,language,defaultValue);
    }


    public static String getLangTextByLangAndCodeWithSystemLanguage(String langCode,String defaultValue)
    {
        String language = getRequestHeaderLanguage();
        return getLangTextByLangAndCode(langCode,language,defaultValue);
    }


    public static String getLangValueByLangAndCode(String langCode,String language)
    {
        return getLangValueByLangAndCode(langCode,language,DEFAULT_EMPTY);
    }

    public static String getLangTextByLangAndCode(String langCode,String language)
    {
        return getLangTextByLangAndCode(langCode,language,DEFAULT_EMPTY);
    }


    public static String getLangValueByLangAndCode(String langCode,String language,String defaultValue)
    {
        LangValueCache langValueCache = BeanPool.getService(LangValueCache.class);
        if(null == langValueCache)
        {
            return defaultValue;
        }
        if(!checkLanguageDefine(language))
        {
            return defaultValue;
        }
        return langValueCache.getLangValueByLangCodeAndLanguage(langCode,language);
    }

    public static String getLangTextByLangAndCode(String langCode,String language,String defaultValue)
    {
        LangTextCache langTextCache = BeanPool.getService(LangTextCache.class);
        if(null == langTextCache)
        {
            return defaultValue;
        }
        if(!checkLanguageDefine(language))
        {
            return defaultValue;
        }
        return langTextCache.getLangTextByLangCodeAndLanguage(langCode,language);
    }

    public static boolean checkLanguageDefine(String language)
    {
        if(StringUtils.isNullOrEmpty(language))
        {
            return false;
        }
        LangDefineCache langValueCache = BeanPool.getService(LangDefineCache.class);
        LangDefineDetail langDef = langValueCache.queryLanguageByCode(language);
        return null != langDef;
    }
}
