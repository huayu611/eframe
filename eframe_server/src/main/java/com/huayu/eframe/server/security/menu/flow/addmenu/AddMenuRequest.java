package com.huayu.eframe.server.security.menu.flow.addmenu;

import com.huayu.eframe.server.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.flow.restful.EffectiveExpireDateTime;
import com.huayu.eframe.server.flow.restful.RestfulRequest;
import com.huayu.eframe.server.flow.restful.RestfulResponse;

/**
 * Created by Administrator on 2018/8/12.
 */
public class AddMenuRequest implements EffectiveExpireDateTime
{
    @EFrameRequest(required = true)
    private String menuCode;

    private String menuName;

    private String url;

    private String parentMenu;

    @EFrameRequest(defaultStrValue = "0")
    private String status;

    private String effectiveTime;

    private String expireTime;

    @EFrameRequest(required = true)
    private String level;

    public String getMenuCode()
    {
        return menuCode;
    }

    public void setMenuCode(String menuCode)
    {
        this.menuCode = menuCode;
    }

    public String getMenuName()
    {
        return menuName;
    }

    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getParentMenu()
    {
        return parentMenu;
    }

    public void setParentMenu(String parentMenu)
    {
        this.parentMenu = parentMenu;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getEffectiveTime()
    {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime)
    {
        this.effectiveTime = effectiveTime;
    }

    public String getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(String expireTime)
    {
        this.expireTime = expireTime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
