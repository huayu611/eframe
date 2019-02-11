package com.huayu.eframe.server.security.menu.flow.addmenu;

import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.security.menu.bo.Menu;
import com.huayu.eframe.server.security.menu.service.MenuService;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/8/12.
 */
@Service("AddMenuBusiness")
public class AddMenuBusiness extends AbstractExecuteBusiness
{

    private static final LogDebug debug = new LogDebug(AddMenuBusiness.class);

    private String PARENT_MENU = "ADD_MENU_PARENT_MENU";

    @Autowired
    private MenuService menuService;

    @Override
    public void before(BusinessParameter param)
    {
//        debug.beginLog();
//        AddMenuRequest addMenuRequest = param.getRequest();
//        String parentMenuCode = addMenuRequest.getParentMenu();
//        if(StringUtils.isNotNullAndEmpty(parentMenuCode)&& StringUtils.equalString("0",parentMenuCode))
//        {
//            Menu parentMenu = menuService.queryMenuByCode(parentMenuCode);
//            if (null == parentMenu)
//            {
//                throw new IFPException(ErrorCode.ADD_MENU_PARENT_MENU_NOT_EXIST);
//            }
//            param.addParameter(PARENT_MENU,parentMenu);
//        }
//        debug.endLog();

    }

    @Override
    public void execute(BusinessParameter param)
    {
//        debug.beginLog();
//        AddMenuRequest addMenuRequest = param.getRequest();
//        Menu menu = converMenuBO(addMenuRequest,param);
//        menuService.addMenu(menu, LocalAttribute.getNow());
//        debug.endLog();
    }

//    private Menu converMenuBO(AddMenuRequest request,BusinessParameter param)
//    {
//        debug.beginLog();
//        Menu menu = new Menu();
//        menu.setMenuName(request.getMenuName());
//        menu.setCode(request.getMenuCode());
//        menu.setMenuUrl(request.getUrl());
//        Menu parentMenu = param.getParameter(PARENT_MENU);
//        if(null == parentMenu)
//        {
//            menu.setParentMenu(0l);
//            menu.setTopMenu(0l);
//        }
//        else
//        {
//            menu.setParentMenu(parentMenu.getMenuId());
//            menu.setTopMenu(parentMenu.getTopMenu());
//        }
//        menu.setEffectiveTime(DateUtils.stringToDate(request.getEffectiveTime()));
//        menu.setExpireTime(DateUtils.stringToDate(request.getExpireTime()));
//        menu.setStatus(StringUtils.isNullOrEmpty(request.getStatus())?"0":request.getStatus());
//        debug.log(menu);
//        debug.endLog();
//        return menu;
//
//    }
}
