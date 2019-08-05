package com.huayu.eframe.config.lang.entity.atom;

import com.huayu.eframe.config.lang.entity.bo.LangDefine;

import java.util.List;

/**
 * Created by Leo on 2019/7/22.
 */
public interface LangDefineAtom
{
    List<LangDefine> queryAllLangDefine();

    List<LangDefine> addBatchLangDefine(List<LangDefine> langDefine);

    LangDefine addLangDefine(LangDefine langDefine);

    LangDefine modifyLangDefine(LangDefine langDefine);

    LangDefine queryLangDefineByCode(String langDefineCode);

    void removeLangDefine(LangDefine langDefine);
}
