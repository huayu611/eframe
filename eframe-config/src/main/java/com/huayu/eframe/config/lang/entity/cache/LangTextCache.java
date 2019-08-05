package com.huayu.eframe.config.lang.entity.cache;

import com.huayu.eframe.config.lang.entity.service.LangTextValue;

import java.util.List;

/**
 * Created by Leo on 2018/9/2.
 */
public interface LangTextCache
{
    String CACHE_NAME = "SYS_LANG_TEXT";

    LangTextValue getLangTextByLangCode(String langCode);

    List<LangTextValue> queryAll();

    String getLangTextByLangCodeAndLanguage(String langCode, String langDef);

}
