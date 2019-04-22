package com.huayu.eframe.menu.flow.modmenu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/2/12.
 */
public class ModifyMenuRequest
{
    @EFrameRequest(required = true,length = 128)
    private String code;

    @EFrameRequest(length = 128)
    private String name;

    @EFrameRequest(length = 128)
    private String path;

    @EFrameRequest(length = 128)
    private String parentMenu;

    @JsonIgnore
    private String status;

    @EFrameRequest(length = 128)
    private String redirect;

    @EFrameRequest(length = 64)
    private String icon;

    @EFrameRequest(length = 128)
    private String component;

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
