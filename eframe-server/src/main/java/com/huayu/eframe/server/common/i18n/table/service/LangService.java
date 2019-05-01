package com.huayu.eframe.server.common.i18n.table.service;

import com.huayu.eframe.server.common.i18n.table.bo.LangValue;

import java.util.List;

public interface LangService
{

    List<LangValue> save(String langCode, List<LangValue> values);

    List<LangValue> update(String langCode, List<LangValue> values);

    void delete(String langCode);


}
