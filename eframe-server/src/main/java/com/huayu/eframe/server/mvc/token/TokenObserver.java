package com.huayu.eframe.server.mvc.token;

import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.StringUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Leo on 2018/9/15.
 */
@Service
public class TokenObserver implements InitializingBean
{

    private static final LogDebug debug = new LogDebug(TokenObserver.class);

    private List<TokenMirror> allToken;

    private Map<String, TokenMirror> tokenMirrorMap;


    public TokenObserver(@Nullable List<TokenMirror> allTokenMirror)
    {
        this.allToken = allTokenMirror;
    }


    @Override
    public void afterPropertiesSet() throws Exception
    {
        tokenMirrorMap = new ConcurrentHashMap<>();

        for (TokenMirror mirror : allToken)
        {
            tokenMirrorMap.put(mirror.getClass().getName(), mirror);
        }
        debug.log(tokenMirrorMap);
    }

    public TokenMirror getTokenMirror(Class clazz)
    {
        String cla = clazz.getName();

        debug.log(cla);

        return tokenMirrorMap.get(cla);

    }

    public TokenMirror getTokenMirrorByTokenId(String tokenId)
    {
        for (TokenMirror mirror : allToken)
        {
            if (mirror.isOwnerToken(tokenId))
            {
                return mirror;
            }
        }
        return null;
    }

    public Token getToken(String tokenId)
    {
        if (TokenConfig.getTokeIdGenType().equals("R"))
        {
            TokenMirror tm = getTokenMirrorByTokenId(tokenId);
            Assert.notNull(tm, "Token error ");
            return tm.getToken(tokenId);

        }
        else
        {
            return getJWTToken(tokenId);
        }

    }

    private Token getJWTToken(String tokenId)
    {
        Claims jwt = TokenConfig.parseJWT(tokenId);
        Object tokenType = jwt.get(AbstractTokenMirror.TOKEN_TYPE_KEY);
        if (StringUtils.isString(tokenType))
        {
            String tokenTypeString = StringUtils.getString(tokenType);
            TokenMirror tm = tokenMirrorMap.get(tokenTypeString);
            Assert.notNull(tm, "Token error ");
            return tm.getToken(jwt);
        }
        return null;
    }

}
