package com.huayu.eframe.config.lang.entity.util;

import com.huayu.eframe.config.lang.entity.bo.Languages;
import com.huayu.eframe.config.lang.entity.service.LangShortService;
import com.huayu.eframe.config.lang.entity.service.LangShortValue;
import com.huayu.eframe.server.tool.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Deprecated
public class InternationalServiceFacade
{
    @Autowired
    private LangShortService langService;


    public String add(Languages languages)
    {
        if (null == languages || MapUtils.isEmpty(languages.getValues()))
        {
            return null;
        }
        String languageCode = LangUtils.genI18nCode();

        List<LangShortValue> list = LangUtils.converToLangList(languages.getValues());
//        langService.save(languageCode, list);

        return languageCode;
    }
}
