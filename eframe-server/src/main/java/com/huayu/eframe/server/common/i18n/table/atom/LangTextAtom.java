package com.huayu.eframe.server.common.i18n.table.atom;

import com.huayu.eframe.server.common.i18n.table.bo.Lang;
import com.huayu.eframe.server.common.i18n.table.bo.LangDefine;
import com.huayu.eframe.server.common.i18n.table.bo.LangText;

import java.util.List;

/**
 * Created by Leo on 2018/9/8.
 */
public interface LangTextAtom
{
    List<LangText> batchAddLangText(List<LangText> lang);

    List<LangText> batchUpdateLangText(List<LangText> lang);

    List<LangText> queryLangTextByDefine(LangDefine langDefine);

    List<LangText> queryLangTextByCode(String fCode);

    List<LangText> queryLangText();

    void batchRemoveLangValue(List<LangText> lang);
}
