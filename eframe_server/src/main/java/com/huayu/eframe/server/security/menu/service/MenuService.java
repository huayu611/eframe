package com.huayu.eframe.server.security.menu.service;

import com.huayu.eframe.server.security.menu.bo.Menu;
import com.huayu.eframe.server.security.menu.bo.MenuTree;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/8/12.
 */
public interface MenuService
{
    List<MenuDetail> queryAllMenu();

    MenuDetail addMenu(MenuDetail menu);

    MenuDetail modifyMenu(MenuDetail menu);

    String deleteMenu(String menuCode);
}
