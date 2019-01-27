package com.huayu.eframe.server.common.i18n.table.cache.impl;

import com.huayu.eframe.server.cache.frame.AbstractFrameCache;
import com.huayu.eframe.server.cache.frame.Index;
import com.huayu.eframe.server.common.i18n.table.bo.Lang;
import com.huayu.eframe.server.common.i18n.table.bo.LangValue;
import com.huayu.eframe.server.common.i18n.table.cache.LangCache;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2018/9/2.
 */
@Service
public class LangCacheImpl extends AbstractFrameCache<Lang> implements LangCache
{

    public LangCacheImpl()
    {
        registerIndex(new LangCodeIndex());
    }

    static class LangCodeIndex implements Index<Lang>
    {

        @Override
        public String getIndex(Lang lang)
        {
            return lang.getLangCode();
        }
    }

    @Override
    public List<Lang> load()
    {
        return null;
    }

    @Override
    public String cacheName()
    {
        return "SYS_LANG";
    }

    @Override
    public  List<LangValue> getLangByLangCode(String langCode)
    {
        Lang lang = new Lang();
        lang.setLangCode(langCode);
        List<Lang> result = getResultByIndex(LangCodeIndex.class, lang);

        List<LangValue> listValue = new ArrayList<>();
        for(Lang langEntity : result)
        {
            LangValue langValue = new LangValue();
            langValue.setLanguage(langEntity.getLanguage());
            langValue.setValue(langEntity.getValue());
            listValue.add(langValue);
        }
        return listValue;
    }

    @Override
    public LangValue getLangByLangCodeAndLanguage(String langCode, String language)
    {
        if(StringUtils.isNullOrEmpty(langCode))
        {
            return null;
        }
        List<LangValue> langValues = getLangByLangCode(langCode);

        if(StringUtils.isNullOrEmpty(language))
        {
            return CollectionUtils.getFirstElement(langValues);
        }

        for(LangValue langValue : langValues)
        {
            if(StringUtils.equalString(langValue.getLanguage(),language))
            {
                return langValue;
            }
        }
        return null;
    }

}
