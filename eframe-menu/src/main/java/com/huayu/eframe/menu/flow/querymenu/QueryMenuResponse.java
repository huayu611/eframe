package com.huayu.eframe.menu.flow.querymenu;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.menu.service.MenuDetail;

import java.util.List;

/**
 * Created by Administrator on 2018/8/12.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QueryMenuResponse
{
    private List<MenuDetail> menus;

    private MenuDetail menu;

    public List<MenuDetail> getMenus()
    {
        return menus;
    }

    public void setMenus(List<MenuDetail> menus)
    {
        this.menus = menus;
    }

    public MenuDetail getMenu()
    {
        return menu;
    }

    public void setMenu(MenuDetail menu)
    {
        this.menu = menu;
    }
}
