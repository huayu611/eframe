package com.huayu.eframe.server.mvc.token;

import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.util.MapUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/7/21.
 */

public class TokenMap
{


    private Map<String, Token> tokenMap;

    public TokenMap()
    {
        tokenMap = new ConcurrentHashMap();
    }


    public void put(Token token)
    {
        tokenMap.put(token.getToken(), token);
    }

    public Token get(String tokenKey)
    {
        Token token = tokenMap.get(tokenKey);
        if (null == token)
        {
            return null;
        }
        if (DateUtils.isExpired(token.getExpireTime()))
        {
            tokenMap.remove(tokenKey);
            return null;
        }
        return token;
    }

    public int count()
    {
        return tokenMap.size();
    }

    public int cleanExpiredSession()
    {
        int count = 0;
        if (MapUtils.isEmpty(tokenMap))
        {
            return count;
        }
        Set<Map.Entry<String, Token>> tokenMapEntrySet = tokenMap.entrySet();
        Iterator<Map.Entry<String, Token>> tokenMapIterator = tokenMapEntrySet.iterator();
        while (tokenMapIterator.hasNext())
        {
            Map.Entry<String, Token> tokenElement = tokenMapIterator.next();
            Token token = tokenElement.getValue();
            if (DateUtils.isExpired(token.getExpireTime()))
            {
                tokenMap.remove(tokenElement.getKey());
                count++;
            }
        }
        return count;
    }

    protected void clean()
    {

    }


}
