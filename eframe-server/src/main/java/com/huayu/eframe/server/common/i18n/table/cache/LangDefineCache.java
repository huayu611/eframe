package com.huayu.eframe.server.common.i18n.table.cache;

import com.huayu.eframe.server.common.i18n.table.service.LangDefineDetail;

import java.util.List;

/**
 * Created by Leo on 2019/7/22.
 */
public interface LangDefineCache
{
    String CACHE_NAME = "SYS_LANG_DEFINE";

    List<LangDefineDetail> queryAllDefineLanguage();

    LangDefineDetail queryLanguageByCode(String langCode);
}
