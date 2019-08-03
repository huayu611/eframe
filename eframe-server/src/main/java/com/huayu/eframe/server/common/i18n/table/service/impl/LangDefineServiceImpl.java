package com.huayu.eframe.server.common.i18n.table.service.impl;

import com.huayu.eframe.server.common.i18n.table.atom.LangDefineAtom;
import com.huayu.eframe.server.common.i18n.table.bo.LangDefine;
import com.huayu.eframe.server.common.i18n.table.cache.LangDefineCache;
import com.huayu.eframe.server.common.i18n.table.service.LangDefineDetail;
import com.huayu.eframe.server.common.i18n.table.service.LangDefineService;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/3.
 */
@Service
public class LangDefineServiceImpl implements LangDefineService
{
    @Autowired
    private LangDefineAtom langDefineAtom;

    @Override
    public LangDefineDetail addLangDefine(LangDefineDetail langDefineDetail)
    {
        LangDefine exist = queryLangDefineByCode(langDefineDetail.getLanguageCode());
        if(null != exist)
        {
            throw new IFPException();
        }

        exist = buildLangDefineDetail(langDefineDetail);
        LangDefine result = langDefineAtom.addLangDefine(exist);
        LocalAttribute.addNeedRefreshCache(LangDefineCache.CACHE_NAME);
        return null == result ? null : new LangDefineDetail(result.getCode(),result.getName());
    }

    @Override
    public LangDefineDetail updateLangDefine(LangDefineDetail langDefineDetail)
    {
        LangDefine exist = queryLangDefineByCode(langDefineDetail.getLanguageCode());
        if(null == exist)
        {
            throw new IFPException();
        }
        if(null != langDefineDetail.getLanguageName())
        {
            exist.setName(langDefineDetail.getLanguageName());
        }

        LangDefine result = langDefineAtom.modifyLangDefine(exist);
        LocalAttribute.addNeedRefreshCache(LangDefineCache.CACHE_NAME);
        return null == result ? null : new LangDefineDetail(result.getCode(),result.getName());
    }

    @Override
    public String removeLangDefine(String langDefineCode)
    {
        LangDefine exist = queryLangDefineByCode(langDefineCode);
        if(null == exist)
        {
            throw new IFPException();
        }
        langDefineAtom.removeLangDefine(exist);
        LocalAttribute.addNeedRefreshCache(LangDefineCache.CACHE_NAME);
        return langDefineCode;
    }

    private LangDefine buildLangDefineDetail(LangDefineDetail langDefineDetail)
    {
        LangDefine langDefine = new LangDefine();
        langDefine.setCode(langDefineDetail.getLanguageCode());
        langDefine.setName(langDefineDetail.getLanguageName());
        return langDefine;
    }

    private LangDefine queryLangDefineByCode(String code)
    {
        if(StringUtils.isNullOrEmpty(code))
        {
            throw new IFPException();
        }
        return langDefineAtom.queryLangDefineByCode(code);
    }
}
