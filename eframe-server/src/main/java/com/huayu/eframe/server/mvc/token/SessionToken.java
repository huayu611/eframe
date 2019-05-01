package com.huayu.eframe.server.mvc.token;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Leo on 2018/9/16.
 */
public class SessionToken
{

    private Map<String, Object> sessionMap;

    public SessionToken()
    {
        sessionMap = new ConcurrentHashMap<>();
    }

    public void addValue(String key, Object value)
    {
        sessionMap.put(key, value);
    }

    public <T> T getValue(String key)
    {
        return (T) sessionMap.get(key);
    }
}
