package com.huayu.eframe.server.common.i18n.table.service.impl;

import com.huayu.eframe.server.common.i18n.table.atom.LangAtom;
import com.huayu.eframe.server.common.i18n.table.bo.Lang;
import com.huayu.eframe.server.common.i18n.table.bo.LangValue;
import com.huayu.eframe.server.common.i18n.table.repository.LangRepository;
import com.huayu.eframe.server.common.i18n.table.service.LangService;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LangServiceImpl implements LangService
{

    @Autowired
    private LangAtom langAtom;


    @Override
    public List<LangValue>  save(String langCode, List<LangValue> values)
    {
        if(CollectionUtils.isEmpty(values) || StringUtils.isNullOrEmpty(langCode))
        {
            return null;
        }

        for(LangValue langValue : values)
        {
            save(langCode,langValue);
        }
        return values;
    }

    @Override
    public List<LangValue> update(String langCode, List<LangValue> values)
    {
        if(CollectionUtils.isEmpty(values) || StringUtils.isNullOrEmpty(langCode))
        {
            return null;
        }

        for(LangValue langValue : values)
        {
            save(langCode,langValue);
        }
        return saveAll(langCode,values);
    }

    @Override
    public void delete(String langCode)
    {
        if(StringUtils.isNullOrEmpty(langCode))
        {
            return ;
        }
        List<Lang> langList = langAtom.getLangsByCode(langCode);
        for(Lang lang : langList)
        {
            langAtom.delete(lang);
        }

    }



    public List<LangValue>  saveAll(String langCode, List<LangValue> values)
    {
        for(LangValue langValue : values)
        {
            save(langCode,langValue);
        }
        return values;
    }

    private void save(String langCode,LangValue langValue)
    {
        Lang lang = new Lang();
        lang.setLangCode(langCode);
        lang.setLanguage(langValue.getLanguage());
        lang.setValue(langValue.getValue());
        langAtom.save(lang);
    }




}
