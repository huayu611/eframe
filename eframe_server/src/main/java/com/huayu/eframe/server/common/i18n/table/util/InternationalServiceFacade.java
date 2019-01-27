package com.huayu.eframe.server.common.i18n.table.util;

import com.huayu.eframe.server.common.i18n.table.bo.LangValue;
import com.huayu.eframe.server.common.i18n.table.bo.Languages;
import com.huayu.eframe.server.common.i18n.table.service.LangService;
import com.huayu.eframe.server.tool.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternationalServiceFacade
{
    @Autowired
    private LangService langService;


    public String add(Languages languages)
    {
        if(null == languages || MapUtils.isEmpty(languages.getValues()))
        {
            return null;
        }
        String languageCode = LangUtils.genI18nCode();

        List<LangValue> list = LangUtils.converToLangList(languages.getValues());
        langService.save(languageCode,list);

        return languageCode;
    }
}
