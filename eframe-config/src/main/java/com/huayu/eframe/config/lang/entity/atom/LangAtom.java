package com.huayu.eframe.config.lang.entity.atom;

import com.huayu.eframe.config.lang.entity.bo.LangValue;
import com.huayu.eframe.config.lang.entity.bo.LangDefine;

import java.util.List;

/**
 * Created by Leo on 2018/9/8.
 */
public interface LangAtom
{
    LangValue addLangValue(LangValue lang);

    List<LangValue> batchAddLangValue(List<LangValue> lang);

    List<LangValue> batchUpdateLangValue(List<LangValue> lang);

    List<LangValue> queryLangValueByLang(LangDefine langDefine);

    List<LangValue> queryLangValueByCode(String fCode);

    LangValue queryLangValueByLangAndCode(LangDefine langDefine, String fCode);

    LangValue queryLangValueByPrimaryCode(String code);

    LangValue updateLangValue(LangValue lang);

    void removeLangValue(LangValue lang);

    void batchRemoveLangValue(List<LangValue> lang);


    List<LangValue> queryLangValue();
}
