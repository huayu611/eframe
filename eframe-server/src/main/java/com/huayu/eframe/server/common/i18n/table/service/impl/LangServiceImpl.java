package com.huayu.eframe.server.common.i18n.table.service.impl;

import com.huayu.eframe.server.common.i18n.table.atom.LangAtom;
import com.huayu.eframe.server.common.i18n.table.atom.LangDefineAtom;
import com.huayu.eframe.server.common.i18n.table.bo.Lang;
import com.huayu.eframe.server.common.i18n.table.bo.LangDefine;
import com.huayu.eframe.server.common.i18n.table.cache.LangCache;
import com.huayu.eframe.server.common.i18n.table.service.LangService;
import com.huayu.eframe.server.common.i18n.table.service.LangValue;
import com.huayu.eframe.server.common.i18n.table.util.LangUtils;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.RandomUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import com.huayu.eframe.server.tool.util.MapUtils;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LangServiceImpl implements LangService
{

    @Autowired
    private LangAtom langAtom;

    @Autowired
    private LangDefineAtom langDefineAtom;

    @Override
    public LangValue addLangValue(LangValue langValue)
    {
        Lang lang = new Lang();
        lang.setLangCode(langValue.getForeignCode());
        putInformation(lang,langValue);
        Lang result = langAtom.addLangValue(lang);
        LocalAttribute.addNeedRefreshCache(LangCache.CACHE_NAME);
        return new LangValue(result);
    }

    @Override
    public LangValue batchAddLangValue(LangValue langValue)
    {
        if(null == langValue || null == langValue.getValues())
        {
          throw new IFPException();
        }
        List<Lang> addLangList = new ArrayList<>();
        MapUtils.iterator(langValue.getValues(),(m,k,v)->{
            Lang lang = new Lang();
            lang.setPrimaryCode(createPrimaryKey());
            lang.setLangCode(langValue.getForeignCode());
            LangValue langValueTemp = new LangValue();
            langValueTemp.setValue(v.getValue());
            langValueTemp.setLanguageCode(k);
            putInformation(lang,langValueTemp);
            addLangList.add(lang);

        });
        List<Lang> result = langAtom.batchAddLangValue(addLangList);
        LocalAttribute.addNeedRefreshCache(LangCache.CACHE_NAME);
        return LangUtils.buildListLangValue(langValue.getForeignCode(),result);
    }

    @Override
    public LangValue batchModifyLangValue(LangValue langValue)
    {
        if(null == langValue || null == langValue.getValues())
        {
            throw new IFPException();
        }
        List<Lang> modifyLangList = new ArrayList<>();
        List<Lang> langByFKey = langAtom.queryLangValueByCode(langValue.getForeignCode());
        Map<String,Lang> langByFKeyMap = new HashMap<>();
        CollectionUtils.iterator(langByFKey,(c,v,i)->{
            langByFKeyMap.put(v.getPrimaryCode(),v);
        });
        MapUtils.iterator(langValue.getValues(),(m,k,v)->{
            Lang lang = langByFKeyMap.get(v.getPrimaryKey());
            LangValue langValueTemp = new LangValue();
            langValueTemp.setValue(v.getValue());
            putInformation(lang,langValueTemp);
            modifyLangList.add(lang);

        });
        List<Lang> result = langAtom.batchUpdateLangValue(modifyLangList);
        LocalAttribute.addNeedRefreshCache(LangCache.CACHE_NAME);
        return LangUtils.buildListLangValue(langValue.getForeignCode(),result);
    }

    private String createPrimaryKey()
    {
        return "lv" +  RandomUtils.getRandomUUID();
    }

    @Override
    public LangValue updateLangValue(LangValue langValue)
    {

        Lang lang = langAtom.queryLangValueByPrimaryCode(langValue.getPrimaryCode());
        if(null == lang)
        {
            throw new IFPException();
        }
        putInformation(lang,langValue);
        List<Lang> resultForQuery = langAtom.queryLangValueByCode(lang.getLangCode());
        LocalAttribute.addNeedRefreshCache(LangCache.CACHE_NAME);
        return LangUtils.buildListLangValue(langValue.getForeignCode(),resultForQuery);
    }

    @Override
    public String removeLangValueByCode(String fCode)
    {
        List<Lang> langs = langAtom.queryLangValueByCode(fCode);
        langAtom.batchRemoveLangValue(langs);
        LocalAttribute.addNeedRefreshCache(LangCache.CACHE_NAME);
        return fCode;
    }

    private void putInformation(Lang lang, LangValue langValue)
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
