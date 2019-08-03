package com.huayu.eframe.server.common.i18n.table.cache.impl;

import com.huayu.eframe.server.cache.frame.AbstractFrameCache;
import com.huayu.eframe.server.cache.frame.DefaultIndex;
import com.huayu.eframe.server.cache.frame.Index;
import com.huayu.eframe.server.common.i18n.table.atom.LangAtom;
import com.huayu.eframe.server.common.i18n.table.bo.Lang;
import com.huayu.eframe.server.common.i18n.table.cache.LangCache;
import com.huayu.eframe.server.common.i18n.table.service.LangValue;
import com.huayu.eframe.server.common.i18n.table.service.LangValueDetail;
import com.huayu.eframe.server.common.i18n.table.util.LangUtils;
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
public class LangCacheImpl extends AbstractFrameCache<LangValue> implements LangCache
{
    @Autowired
    private LangAtom langAtom;

    public LangCacheImpl()
    {
        registerIndex(new ForeignCodeIndex());
    }

    static class ForeignCodeIndex implements Index<LangValue>
    {
        @Override
        public String getIndex(LangValue lang)
        {
            return lang.getForeignCode();
        }
    }

    @Override
    public List<LangValue> load()
    {
        List<Lang> allLangValue = langAtom.queryLangValue();
        Map<String,List<Lang>> buildMap = new HashMap<>();
        CollectionUtils.iterator(allLangValue,(c,v,i)->{
            List<Lang> ret = buildMap.get(v.getLangCode());
            if(null == ret)
            {
                ret = new ArrayList<>();
                buildMap.put(v.getLangCode(),ret);
            }
            ret.add(v);
        });
        List<LangValue> langValuesRet = new ArrayList<>();
        MapUtils.iterator(buildMap,(m,k,v)->{
            LangValue vRet = LangUtils.buildListLangValue(k, v);
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
    public LangValue getLangByLangCode(String langCode)
    {
        LangValue langValue = new LangValue();
        langValue.setForeignCode(langCode);
        List<LangValue> langValues = getResultByIndex(ForeignCodeIndex.class, langValue);
        return CollectionUtils.getFirstElement(langValues);
    }

    @Override
    public List<LangValue> queryAll()
    {
        LangValue langValue = new LangValue();
        List<LangValue> langValues = getResultByIndex(DefaultIndex.class, langValue);
        return langValues;
    }

    @Override
    public String getLangValueByLangCodeAndLanguage(String langCode,String langDef)
    {
        LangValue langValue = getLangByLangCode(langCode);
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
