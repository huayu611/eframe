package com.huayu.eframe.menu.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Leo on 2019/2/11.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuDetail
{
    //解决react中的key问题
    private String key;

    private String code;

    private String name;

    private String path;

    private String component;

    private String redirect;

    private String icon;

    private String parentMenu;

    private String status;

    private Integer range;

    @JsonProperty(value = "children")
    private List<MenuDetail> routes;

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

    public String getComponent()
    {
        return component;
    }

    public void setComponent(String component)
    {
        this.component = component;
    }

    public String getRedirect()
    {
        return redirect;
    }

    public void setRedirect(String redirect)
    {
        this.redirect = redirect;
    }

    public List<MenuDetail> getRoutes()
    {
        return routes;
    }

    public void setRoutes(List<MenuDetail> routes)
    {
        this.routes = routes;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getParentMenu()
    {
        return parentMenu;
    }

    public void setParentMenu(String parentMenu)
    {
        this.parentMenu = parentMenu;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
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
