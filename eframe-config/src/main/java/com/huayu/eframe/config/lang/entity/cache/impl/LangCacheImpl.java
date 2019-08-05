package com.huayu.eframe.config.lang.entity.cache.impl;

import com.huayu.eframe.server.cache.frame.AbstractFrameCache;
import com.huayu.eframe.server.cache.frame.DefaultIndex;
import com.huayu.eframe.server.cache.frame.Index;
import com.huayu.eframe.config.lang.entity.atom.LangAtom;
import com.huayu.eframe.config.lang.entity.bo.LangValue;
import com.huayu.eframe.config.lang.entity.cache.LangValueCache;
import com.huayu.eframe.config.lang.entity.service.LangShortValue;
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
public class LangCacheImpl extends AbstractFrameCache<LangShortValue> implements LangValueCache
{
    @Autowired
    private LangAtom langAtom;

    public LangCacheImpl()
    {
        registerIndex(new ForeignCodeIndex());
    }

    static class ForeignCodeIndex implements Index<LangShortValue>
    {
        @Override
        public String getIndex(LangShortValue lang)
        {
            return lang.getForeignCode();
        }
    }

    @Override
    public List<LangShortValue> load()
    {
        List<LangValue> allLangValue = langAtom.queryLangValue();
        Map<String,List<LangValue>> buildMap = new HashMap<>();
        CollectionUtils.iterator(allLangValue,(c,v,i)->{
            List<LangValue> ret = buildMap.get(v.getLangCode());
            if(null == ret)
            {
                ret = new ArrayList<>();
                buildMap.put(v.getLangCode(),ret);
            }
            ret.add(v);
        });
        List<LangShortValue> langValuesRet = new ArrayList<>();
        MapUtils.iterator(buildMap,(m,k,v)->{
            LangShortValue vRet = LangUtils.buildListLangValue(k, v);
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
    public LangShortValue getLangByLangCode(String langCode)
    {
        LangShortValue langValue = new LangShortValue();
        langValue.setForeignCode(langCode);
        List<LangShortValue> langValues = getResultByIndex(ForeignCodeIndex.class, langValue);
        return CollectionUtils.getFirstElement(langValues);
    }

    @Override
    public List<LangShortValue> queryAll()
    {
        LangShortValue langValue = new LangShortValue();
        List<LangShortValue> langValues = getResultByIndex(DefaultIndex.class, langValue);
        return langValues;
    }

    @Override
    public String getLangValueByLangCodeAndLanguage(String langCode,String langDef)
    {
        LangShortValue langValue = getLangByLangCode(langCode);
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
