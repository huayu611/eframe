package com.huayu.eframe.server.common.i18n.table.cache;

import com.huayu.eframe.server.common.i18n.table.service.LangTextValue;
import com.huayu.eframe.server.common.i18n.table.service.LangValue;

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
