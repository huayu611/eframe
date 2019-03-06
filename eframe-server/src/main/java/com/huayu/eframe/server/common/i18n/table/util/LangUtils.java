package com.huayu.eframe.server.common.i18n.table.util;

import com.huayu.eframe.server.common.i18n.table.bo.LangValue;
import com.huayu.eframe.server.tool.basic.RandomUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import com.huayu.eframe.server.tool.util.MapUtils;

import java.util.*;

public class LangUtils
{
    public static Map<String, String> converToLangMap(List<LangValue> langValueList)
    {
        if (CollectionUtils.isEmpty(langValueList))
        {
            return new HashMap<>();
        }
        Map<String, String> result = new HashMap<>();
        for(LangValue langValue: langValueList )
        {
            result.put(langValue.getLanguage(),langValue.getValue());
        }

        return result;
    }

    public static List<LangValue> converToLangList(Map<String, String> map)
    {
        if(MapUtils.isEmpty(map))
        {
            return new ArrayList<>();
        }

        List<LangValue> result =  new ArrayList<>();
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
        while(iterator.hasNext())
        {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();

            LangValue langValue = new LangValue();
            langValue.setLanguage(key);
            langValue.setValue(value);
            result.add(langValue);
        }
        return result;
    }


    public static String genI18nCode()
    {
        return "IN_"+ RandomUtils.getRandomUUID();
    }



}
