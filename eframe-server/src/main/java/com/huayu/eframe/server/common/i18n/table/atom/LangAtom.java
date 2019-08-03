package com.huayu.eframe.server.common.i18n.table.atom;

import com.huayu.eframe.server.common.i18n.table.bo.Lang;
import com.huayu.eframe.server.common.i18n.table.bo.LangDefine;

import java.util.List;

/**
 * Created by Leo on 2018/9/8.
 */
public interface LangAtom
{
    Lang addLangValue(Lang lang);

    List<Lang> batchAddLangValue(List<Lang> lang);

    List<Lang> batchUpdateLangValue(List<Lang> lang);

    List<Lang> queryLangValueByLang(LangDefine langDefine);

    List<Lang> queryLangValueByCode(String fCode);

    Lang queryLangValueByLangAndCode(LangDefine langDefine,String fCode);

    Lang queryLangValueByPrimaryCode(String code);

    Lang updateLangValue(Lang lang);

    void removeLangValue(Lang lang);

    void batchRemoveLangValue(List<Lang> lang);


    List<Lang> queryLangValue();
}
