package com.huayu.eframe.server.mvc.token;

import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.StringUtils;

import java.util.Locale;

/**
 * Created by Administrator on 2018/7/18.
 */
public class TokenManager
{
    private static final LogDebug debug = new LogDebug(TokenManager.class);


    public static Token createToken(Class clazz, String loginName, String type)
    {
        return createToken(clazz, loginName, type, null);
    }

    public static Token createToken(Class clazz, String loginName, String type, Locale locale)
    {
        TokenFacade tokenFacade = BeanPool.getServiceByClass(TokenFacade.class);
        return tokenFacade.createToken(clazz, loginName, type, locale);
    }

    public static Token getToken(String tokenID)
    {
        if (StringUtils.isNullOrEmpty(tokenID))
        {
            return null;
        }
        TokenFacade tokenFacade = BeanPool.getServiceByClass(TokenFacade.class);
        return tokenFacade.getMirrorByToken(tokenID);
    }

    public static void updateToken(Token token)
    {
        TokenFacade tokenFacade = BeanPool.getServiceByClass(TokenFacade.class);
        tokenFacade.updateTokenExpireTime(token);
    }





}
