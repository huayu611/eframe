package com.huayu.eframe.config.lang.entity.util;

import com.huayu.eframe.config.lang.entity.bo.LangValue;
import com.huayu.eframe.config.lang.entity.bo.LangText;
import com.huayu.eframe.config.lang.entity.cache.LangDefineCache;
import com.huayu.eframe.config.lang.entity.service.LangDefineDetail;
import com.huayu.eframe.config.lang.entity.service.LangTextValue;
import com.huayu.eframe.config.lang.entity.service.LangShortValue;
import com.huayu.eframe.config.lang.entity.service.LangValueDetail;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.RandomUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import com.huayu.eframe.server.tool.util.MapUtils;

import java.util.*;

public class LangUtils
{
    public static Map<String, String> converToLangMap(List<LangShortValue> langValueList)
    {
        if (CollectionUtils.isEmpty(langValueList))
        {
            return new HashMap<>();
        }
        Map<String, String> result = new HashMap<>();
        for (LangShortValue langValue : langValueList)
        {
//            result.put(langValue.getLanguage(), langValue.getValue());
        }

        return result;
    }

    public static List<LangShortValue> converToLangList(Map<String, String> map)
    {
        if (MapUtils.isEmpty(map))
        {
            return new ArrayList<>();
        }

        List<LangShortValue> result = new ArrayList<>();
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();

            LangShortValue langValue = new LangShortValue();
//            langValue.setLanguage(key);
            langValue.setValue(value);
            result.add(langValue);
        }
        return result;
    }


    public static String genI18nCode()
    {
        return "IN_" + RandomUtils.getRandomUUID();
    }


    public static  String getLanguageNameByCode(String code)
    {
        if(StringUtils.isNullOrEmpty(code))
        {
            return null;
        }
        LangDefineCache langDefineCache = BeanPool.getService(LangDefineCache.class);
        LangDefineDetail ret = langDefineCache.queryLanguageByCode(code);
        return null == ret ? null : ret.getLanguageName();
    }

    public static LangShortValue buildListLangValue(String fCode, List<LangValue> langValues)
    {
        Map<String, LangValueDetail> map = new HashMap<>();
        CollectionUtils.iterator(langValues,(c,v,i)->{
            if(null != v.getLangDefine())
            {
                LangValueDetail langValueDetail = new LangValueDetail(v);

                map.put(v.getLangDefine().getCode(),langValueDetail);
            }
        });
        LangShortValue langValue = new LangShortValue();
        langValue.setForeignCode(fCode);
        langValue.setValues(map);
        return langValue;
    }

    public static LangTextValue buildListLangText(String fCode, List<LangText> langValues)
    {
        Map<String, LangValueDetail> map = new HashMap<>();
        CollectionUtils.iterator(langValues,(c,v,i)->{
            if(null != v.getLangDefine())
            {
                LangValueDetail langValueDetail = new LangValueDetail(v);
                map.put(v.getLangDefine().getCode(),langValueDetail);
            }
        });
        LangTextValue langValue = new LangTextValue();
        langValue.setForeignCode(fCode);
        langValue.setValues(map);
        return langValue;
    }


}
