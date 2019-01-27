package com.huayu.eframe.server.security.menu.bo;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * Created by Administrator on 2018/8/12.
 */
public class MenuTree
{
    private Menu menu;

    private List<MenuTree> sonMenu;

    private Integer menuLevel;

    private boolean hasSonMenu;

    public Menu getMenu()
    {
        return menu;
    }

    public void setMenu(Menu menu)
    {
        this.menu = menu;
    }

    public List<MenuTree> getSonMenu()
    {
        return sonMenu;
    }

    public void setSonMenu(List<MenuTree> sonMenu)
    {
        this.sonMenu = sonMenu;
    }

    public Integer getMenuLevel()
    {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel)
    {
        this.menuLevel = menuLevel;
    }

    public boolean isHasSonMenu()
    {
        return hasSonMenu;
    }

    public void setHasSonMenu(boolean hasSonMenu)
    {
        this.hasSonMenu = hasSonMenu;
    }
}
