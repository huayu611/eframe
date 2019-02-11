package com.huayu.eframe.server.security.menu.flow.querymenu;

import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.security.menu.bo.MenuTree;
import com.huayu.eframe.server.security.menu.service.MenuDetail;
import com.huayu.eframe.server.security.menu.service.MenuService;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/12.
 */
@Service
public class QueryMenuBusiness extends AbstractExecuteBusiness
{

    private static final LogDebug debug = new LogDebug(QueryMenuBusiness.class);

    private static final String RESULT = "QueryMenuBusiness_RESULT";

    @Autowired
    private MenuService menuService;

    @Override
    public void execute(BusinessParameter param)
    {
        List<MenuDetail> menuResult = menuService.queryAllMenu();
        param.addParameter(RESULT,menuResult);

    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        QueryMenuResponse queryMenuResponse = new QueryMenuResponse();
        List<MenuDetail> menuResult = param.getParameter(RESULT);
        queryMenuResponse.setMenus(menuResult);
        return queryMenuResponse;
    }


    //    private QueryMenuResponse convertToResponse(MenuTree menuTree)
//    {
//        debug.beginLog();
//
//        QueryMenuResponse response = new QueryMenuResponse();
//        if(null == menuTree)
//        {
//            debug.endLog();
//            return response;
//        }
//        List<MenuDetail> menuDetails = null;
//        if (null == menuTree.getMenu())
//        {
//            menuDetails = converMenuDetail(menuTree.getSonMenu());
//        }
//        else
//        {
//            List<MenuTree>  mtl = new ArrayList<>();
//            mtl.add(menuTree);
//            menuDetails = converMenuDetail(mtl);
//        }
//        response.setMenuDetails(menuDetails);
//        debug.endLog();
//        return response;
//    }
//
//    private List<MenuDetail> converMenuDetail(List<MenuTree> menuTree)
//    {
//        debug.beginLog();
//        if(CollectionUtils.isEmpty(menuTree))
//        {
//            debug.endLog();
//            return null;
//        }
//        List<MenuDetail> menuDetails = new ArrayList<>();
//        for(MenuTree mt : menuTree)
//        {
//            MenuDetail ud = new MenuDetail();
//            ud.setStatus(mt.getMenu().getStatus());
//            ud.setMenuName(mt.getMenu().getMenuName());
//            ud.setMenuCode(mt.getMenu().getCode());
//            ud.setUrl(mt.getMenu().getMenuUrl());
//            if(mt.isHasSonMenu())
//            {
//                ud.setSonMenu(converMenuDetail(mt.getSonMenu()));
//            }
//            menuDetails.add(ud);
//        }
//        debug.endLog();
//        return menuDetails;
//    }
}
