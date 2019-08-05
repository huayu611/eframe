package com.huayu.eframe.config.lang.entity.cache.impl;

import com.huayu.eframe.server.cache.frame.AbstractFrameCache;
import com.huayu.eframe.server.cache.frame.DefaultIndex;
import com.huayu.eframe.server.cache.frame.Index;
import com.huayu.eframe.config.lang.entity.atom.LangDefineAtom;
import com.huayu.eframe.config.lang.entity.bo.LangDefine;
import com.huayu.eframe.config.lang.entity.cache.LangDefineCache;
import com.huayu.eframe.config.lang.entity.service.LangDefineDetail;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2019/7/22.
 */
@Service
public class LangDefineCacheImpl extends AbstractFrameCache<LangDefineDetail> implements LangDefineCache
{
    @Autowired
    private LangDefineAtom langDefineAtom;


    public LangDefineCacheImpl()
    {
        registerIndex(new LangDefineCodeIndex());
    }

    @Override
    public List<LangDefineDetail> load()
    {
        List<LangDefineDetail> langDefineDetails = new ArrayList<>();
        List<LangDefine> allLanguage = langDefineAtom.queryAllLangDefine();
        CollectionUtils.iterator(allLanguage,(c,v,i)->{
            LangDefineDetail langDefineDetail = new LangDefineDetail();
            langDefineDetail.setLanguageName(v.getName());
            langDefineDetail.setLanguageCode(v.getCode());
            langDefineDetails.add(langDefineDetail);
        });
        return langDefineDetails;
    }

    @Override
    public String cacheName()
    {
        return CACHE_NAME;
    }


    @Override
    public List<LangDefineDetail> queryAllDefineLanguage()
    {
        LangDefineDetail langDefineDetail = new LangDefineDetail();
        List<LangDefineDetail> langList = getResultByIndex(DefaultIndex.class, langDefineDetail);
        return langList;
    }

    @Override
    public LangDefineDetail queryLanguageByCode(String langCode)
    {
        LangDefineDetail lang = new LangDefineDetail();
        lang.setLanguageCode(langCode);
        List<LangDefineDetail> result = getResultByIndex(LangDefineCodeIndex.class, lang);
        return CollectionUtils.getFirstElement(result);
    }

    static class LangDefineCodeIndex implements Index<LangDefineDetail>
    {
        @Override
        public String getIndex(LangDefineDetail lang)
        {
            return lang.getLanguageCode();
        }
    }
}
