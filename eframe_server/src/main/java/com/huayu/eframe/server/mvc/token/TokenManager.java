package com.huayu.eframe.server.mvc.token;

import com.huayu.eframe.server.config.properties.SystemConfig;
import com.huayu.eframe.server.flow.FlowConstant;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.security.service.constant.SecurityConstant;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.RandomUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.encrypt.Encrypt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

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
