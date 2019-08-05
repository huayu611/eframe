package com.huayu.eframe.config.lang.entity.cache;

import com.huayu.eframe.config.lang.entity.service.LangShortValue;

import java.util.List;

/**
 * Created by Leo on 2018/9/2.
 */
public interface LangValueCache
{
    String CACHE_NAME = "SYS_LANG_VALUE";

    LangShortValue getLangByLangCode(String langCode);

    List<LangShortValue> queryAll();

    String getLangValueByLangCodeAndLanguage(String langCode,String langDef);

}
