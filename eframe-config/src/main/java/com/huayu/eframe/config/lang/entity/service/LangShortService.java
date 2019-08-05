package com.huayu.eframe.config.lang.entity.service;

public interface LangShortService
{

    LangShortValue addLangValue(LangShortValue langValue);

    LangShortValue batchAddLangValue(LangShortValue langValue);

    LangShortValue batchModifyLangValue(LangShortValue langValue);

    LangShortValue updateLangValue(LangShortValue langValue);

    String removeLangValueByCode(String primaryCode);


}
