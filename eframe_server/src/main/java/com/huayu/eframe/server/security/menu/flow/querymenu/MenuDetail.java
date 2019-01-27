package com.huayu.eframe.server.security.menu.flow.querymenu;

import java.util.List;

/**
 * Created by Administrator on 2018/8/12.
 */
public class MenuDetail
{
    private String menuCode;

    private String url;

    private String menuName;

    private String status;

    private List<MenuDetail> sonMenu;

    public String getMenuCode()
    {
        return menuCode;
    }

    public void setMenuCode(String menuCode)
    {
        this.menuCode = menuCode;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getMenuName()
    {
        return menuName;
    }

    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public List<MenuDetail> getSonMenu()
    {
        return sonMenu;
    }

    public void setSonMenu(List<MenuDetail> sonMenu)
    {
        this.sonMenu = sonMenu;
    }
}
