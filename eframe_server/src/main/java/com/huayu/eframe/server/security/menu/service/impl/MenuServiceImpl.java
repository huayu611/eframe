package com.huayu.eframe.server.security.menu.service.impl;

import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.security.menu.atom.MenuAtom;
import com.huayu.eframe.server.security.menu.bo.Menu;
import com.huayu.eframe.server.security.menu.service.MenuDetail;
import com.huayu.eframe.server.security.menu.service.MenuService;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/8/12.
 */

@Service
public class MenuServiceImpl implements MenuService
{
    private final static Integer TOP_LEVEL = Integer.valueOf("0");
    @Autowired
    private MenuAtom menuAtom;

    @Override
    public List<MenuDetail> queryAllMenu()
    {
        List<MenuDetail> menuDetails = new ArrayList<>();
        List<Menu> allMenu = menuAtom.queryMenu(LocalAttribute.getNow(), TOP_LEVEL);
        if (CollectionUtils.isEmpty(allMenu))
        {
            return menuDetails;
        }
        for (Menu menu : allMenu)
        {
            MenuDetail menuDetail = buildMenuDetail(menu);
            menuDetails.add(menuDetail);
        }
        return menuDetails;
    }

    @Override
    public MenuDetail queryMenuByCode(String code)
    {
        Menu menu = getMenuByCode(code);
        if(null == menu)
        {
            return null;
        }
        MenuDetail menuDetail = buildMenuDetail(menu);
        return menuDetail;
    }

    @Override
    public MenuDetail addMenu(MenuDetail menuDetail)
    {
        Menu menu = buildMenu(menuDetail);
        if(null == menu)
        {
            return null;
        }
        Menu resultMenu = menuAtom.addMenu(menu);
        MenuDetail menuDetailResult = buildMenuDetail(resultMenu);
        return menuDetailResult;
    }

    @Override
    public MenuDetail modifyMenu(MenuDetail menuDetail)
    {
        Menu menu = getMenuByCode(menuDetail.getCode());
        buildModifyMenu(menu,menuDetail);
        Menu newMenu = menuAtom.updateMenu(menu);
        MenuDetail newMenuDetail = buildMenuDetail(newMenu);
        return newMenuDetail;
    }

    @Override
    public String deleteMenu(String menuCode)
    {
        if(StringUtils.isNullOrEmpty(menuCode))
        {
            return "";
        }
        Menu menu = getMenuByCode(menuCode);
        if(null != menu)
        {
            Menu expireMenu = expireMenu(menu);
            return expireMenu.getCode();
        }
        return "";
    }

    private Menu expireMenu(Menu menu)
    {
        menu.setStatus("D");
        menu.setExpireTime(LocalAttribute.getNow());
        fixUpdateItem(menu);
        Menu result = menuAtom.updateMenu(menu);
        return result;
    }

    private MenuDetail buildMenuDetail(Menu menu)
    {
        MenuDetail menuDetail = new MenuDetail();
        menuDetail.setCode(menu.getCode());
        menuDetail.setComponent(menu.getComponent());
        menuDetail.setName(menu.getMenuName());
        menuDetail.setPath(menu.getMenuPath());
        menuDetail.setRedirect(menu.getRedirect());
        menuDetail.setIcon(menu.getIcon());
        menuDetail.setKey(menu.getCode());
        Long parent = menu.getParentMenu();
        if (null != parent && !Long.valueOf(0).equals(parent))
        {
            Menu parentMenu = menuAtom.queryMenuById( parent);
            menuDetail.setParentMenu(null != parentMenu ? parentMenu.getCode() : null);
        }

        //routes
        List<Menu> sonMenu = menuAtom.queryMenuByParent(LocalAttribute.getNow(), menu.getMenuId());
        if (CollectionUtils.isEmpty(sonMenu))
        {
            return menuDetail;
        }
        List<MenuDetail> sonMenuDetail = new ArrayList<>();
        for (Menu son : sonMenu)
        {
            MenuDetail sonDetail = buildMenuDetail(son);
            sonMenuDetail.add(sonDetail);
        }
        menuDetail.setRoutes(sonMenuDetail);
        return menuDetail;
    }

    private Menu buildMenu(MenuDetail menuDetail)
    {
        if(null == menuDetail)
        {
            return null;
        }
        Menu menu = new Menu();
        buildBaseInfo(menuDetail, menu);

        fixCreateItem(menu);
        fixUpdateItem(menu);
        return menu;
    }

    private void buildBaseInfo(MenuDetail menuDetail, Menu menu)
    {
        menu.setCode(menuDetail.getCode());
        menu.setComponent(menuDetail.getComponent());
        menu.setMenuPath(menuDetail.getPath());
        menu.setIcon(menuDetail.getIcon());
        menu.setMenuName(menuDetail.getName());
        menu.setStatus("0");
        menu.setRedirect(menuDetail.getRedirect());
        //parent menu
        Menu parentMenu = getMenuByCode(menuDetail.getParentMenu());
        if(null != parentMenu)
        {
            menu.setParentMenu(parentMenu.getMenuId());
            menu.setMenuLevel(parentMenu.getMenuLevel() + 1);
        }
        else
        {
            menu.setMenuLevel(TOP_LEVEL);
        }
    }

    private void buildModifyMenu(Menu menu,MenuDetail menuDetail)
    {
        buildBaseInfo(menuDetail,menu);
        fixUpdateItem(menu);

        //parentMenu
    }

    private void fixCreateItem(Menu menu)
    {
        menu.setCreateTime(LocalAttribute.getNow());
        menu.setEffectiveTime(LocalAttribute.getNow());
        menu.setExpireTime(DateUtils.getDefaultExpireDate());
    }

    private void fixUpdateItem(Menu menu)
    {
        menu.setLastUpdateTime(LocalAttribute.getNow());
    }

    private Menu getMenuByCode(String code)
    {
        if(StringUtils.isNullOrEmpty(code))
        {
            return null;
        }
        Menu menu = menuAtom.queryMenuByCode(LocalAttribute.getNow(), code);
        return menu;
    }

}
