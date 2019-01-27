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
    List<Menu> queryAllMenu();

    MenuTree getMenuTree(String code);

    Long addMenu(Menu menu,Date now);

    Menu queryMenuByCode(String menuCode);


}
