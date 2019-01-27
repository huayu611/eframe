package com.huayu.eframe.server.mvc.token;

import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Created by Leo on 2018/9/15.
 */
@Service
public class TokenFacade
{
    private static final LogDebug debug = new LogDebug(TokenFacade.class);

    @Autowired
    private TokenObserver tokenObserver;

    public Token createToken(Class tokenClass,String loginName,String type)
    {
        return createToken(tokenClass,loginName,type,null);
    }

    public Token createToken(Class tokenClass, String loginName, String type, Locale locale)
    {
        return getTokenMirror(tokenClass).createToken(loginName,type,locale);
    }

    private TokenMirror getTokenMirror(Class tokenClass)
    {
        TokenMirror mirror = tokenObserver.getTokenMirror(tokenClass);
        if(null == mirror)
        {
            throw new IFPException(ErrorCode.TOKEN_ERROR,"Token Error , please contract administrators ");
        }
        return mirror;
    }

    public Token getMirrorByToken(String tokenId)
    {
        Token token = null;
        try {
            token = tokenObserver.getToken(tokenId);
        }
        catch(Exception e)
        {
            debug.errorLog("check token error");
            throw e;
        }
        return token;

    }

    public void updateTokenExpireTime(Token token)
    {
        TokenMirror tokenMirror = token.getTokenMirror();
        tokenMirror.updateTokenExpireTime(token);
    }
}
