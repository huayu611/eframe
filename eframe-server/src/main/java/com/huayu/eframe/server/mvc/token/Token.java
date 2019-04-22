package com.huayu.eframe.server.mvc.token;

import com.huayu.eframe.server.common.ConfigurationUtils;
import com.huayu.eframe.server.config.properties.SystemConfig;
import com.huayu.eframe.server.mvc.token.instance.TokenInstance;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/21.
 */
public class Token
{


    public Token(TokenMirror tokenMirror)
    {
        session = new SessionToken();

        this.tokenMirror = tokenMirror;
    }

    public Token()
    {
        session = new SessionToken();
    }
    private String primaryCode;

    private String primaryType;

    private Date loginTime;

    private Date lastUpdateTime;

    private Date expireTime;

    private Long duration;

    private String token;

    private String type;

    private TokenInstance tokenInstance;

    private SessionToken session;

    private List<AuthView> allAuthView;

    private TokenMirror tokenMirror;

    private Locale locale;

    public String getPrimaryCode()
    {
        return primaryCode;
    }

    public void setPrimaryCode(String primaryCode)
    {
        this.primaryCode = primaryCode;
    }

    public SessionToken getSession()
    {
        return session;
    }

    public void setSession(SessionToken session)
    {
        this.session = session;
    }

    public Date getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Date loginTime)
    {
        this.loginTime = loginTime;
    }

    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(Date expireTime)
    {
        this.expireTime = expireTime;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }


    public List<AuthView> getAllAuthView()
    {
        return allAuthView;
    }

    public void setAllAuthView(List<AuthView> allAuthView)
    {
        this.allAuthView = allAuthView;
    }

    public TokenMirror getTokenMirror()
    {
        return tokenMirror;
    }

    public void setTokenMirror(TokenMirror tokenMirror)
    {
        this.tokenMirror = tokenMirror;
    }

    public Locale getLocale()
    {
        if(null == locale)
        {
            return ConfigurationUtils.getDefaultLocal();
        }
        return locale;
    }

    public void setLocale(Locale locale)
    {
        this.locale = locale;
    }

    public String getPrimaryType()
    {
        return primaryType;
    }

    public void setPrimaryType(String primaryType)
    {
        this.primaryType = primaryType;
    }

    public TokenInstance getTokenInstance()
    {
        return tokenInstance;
    }

    public void setTokenInstance(TokenInstance tokenInstance)
    {
        this.tokenInstance = tokenInstance;
    }

    public Long getDuration()
    {
        return duration;
    }

    public void setDuration(Long duration)
    {
        this.duration = duration;
    }
}
