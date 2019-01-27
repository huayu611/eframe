package com.huayu.eframe.server.security.menu.flow.querymenu;

/**
 * Created by Administrator on 2018/8/12.
 */
public class QueryMenuRequest
{
    private String menuCode;

    private String url;

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
}
