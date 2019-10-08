package com.huayu.eframe.menu.cache;

import com.huayu.eframe.menu.bo.Menu;

import java.util.List;

/**
 * Created by Leo on 2019/8/5.
 */
public interface MenuMetaCache
{
    String CACHE_NAME = "SYS_MENU#META";

    Menu queryMenuByCode(String code);

    Menu queryMenuById(Long id);

    Menu queryMenuByPath(String path);

    List<Menu> queryMenuByParent(Long id);

    List<Menu> queryAllMenu();
}
