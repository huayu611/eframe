package com.huayu.eframe.config.lang.entity.service.impl;

import com.huayu.eframe.config.lang.entity.atom.LangAtom;
import com.huayu.eframe.config.lang.entity.atom.LangDefineAtom;
import com.huayu.eframe.config.lang.entity.bo.LangValue;
import com.huayu.eframe.config.lang.entity.bo.LangDefine;
import com.huayu.eframe.config.lang.entity.cache.LangValueCache;
import com.huayu.eframe.config.lang.entity.service.LangShortService;
import com.huayu.eframe.config.lang.entity.service.LangShortValue;
import com.huayu.eframe.config.lang.entity.util.LangUtils;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.RandomUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import com.huayu.eframe.server.tool.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LangShortServiceImpl implements LangShortService
{

    @Autowired
    private LangAtom langAtom;

    @Autowired
    private LangDefineAtom langDefineAtom;

    @Override
    public LangShortValue addLangValue(LangShortValue langValue)
    {
        LangValue lang = new LangValue();
        lang.setLangCode(langValue.getForeignCode());
        putInformation(lang,langValue);
        LangValue result = langAtom.addLangValue(lang);
        LocalAttribute.addNeedRefreshCache(LangValueCache.CACHE_NAME);
        return new LangShortValue(result);
    }

    @Override
    public LangShortValue batchAddLangValue(LangShortValue langValue)
    {
        if(null == langValue || null == langValue.getValues())
        {
          throw new IFPException();
        }
        List<LangValue> addLangList = new ArrayList<>();
        MapUtils.iterator(langValue.getValues(),(m,k,v)->{
            LangValue lang = new LangValue();
            lang.setPrimaryCode(createPrimaryKey());
            lang.setLangCode(langValue.getForeignCode());
            LangShortValue langValueTemp = new LangShortValue();
            langValueTemp.setValue(v.getValue());
            langValueTemp.setLanguageCode(k);
            putInformation(lang,langValueTemp);
            addLangList.add(lang);

        });
        List<LangValue> result = langAtom.batchAddLangValue(addLangList);
        LocalAttribute.addNeedRefreshCache(LangValueCache.CACHE_NAME);
        return LangUtils.buildListLangValue(langValue.getForeignCode(),result);
    }

    @Override
    public LangShortValue batchModifyLangValue(LangShortValue langValue)
    {
        if(null == langValue || null == langValue.getValues())
        {
            throw new IFPException();
        }
        List<LangValue> modifyLangList = new ArrayList<>();
        List<LangValue> langByFKey = langAtom.queryLangValueByCode(langValue.getForeignCode());
        Map<String, LangValue> langByFKeyMap = new HashMap<>();
        CollectionUtils.iterator(langByFKey,(c,v,i)->{
            langByFKeyMap.put(v.getPrimaryCode(),v);
        });
        MapUtils.iterator(langValue.getValues(),(m,k,v)->{
            LangValue lang = langByFKeyMap.get(v.getPrimaryKey());
            LangShortValue langValueTemp = new LangShortValue();
            langValueTemp.setValue(v.getValue());
            putInformation(lang,langValueTemp);
            modifyLangList.add(lang);

        });
        List<LangValue> result = langAtom.batchUpdateLangValue(modifyLangList);
        LocalAttribute.addNeedRefreshCache(LangValueCache.CACHE_NAME);
        return LangUtils.buildListLangValue(langValue.getForeignCode(),result);
    }

    private String createPrimaryKey()
    {
        return "lv" +  RandomUtils.getRandomUUID();
    }

    @Override
    public LangShortValue updateLangValue(LangShortValue langValue)
    {

        LangValue lang = langAtom.queryLangValueByPrimaryCode(langValue.getPrimaryCode());
        if(null == lang)
        {
            throw new IFPException();
        }
        putInformation(lang,langValue);
        List<LangValue> resultForQuery = langAtom.queryLangValueByCode(lang.getLangCode());
        LocalAttribute.addNeedRefreshCache(LangValueCache.CACHE_NAME);
        return LangUtils.buildListLangValue(langValue.getForeignCode(),resultForQuery);
    }

    @Override
    public String removeLangValueByCode(String fCode)
    {
        List<LangValue> langs = langAtom.queryLangValueByCode(fCode);
        langAtom.batchRemoveLangValue(langs);
        LocalAttribute.addNeedRefreshCache(LangValueCache.CACHE_NAME);
        return fCode;
    }

    private void putInformation(LangValue lang, LangShortValue langValue)
    {
       if(null == lang || null == langValue)
       {
           return ;
       }
       if(null != langValue.getValue())
       {
           lang.setValue(langValue.getValue());
       }
        if(null != langValue.getLanguageCode())
        {
            LangDefine existLang = langDefineAtom.queryLangDefineByCode(langValue.getLanguageCode());
            if(null == existLang)
            {
                throw new IFPException();
            }
            lang.setLangDefine(existLang);
        }
    }


}
