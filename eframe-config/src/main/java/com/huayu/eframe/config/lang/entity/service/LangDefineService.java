package com.huayu.eframe.config.lang.entity.service;

/**
 * Created by Leo on 2019/8/3.
 */
public interface LangDefineService
{
    LangDefineDetail addLangDefine(LangDefineDetail langDefineDetail);

    LangDefineDetail updateLangDefine(LangDefineDetail langDefineDetail);

    String removeLangDefine(String langDefineCode);
}
