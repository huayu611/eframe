package com.huayu.eframe.server.mvc.token;

import com.huayu.eframe.server.common.ConfigurationUtils;
import com.huayu.eframe.server.config.properties.SystemConfig;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.mvc.token.instance.TokenInstance;
import com.huayu.eframe.server.mvc.token.instance.TokenObjectMap;
import com.huayu.eframe.server.security.service.constant.SecurityConstant;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.RandomUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.encrypt.Encrypt;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by Leo on 2018/9/15.
 * 此类支持JWT和
 */
public abstract class AbstractTokenMirror implements TokenMirror, InitializingBean
{

    public final static String LOCALE = "E_LOCALE";

    public final static String PRIMARY = "E_PRIMARY";

    public final static String TOKEN_TYPE_KEY = "TOKEN_TYPE_KEY";

    public final static String TOKEN_OWNER_TYPE = "TOKEN_OWNER_TYPE";

    private static final LogDebug debug = new LogDebug(AbstractTokenMirror.class);

    private TokenMap tokenMap;

    public AbstractTokenMirror()
    {
        tokenMap = new TokenMap();
    }

    @Autowired
    private TokenObjectMap tokenObjectMap;

    @Override
    public Token createToken(String primary, String type, Locale locale)
    {
        String value = TokenConfig.getTokeIdGenType();
        if(null == locale)
        {
            locale = ConfigurationUtils.getDefaultLocal();
        }
        if (value.equals("R"))
        {
            return generateSessionToken(primary, type, locale);
        }
        else
        {
            if (value.equals("J"))
            {
                Map<String, Object> map = new HashMap<>();
                map.put(LOCALE, locale.getLanguage());
                map.put(PRIMARY, primary);
                map.put(TOKEN_TYPE_KEY, getTokenType());
                map.put(TOKEN_OWNER_TYPE,getObjType());//
                extendMap(map);
                return TokenConfig.generateJWTToken(primary, cycle(), map);
            }
        }
        return null;
    }

    protected void extendMap(Map map)
    {

    }

    private Token generateSessionToken(String primary, String type, Locale locale)
    {
        Date nowDate = DateUtils.getCurrentDate();
        Token token = new Token(this);
        token.setPrimaryCode(primary);
        token.setLoginTime(nowDate);
        token.setExpireTime(DateUtils.modifySeconds(nowDate, cycle()));
        token.setLastUpdateTime(nowDate);
        token.setType(type);
        token.setLocale(locale);
        token.setPrimaryType(getObjType());
        StringBuffer tokenBuffer = new StringBuffer();
        //token 拼接方法
        //token类型+"|"+（启动时长+6位随机数+UUID）
        tokenBuffer.append(getTokenType()).append("|").append(DateUtils.getNonTime()).append(RandomUtils.getRandomCharString(6)).append(UUID.randomUUID());
        String tokenBase64 = Encrypt.encodeBase64(tokenBuffer.toString());
        token.setToken(tokenBase64);
        token.setAllAuthView(loadAuthView(primary));
        if (StringUtils.equalString(SecurityConstant.LOGIN_TYPE.LOGIN_ONCE, token.getType()))
        {
            return token;
        }
//        TokenStore.putToken(token);

        tokenMap.put(token);
        return token;
    }

    public void updateTokenExpireTime(Token token)
    {
        if (StringUtils.equalString(SecurityConstant.LOGIN_TYPE.LOGIN_REFRESH_EXPIRE_TIME, token.getType()))
        {
            token.setExpireTime(DateUtils.modifySeconds(token.getExpireTime(), cycle()));
        }

    }

    protected String getTokenType()
    {
        return this.getClass().getName();
    }

    @Override
    public boolean isOwnerToken(String tokenID)
    {
        String value = TokenConfig.getTokeIdGenType();
        if (value.equals("R"))
        {
            String token64 = Encrypt.decodeBase64(tokenID);
            String selfClass = this.getClass().getName();
            return StringUtils.equalString(token64.substring(0, selfClass.length()), selfClass);
        }
        else if (value.equals("J"))
        {
            Claims jwtClaim = TokenConfig.parseJWT(tokenID);
            Object tokenType = jwtClaim.get(TOKEN_TYPE_KEY);
            debug.log(tokenType);
            if (StringUtils.isString(tokenType))
            {
                String clazz = StringUtils.getString(tokenType);
                String selfClass = this.getClass().getName();
                return StringUtils.equalString(clazz, selfClass);
            }
            return false;
        }
        return false;
    }

    @Override
    public Token getToken(String tokenID)
    {
        String value = TokenConfig.getTokeIdGenType();
        if (value.equals("R"))
        {
            return tokenMap.get(tokenID);
        }
        else if (value.equals("J"))
        {
            Claims jwtClaim = TokenConfig.parseJWT(tokenID);
            return getToken(jwtClaim);
        }
        return null;
    }

    public int onlineCount()
    {
        return tokenMap.count();
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {

        startTimeTask();

    }

    private void startTimeTask()
    {
        if (!needStartTimeTaskCleanSession())
        {
            return;
        }
        Long cycleMill = Long.valueOf(cycle()).longValue() * 1000l;

        CleanSession cleanSession = new CleanSession(tokenMap, cycleMill);

        Thread thread = new Thread(cleanSession);

        StringBuffer tName = new StringBuffer();
        tName.append("CleanSession-").append(getTokenType()).append(RandomUtils.getRandomInteger(5));
        thread.setName(tName.toString());

        thread.start();
    }

    /**
     * @param jwtClaim
     * @return
     */
    @Override
    public Token getToken(Claims jwtClaim)
    {
        debug.log(jwtClaim);
        Token token = new Token(this);
        Object locale = jwtClaim.get(LOCALE);
        Object primary = jwtClaim.get(PRIMARY);

        Object ownType = jwtClaim.get(TOKEN_OWNER_TYPE);
        if (null == primary)
        {
            return null;
        }
        if (null != locale)
        {
            Locale locale1 = new Locale(StringUtils.getString(locale));
            debug.log(locale);
            token.setLocale(locale1);
        }
        TokenInstance tokenInstance  = tokenObjectMap.getTokenInstance(StringUtils.getString(ownType));

        if(null == tokenInstance)
        {
            tokenInstance = BeanPool.getService("StaffTokenInstance");
        }
        token.setTokenInstance(tokenInstance);
        token.setPrimaryCode(StringUtils.getString(primary));
        token.setPrimaryType(StringUtils.getString(ownType));
        token.setAllAuthView(loadAuthView(StringUtils.getString(primary)));

        //token type

        return token;
    }


    class CleanSession implements Runnable
    {

        private long sleepTime = 300000l;
        private TokenMap tokenMap = null;

        CleanSession(TokenMap tm, Long sleep)
        {
            sleepTime = sleep;
            tokenMap = tm;
        }

        @Override
        public void run()
        {
            if (null == tokenMap)
            {
                return;
            }
            while (true)
            {
                tokenMap.cleanExpiredSession();
                try
                {
                    Thread.sleep(sleepTime);
                }
                catch (InterruptedException e)
                {
                    break;
                }
            }
        }

    }

    protected boolean needStartTimeTaskCleanSession()
    {
        return false;
    }

    //Token操作员类型
    public String getObjType()
    {
        return "STAFF";
    }

    //Token操作员类型对应的对象
    public TokenInstance getObjTypeInstance()
    {
        return tokenObjectMap.getTokenInstance(getObjType());
    }

}
