package com.huayu.eframe.server.context;


import com.huayu.eframe.server.mvc.token.SessionToken;
import com.huayu.eframe.server.mvc.token.Token;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/18.
 */
public class LocalAttribute
{
    private static ThreadLocal<LocalContext> local = new ThreadLocal<LocalContext>();

    private final static String TOKEN_FLAG = "_EFRAME_SESSION_TOKEN";

    private final static String LOCAL_CACHE_NEED_REFRESH = "_LOCAL_CACHE_REFRESH";

    public static void init()
    {
        LocalContext localContext = new LocalContext();
        localContext.setNowDate(DateUtils.getCurrentDate());
        local.set(localContext);
    }

    public static void addValue(String key, Object obj)
    {
        LocalContext localContext = getLocalAttribute();
        localContext.addValue(key, obj);
    }

    public static void addToken(Token token)
    {
        LocalContext localContext = getLocalAttribute();
        localContext.addValue(TOKEN_FLAG, token);
    }

    public static Token getToken()
    {
        LocalContext localContext = getLocalAttribute();
        Token token = localContext.getValue(TOKEN_FLAG);
        return token;
    }

    public static SessionToken getSession()
    {
        LocalContext localContext = getLocalAttribute();
        Token token = localContext.getValue(TOKEN_FLAG);
        if (null != token)
        {
            return token.getSession();
        }
        return null;
    }

    public static <T> T getValue(String key)
    {
        LocalContext localContext = getLocalAttribute();
        return localContext.getValue(key);
    }

    public static LocalContext getLocalAttribute()
    {
        LocalContext localContext = local.get();
        if (null == localContext)
        {
            localContext = new LocalContext();
            local.set(localContext);
        }
        return localContext;
    }

    public static Date getNow()
    {
        Date now = getLocalAttribute().getNowDate();
        if (null == now)
        {
            return DateUtils.getCurrentDate();
        }
        return now;
    }


    public Date getNowDate()
    {
        return getLocalAttribute().getNowDate();
    }

    public static void destroy()
    {
        LocalContext localVar = local.get();
        if (null != localVar)
        {
            local.remove();
        }
    }


    public static void addNeedRefreshCache(String cacheName)
    {
        if (StringUtils.isNullOrEmpty(cacheName))
        {
            return;
        }
        LocalContext localContext = getLocalAttribute();
        List<String> cacheList = localContext.getValue(LOCAL_CACHE_NEED_REFRESH);
        if (null == cacheList)
        {
            cacheList = new ArrayList<>();
            localContext.addValue(LOCAL_CACHE_NEED_REFRESH, cacheList);
        }
        cacheList.add(cacheName);
    }

    public static List<String> getNeedRefreshCache()
    {
        LocalContext localContext = getLocalAttribute();
        return localContext.getValue(LOCAL_CACHE_NEED_REFRESH);
    }
}
