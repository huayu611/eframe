package com.huayu.eframe.server.tool.basic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huayu.eframe.server.tool.util.CollectionUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/8/2.
 */
public class JSonUtils
{
    public static String coverToJson(Object obj)
    {
        ObjectMapper mapper = new ObjectMapper();
        String jsonList = "";
        try
        {
            jsonList = mapper.writeValueAsString(obj);
        }
        catch (Exception e)
        {
            return "{}";
        }
        return jsonList;
    }

    public static String replaseSens(List<String> sens, String json)
    {
        if(CollectionUtils.isEmpty(sens))
        {
            return json;
        }
        String newJson = json;
        Pattern p= Pattern.compile("\"(.*?)\":\"(.*?)\"");
        Matcher m = p.matcher(newJson);
        int i=0;
        while (m.find())
        {
            String target = m.group(1);
            String target2 = m.group(2);
            for(String str : sens)
            {
                if (target.contains(str))
                {
                    String ok = m.group().replace(target2, "******");
                    newJson = newJson.replace(m.group(), ok);
                }
            }
        }
        return newJson;
    }



    public static <T> T jsonToObject(String jsonStr, Class<T> valueType)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            return objectMapper.readValue(jsonStr, valueType);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
