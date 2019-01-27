package com.huayu.eframe.server.security.config;


import com.huayu.eframe.server.mvc.token.AuthView;
import org.springframework.security.access.ConfigAttribute;

/**
 * Created by Administrator on 2018/7/29.
 */
public class EFrameConfigAttribute implements ConfigAttribute
{
    private AuthView authView;

    private boolean isWhite;

    public EFrameConfigAttribute()
    {

    }

    public EFrameConfigAttribute(AuthView authView)
    {
        this.authView = authView;
    }

    @Override
    public String getAttribute()
    {
        if (null == authView)
        {
            return "";
        }
        return authView.getUrl() + ";" + authView.getMethod();
    }

    public boolean isWhite()
    {
        return isWhite;
    }

    public void setWhite(boolean white)
    {
        isWhite = white;
    }
}
