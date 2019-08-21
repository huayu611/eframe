package com.huayu.eframe.menu.cache;

import com.huayu.eframe.menu.service.MenuDetail;

import java.util.List;

/**
 * Created by Leo on 2019/8/21.
 */
public interface MenuDetailCache
{
    String CACHE_NAME = "SYS_MENU#DETAIL";

    MenuDetail queryMenuByCode(String menuCode);

    MenuDetail queryMenuByPath(String path);

    List<MenuDetail> queryAllMenu();
}
