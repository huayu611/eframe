package com.huayu.eframe.menu.flow.addmenu;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Administrator on 2018/8/12.
 */
public class AddMenuRequest
{
    @EFrameRequest(bean = "_e_eframe_menucode")
    private String code;

    @EFrameRequest(required = true)
    private String name;

    private String path;

    private String parentMenu;

    @EFrameRequest(defaultStrValue = "0")
    private String status;

    private String redirect;

    private String icon;

    private String component;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
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

    public String getRedirect()
    {
        return redirect;
    }

    public void setRedirect(String redirect)
    {
        this.redirect = redirect;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getComponent()
    {
        return component;
    }

    public void setComponent(String component)
    {
        this.component = component;
    }
}
