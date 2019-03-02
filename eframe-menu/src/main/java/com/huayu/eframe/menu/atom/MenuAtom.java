package com.huayu.eframe.menu.atom;

import com.huayu.eframe.menu.bo.Menu;

import java.util.Date;
import java.util.List;

/**
 * Created by Leo on 2019/2/11.
 */
public interface MenuAtom
{
    Menu addMenu(Menu menu);

    Menu updateMenu(Menu menu);

    List<Menu> queryMenu(Date now,Integer level);

    List<Menu> queryMenuByParent(Date now,Long parentMenu);

    Menu queryMenuById(Long id);

    Menu queryMenuByCode(Date now,String code);

    void deleteMenu(Menu menu);
}
