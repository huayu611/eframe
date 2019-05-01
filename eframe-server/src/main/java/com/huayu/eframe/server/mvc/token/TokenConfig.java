package com.huayu.eframe.server.mvc.token;

import com.huayu.eframe.server.common.ConfigurationUtils;
import com.huayu.eframe.server.config.properties.SystemConfig;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.restful.TokenErrorAuthenticationException;
import com.huayu.eframe.server.service.exception.restful.TokenExpireAuthenticationException;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Leo on 2018/10/3.
 */
public class TokenConfig
{

    private static final LogDebug debug = new LogDebug(TokenConfig.class);

    public final static String TOKEN_GEN_TYPE = "sys_token_id_generate_type";

    public final static String TOKEN_GEN_DEFAULT_TYPE = "J";

    private final static String base64Secret = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";


    public static Claims parseJWT(String jsonWebToken)
    {
        try
        {
            debug.log(new Date());
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        }
        catch (Exception ex)
        {
            debug.log("parser JWT error");
            debug.log(ex);
            if (ex instanceof ExpiredJwtException)
            {
                throw new TokenExpireAuthenticationException("Token Expired");
            }
            else
            {
                throw new TokenErrorAuthenticationException("Token Error");
            }


        }
    }

    public static String getTokeIdGenType()
    {
        String value = SystemConfig.getValue(TOKEN_GEN_TYPE, TOKEN_GEN_DEFAULT_TYPE);
        debug.log(value);
        return value;
    }

    public static Token generateJWTToken(String primary, int duration, Map map)
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
        Date expireTime = null;
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").setClaims(map)
                .setIssuer(primary)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (duration >= 0)
        {

            Date nowTime = DateUtils.getCurrentDate();
            expireTime = DateUtils.modifySeconds(nowTime, duration);
            builder.setExpiration(expireTime).setNotBefore(nowTime);
        }
        Token token = new Token();
        String result = builder.compact();
        token.setToken(result);
        token.setExpireTime(expireTime);
        token.setDuration(Long.valueOf(duration));
        token.setPrimaryCode(primary);
        token.setPrimaryType(StringUtils.getString(map.get(AbstractTokenMirror.TOKEN_OWNER_TYPE)));
        String localObject = StringUtils.getString(map.get(AbstractTokenMirror.LOCALE));
        if (StringUtils.isNotNullAndEmpty(localObject))
        {
            Locale local = new Locale(localObject);
            token.setLocale(local);
        }
        else
        {
            token.setLocale(ConfigurationUtils.getDefaultLocal());
        }

        return token;
    }
}
