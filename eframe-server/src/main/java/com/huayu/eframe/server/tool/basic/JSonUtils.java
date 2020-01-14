package com.huayu.eframe.server.tool.basic;

import com.fasterxml.jackson.databind.ObjectMapper;

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
