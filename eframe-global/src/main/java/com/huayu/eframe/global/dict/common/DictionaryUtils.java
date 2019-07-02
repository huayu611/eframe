package com.huayu.eframe.global.dict.common;

import com.huayu.eframe.global.dict.reader.DictDetail;
import com.huayu.eframe.global.dict.reader.DictLangService;
import com.huayu.eframe.global.dict.reader.DictionaryService;
import com.huayu.eframe.server.common.CommonHelper;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;

import java.util.List;
import java.util.Locale;

/**
 * Created by Leo on 2019/6/25.
 */
public class DictionaryUtils
{
    private static final LogDebug debug = new LogDebug(DictionaryUtils.class);

    public static String getDictNameDictKeyAndValue(String code,String param)
    {
        DictionaryService dictionaryService = BeanPool.getService(DictionaryService.class);
        List<DictDetail> result = dictionaryService.getDict(code);
        if(CollectionUtils.isEmpty(result))
        {
            return StringUtils.BLANK;
        }
        for(DictDetail detail : result)
        {
            if(StringUtils.equalStringNoCareUpperAndLower(detail.getCode(),param))
            {
                return buildDictName(detail);
            }
        }
        return StringUtils.BLANK;
    }


    public static String buildDictName(DictDetail dic)
    {
        String lang = dic.getLangCode();
        String name = dic.getName();
        if (StringUtils.isNotNullAndEmpty(name))
        {
            return name;
        }
        Locale l = null;
        if (null == LocalAttribute.getToken() || null == LocalAttribute.getToken().getLocale())
        {
            l = CommonHelper.getDefaultLocal();
        }
        else
        {
            l = LocalAttribute.getToken().getLocale();
        }
        String inName = "";
        try
        {
            inName = DictLangService.getDictName(lang, null, l);
        }
        catch (Exception e)
        {
            debug.log(e);
        }
        return inName;
    }

}
