package com.huayu.eframe.menu.flow.addmenu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Administrator on 2018/8/12.
 */
public class AddMenuRequest
{
    @EFrameRequest(bean = "_e_eframe_menucode")
    @JsonIgnore
    private String code;

    @EFrameRequest(required = true,length = 64)
    private String name;
    @EFrameRequest(length = 128)
    private String path;

    @EFrameRequest(length = 128)
    private String parentMenu;

    @EFrameRequest(defaultStrValue = "0")
    @JsonIgnore
    private String status;

    @EFrameRequest(length = 128)
    private String redirect;

    @EFrameRequest(length = 64)
    private String icon;

    @EFrameRequest(length = 128)
    private String component;

    @EFrameRequest(defaultIntegerValue = 16)
    private Integer range;

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

    public Integer getRange()
    {
        return range;
    }

    public void setRange(Integer range)
    {
        this.range = range;
    }
}
