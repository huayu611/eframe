package com.huayu.eframe.server.common.i18n.table.cache;

import com.huayu.eframe.server.common.i18n.table.bo.LangValue;

import java.util.List;

/**
 * Created by Leo on 2018/9/2.
 */
public interface LangCache
{
    List<LangValue> getLangByLangCode(String langCode);

    LangValue getLangByLangCodeAndLanguage(String langCode, String language);
}
