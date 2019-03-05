package com.huayu.eframe.menu.flow.modmenu;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.menu.service.MenuDetail;
import com.huayu.eframe.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/2/12.
 */
@Service
public class ModifyMenuBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "ModifyMenuBusiness_RESULT";
    @Autowired
    private MenuService menuService;

    @Override
    public void execute(BusinessParameter param)
    {
        ModifyMenuRequest modifyMenuRequest = param.getRequest();
        MenuDetail menuDetail = buildMenuDetail(modifyMenuRequest);
        MenuDetail updateResult = menuService.modifyMenu(menuDetail);
        param.addParameter(RESULT,updateResult);

    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }

    private MenuDetail buildMenuDetail(ModifyMenuRequest request)
    {
        MenuDetail menuDetail = new MenuDetail();
        menuDetail.setParentMenu(request.getParentMenu());
        menuDetail.setIcon(request.getIcon());
        menuDetail.setRedirect(request.getRedirect());
        menuDetail.setPath(request.getPath());
        menuDetail.setName(request.getName());
        menuDetail.setComponent(request.getComponent());
        menuDetail.setCode(request.getCode());
        menuDetail.setStatus(request.getStatus());
        return menuDetail;
    }
}
