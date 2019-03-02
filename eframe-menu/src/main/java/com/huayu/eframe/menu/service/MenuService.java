package com.huayu.eframe.menu.service;

import java.util.List;

/**
 * Created by Administrator on 2018/8/12.
 */
public interface MenuService
{
    List<MenuDetail> queryAllMenu();

    MenuDetail queryMenuByCode(String code);

    MenuDetail addMenu(MenuDetail menu);

    MenuDetail modifyMenu(MenuDetail menu);

    String deleteMenu(String menuCode);
}
