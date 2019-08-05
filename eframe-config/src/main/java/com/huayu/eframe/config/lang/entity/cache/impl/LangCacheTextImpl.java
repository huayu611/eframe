package com.huayu.eframe.config.lang.entity.cache.impl;

import com.huayu.eframe.server.cache.frame.AbstractFrameCache;
import com.huayu.eframe.server.cache.frame.DefaultIndex;
import com.huayu.eframe.server.cache.frame.Index;
import com.huayu.eframe.config.lang.entity.atom.LangTextAtom;
import com.huayu.eframe.config.lang.entity.bo.LangText;
import com.huayu.eframe.config.lang.entity.cache.LangTextCache;
import com.huayu.eframe.config.lang.entity.service.LangTextValue;
import com.huayu.eframe.config.lang.entity.service.LangValueDetail;
import com.huayu.eframe.config.lang.entity.util.LangUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import com.huayu.eframe.server.tool.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Leo on 2018/9/2.
 */
@Service
public class LangCacheTextImpl extends AbstractFrameCache<LangTextValue> implements LangTextCache
{
    @Autowired
    private LangTextAtom langTextAtom;

    public LangCacheTextImpl()
    {
        registerIndex(new TextForeignCodeIndex());
    }

    static class TextForeignCodeIndex implements Index<LangTextValue>
    {
        @Override
        public String getIndex(LangTextValue lang)
        {
            return lang.getForeignCode();
        }
    }

    @Override
    public List<LangTextValue> load()
    {
        List<LangText> allLangValue = langTextAtom.queryLangText();
        Map<String,List<LangText>> buildMap = new HashMap<>();
        CollectionUtils.iterator(allLangValue,(c,v,i)->{
            List<LangText> ret = buildMap.get(v.getLangCode());
            if(null == ret)
            {
                ret = new ArrayList<>();
                buildMap.put(v.getLangCode(),ret);
            }
            ret.add(v);
        });
        List<LangTextValue> langValuesRet = new ArrayList<>();
        MapUtils.iterator(buildMap,(m,k,v)->{
            LangTextValue vRet = LangUtils.buildListLangText(k, v);
            langValuesRet.add(vRet);
        });
        return langValuesRet;
    }

    @Override
    public String cacheName()
    {
        return CACHE_NAME;
    }

    @Override
    public LangTextValue getLangTextByLangCode(String langCode)
    {
        LangTextValue langText = new LangTextValue();
        langText.setForeignCode(langCode);
        List<LangTextValue> langValues = getResultByIndex(TextForeignCodeIndex.class, langText);
        return CollectionUtils.getFirstElement(langValues);
    }

    @Override
    public List<LangTextValue> queryAll()
    {
        LangTextValue langValue = new LangTextValue();
        List<LangTextValue> langValues = getResultByIndex(DefaultIndex.class, langValue);
        return langValues;
    }

    @Override
    public String getLangTextByLangCodeAndLanguage(String langCode, String langDef)
    {
        LangTextValue langValue = getLangTextByLangCode(langCode);
        if(null != langValue && null != langValue.getValues())
        {
            LangValueDetail detail = langValue.getValues().get(langDef);
            if(null !=detail)
            {
                return detail.getValue();
            }
        }
        return "";
    }

}
