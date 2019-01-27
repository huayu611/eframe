package com.huayu.eframe.server.mvc.token;

import io.jsonwebtoken.Claims;

import java.util.List;
import java.util.Locale;

/**
 * Created by Leo on 2018/9/15.
 */
public interface TokenMirror
{

    /**
     * 获取token使用
     * @param primary, Token中唯一标识
     * @param type 标记token类型，可以定制传递
     * @return
     */
    default Token createToken(String primary,String type){return createToken(primary,type,null);};

    /**
     * @param primary
     * @param type
     * @param locale
     * @return
     */
    Token createToken(String primary, String type, Locale locale);

    /**
     * @param tokenID
     * @return
     */
    Token getToken(String tokenID);


    /**
     * JWT专用
     * @param clm
     * @return
     */
    default Token getToken(Claims clm){return null;};


    /**
     * @param tokenID
     * @return
     */
    boolean isOwnerToken(String tokenID);

    /**
     * token 生命周期时长， 单位位秒
     * @return
     */
    default int cycle(){return 3600;};

    /**
     * 读取权限
     * @param primary
     * @return
     */
    List<AuthView> loadAuthView(String primary);

    /**
     * 刷新token的有效期
     * @param token
     */
    default void updateTokenExpireTime(Token token){};


}
