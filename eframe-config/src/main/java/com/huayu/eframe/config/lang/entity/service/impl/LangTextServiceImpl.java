package com.huayu.eframe.config.lang.entity.service.impl;

import com.huayu.eframe.config.lang.entity.atom.LangDefineAtom;
import com.huayu.eframe.config.lang.entity.atom.LangTextAtom;
import com.huayu.eframe.config.lang.entity.bo.LangDefine;
import com.huayu.eframe.config.lang.entity.bo.LangText;
import com.huayu.eframe.config.lang.entity.cache.LangTextCache;
import com.huayu.eframe.config.lang.entity.service.LangTextService;
import com.huayu.eframe.config.lang.entity.service.LangTextValue;
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
public class LangTextServiceImpl implements LangTextService
{

    @Autowired
    private LangTextAtom langTextAtom;

    @Autowired
    private LangDefineAtom langDefineAtom;

    @Override
    public LangTextValue batchAddLangValue(LangTextValue langTextValue)
    {
        if(null == langTextValue || null == langTextValue.getValues())
        {
          throw new IFPException();
        }
        List<LangText> addLangList = new ArrayList<>();
        MapUtils.iterator(langTextValue.getValues(),(m,k,v)->{
            LangText langText = new LangText();
            langText.setPrimaryCode(createPrimaryKey());
            langText.setLangCode(langTextValue.getForeignCode());
            LangShortValue langValueTemp = new LangShortValue();
            langValueTemp.setValue(v.getValue());
            langValueTemp.setLanguageCode(k);
            putInformation(langText,langValueTemp);
            addLangList.add(langText);

        });
        List<LangText> addResult = langTextAtom.batchAddLangText(addLangList);
        LocalAttribute.addNeedRefreshCache(LangTextCache.CACHE_NAME);
        return LangUtils.buildListLangText(langTextValue.getForeignCode(),addResult);
    }

    @Override
    public LangTextValue batchModifyLangValue(LangTextValue langTextValue)
    {
        if(null == langTextValue || null == langTextValue.getValues())
        {
            throw new IFPException();
        }
        List<LangText> modifyLangList = new ArrayList<>();
        List<LangText> langByFKey = langTextAtom.queryLangTextByCode(langTextValue.getForeignCode());
        Map<String,LangText> langByFKeyMap = new HashMap<>();
        CollectionUtils.iterator(langByFKey,(c,v,i)->{
            langByFKeyMap.put(v.getPrimaryCode(),v);
        });
        MapUtils.iterator(langTextValue.getValues(),(m,k,v)->{
            LangText langText = langByFKeyMap.get(v.getPrimaryKey());
            LangShortValue langValueTemp = new LangShortValue();
            langValueTemp.setValue(v.getValue());
            putInformation(langText,langValueTemp);
            modifyLangList.add(langText);

        });
        List<LangText> updateResult = langTextAtom.batchUpdateLangText(modifyLangList);
        LocalAttribute.addNeedRefreshCache(LangTextCache.CACHE_NAME);
        return LangUtils.buildListLangText(langTextValue.getForeignCode(),updateResult);
    }

    private String createPrimaryKey()
    {
        return "lt" +  RandomUtils.getRandomUUID();
    }


    @Override
    public String removeLangValueByCode(String fCode)
    {
        List<LangText> langTexts = langTextAtom.queryLangTextByCode(fCode);
        langTextAtom.batchRemoveLangValue(langTexts);
        LocalAttribute.addNeedRefreshCache(LangTextCache.CACHE_NAME);
        return fCode;
    }

    private void putInformation(LangText langText, LangShortValue langValue)
    {
       if(null == langText || null == langValue)
       {
           return ;
       }
       if(null != langValue.getValue())
       {
           langText.setValue(langValue.getValue());
       }
        if(null != langValue.getLanguageCode())
        {
            LangDefine existLang = langDefineAtom.queryLangDefineByCode(langValue.getLanguageCode());
            if(null != existLang)
            {
                langText.setLangDefine(existLang);
            }

        }
    }


}
