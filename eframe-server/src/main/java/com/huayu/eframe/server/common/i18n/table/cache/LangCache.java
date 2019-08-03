package com.huayu.eframe.server.common.i18n.table.cache;

import com.huayu.eframe.server.common.i18n.table.service.LangValue;

import java.util.List;

/**
 * Created by Leo on 2018/9/2.
 */
public interface LangCache
{
    String CACHE_NAME = "SYS_LANG";

    LangValue getLangByLangCode(String langCode);

    List<LangValue> queryAll();

    String getLangValueByLangCodeAndLanguage(String langCode,String langDef);

}
