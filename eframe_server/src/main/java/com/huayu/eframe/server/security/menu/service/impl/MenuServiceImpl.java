package com.huayu.eframe.server.security.menu.service.impl;

import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.security.menu.atom.MenuAtom;
import com.huayu.eframe.server.security.menu.bo.Menu;
import com.huayu.eframe.server.security.menu.service.MenuDetail;
import com.huayu.eframe.server.security.menu.service.MenuService;
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
    public Long addMenu(Menu menu, Date now)
    {
        return null;
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
//
////    @Override
//    public MenuTree getMenuTree(String menuCode)
//    {
//        Menu conditionMenu = null;
//        if(StringUtils.isEmpty(menuCode))
//        {
//            conditionMenu = queryMenuByCode(menuCode);
//        }
//        List<Menu> allMenu = queryAllMenu();
//        if (CollectionUtils.isEmpty(allMenu))
//        {
//            return null;
//        }
//
//        Map<Long, List<Menu>> sonMenuMap = new HashMap<>();
//        for (Menu menu : allMenu)
//        {
//            //parent menu
//            List<Menu> parentMenu = sonMenuMap.get(menu.getParentMenu());
//            if (null == parentMenu)
//            {
//                sonMenuMap.put(menu.getParentMenu(), new ArrayList<>());
//            }
//            sonMenuMap.get(menu.getParentMenu()).add(menu);
//
//        }
//
//        return mergeTree(conditionMenu,sonMenuMap);
//    }
//
//    private MenuTree mergeTree(Menu rootMenu , Map<Long, List<Menu>> sonMenuMap)
//    {
//
//        MenuTree menuTree = new MenuTree();
//        if(null == rootMenu)
//        {
//            menuTree.setMenuLevel(0);
//            menuTree.setSonMenu(createSonMenu(sonMenuMap, 0L));
//            menuTree.setHasSonMenu(CollectionUtils.isNotEmpty(menuTree.getSonMenu()));
//        }
//        else
//        {
//            menuTree.setMenu(rootMenu);
//            menuTree.setMenuLevel(rootMenu.getMenuLevel());
//            menuTree.setSonMenu(createSonMenu(sonMenuMap, rootMenu.getMenuId()));
//            menuTree.setHasSonMenu(CollectionUtils.isNotEmpty(menuTree.getSonMenu()));
//        }
//        return menuTree;
//    }
//    private List<MenuTree> createSonMenu(Map<Long, List<Menu>> sonMenuMap,Long parentId)
//    {
//        List<MenuTree> menuTreeList = new ArrayList<>();
//        List<Menu> sonMenu = sonMenuMap.get(parentId);
//        if(CollectionUtils.isEmpty(sonMenu))
//        {
//            return null;
//        }
//        for(Menu sonMenuSingle :sonMenu )
//        {
//            MenuTree mt = new MenuTree();
//            mt.setMenuLevel(sonMenuSingle.getMenuLevel());
//            mt.setMenu(sonMenuSingle);
//            mt.setSonMenu(createSonMenu(sonMenuMap, sonMenuSingle.getMenuId()));
//            mt.setHasSonMenu(CollectionUtils.isNotEmpty(mt.getSonMenu()));
//            menuTreeList.add(mt);
//        }
//        return menuTreeList;
//    }
//
//    @Override
//    public Long addMenu(Menu menu, Date now)
//    {
//        now = null == now ? DateUtils.getCurrentDate() : now;
//        menu.setCreateTime(now);
//        menu.setEffectiveTime(null == menu.getEffectiveTime()?now : menu.getEffectiveTime());
//        menu.setLastUpdateTime(now);
//        menu.setExpireTime(null == menu.getExpireTime()?DateUtils.getDefaultExpireDate() : menu.getExpireTime());
//        Menu menuPresist = menuRepository.save(menu);
//        return menuPresist.getMenuId();
//    }
//
//    public Menu queryMenuByCode(String menuCode)
//    {
//        Menu menu = new Menu();
//        menu.setCode(menuCode);
//
//
//        ExampleMatcher matcher = ExampleMatcher.matching();
//        Example<Menu> example = Example.of(menu ,matcher);
//
//        List<Menu> staffRoleReturn = menuRepository.findAll(example);
//        return CollectionUtils.getFirstElement(staffRoleReturn);
//    }
}
