package com.huayu.eframe.server.common.i18n.table.service;

public interface LangService
{

    LangValue addLangValue(LangValue langValue);

    LangValue batchAddLangValue(LangValue langValue);

    LangValue batchModifyLangValue(LangValue langValue);

    LangValue updateLangValue(LangValue langValue);

    String removeLangValueByCode(String primaryCode);


}
