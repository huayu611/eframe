package com.huayu.eframe.server.context.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leo on 2019/2/18.
 */
public class HttpLocalAttribute
{
    private static ThreadLocal<String> httpJSONContent = new ThreadLocal<String>();



    public static void setHttpJSONContent(String key)
    {
        httpJSONContent.set(key);
    }

    public static String getHttpJSONContent()
    {
        return httpJSONContent.get();
    }
}
