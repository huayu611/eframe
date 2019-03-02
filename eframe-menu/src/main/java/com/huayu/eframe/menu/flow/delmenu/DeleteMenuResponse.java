package com.huayu.eframe.menu.flow.delmenu;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Leo on 2019/2/12.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeleteMenuResponse
{

    private String menus;

    public String getMenus()
    {
        return menus;
    }

    public void setMenus(String menus)
    {
        this.menus = menus;
    }
}
