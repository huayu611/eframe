package com.huayu.eframe.config.lang.entity.cache;

import com.huayu.eframe.config.lang.entity.service.LangDefineDetail;

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
