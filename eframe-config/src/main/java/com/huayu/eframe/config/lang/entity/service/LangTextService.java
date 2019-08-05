package com.huayu.eframe.config.lang.entity.service;

/**
 * Created by Leo on 2019/8/3.
 */
public interface LangTextService
{

    LangTextValue batchAddLangValue(LangTextValue langValue);

    LangTextValue batchModifyLangValue(LangTextValue langValue);

    String removeLangValueByCode(String primaryCode);
}
