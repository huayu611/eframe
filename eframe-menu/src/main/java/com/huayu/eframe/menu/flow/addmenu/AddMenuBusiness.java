package com.huayu.eframe.menu.flow.addmenu;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.menu.service.MenuDetail;
import com.huayu.eframe.menu.service.MenuService;
import com.huayu.eframe.server.log.LogDebug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/8/12.
 */
@Service
public class AddMenuBusiness extends AbstractExecuteBusiness
{

    private static final LogDebug debug = new LogDebug(AddMenuBusiness.class);

    private static final String RESULT = "AddMenuBusiness_RESULT";

    @Autowired
    private MenuService menuService;


    @Override
    public void execute(BusinessParameter param)
    {
        AddMenuRequest addMenuRequest = param.getRequest();
        MenuDetail menuDetail = buildMenuDetail(addMenuRequest);
        MenuDetail menuResult = menuService.addMenu(menuDetail);
        param.addParameter(RESULT, menuResult);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);

    }

    private MenuDetail buildMenuDetail(AddMenuRequest request)
    {
        MenuDetail menuDetail = new MenuDetail();
        menuDetail.setParentMenu(request.getParentMenu());
        menuDetail.setIcon(request.getIcon());
        menuDetail.setRedirect(request.getRedirect());
        menuDetail.setName(request.getName());
        menuDetail.setPath(request.getPath());
        menuDetail.setComponent(request.getComponent());
        menuDetail.setCode(request.getCode());
        menuDetail.setStatus(request.getStatus());
        menuDetail.setRange(request.getRange());
        return menuDetail;
    }
}
