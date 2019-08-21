package com.huayu.eframe.menu.flow.querymenu;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.menu.bo.Menu;
import com.huayu.eframe.menu.cache.MenuDetailCache;
import com.huayu.eframe.menu.cache.MenuMetaCache;
import com.huayu.eframe.menu.service.MenuDetail;
import com.huayu.eframe.menu.service.MenuService;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/8/12.
 */
@Service
public class QueryMenuBusiness extends AbstractExecuteBusiness
{

    private static final LogDebug debug = new LogDebug(QueryMenuBusiness.class);

    private static final String RESULT_LIST = "QueryMenuBusiness_RESULT_List";

    private static final String RESULT_SINGLE = "QueryMenuBusiness_RESULT_Single";

    @Autowired
    private MenuDetailCache menuDetailCache;

    @Autowired
    private MenuMetaCache menuMetaCache;

    @Override
    public void execute(BusinessParameter param)
    {
        QueryMenuRequest queryMenuRequest = param.getRequest();
        if (StringUtils.isNotNullAndEmpty(queryMenuRequest.getCode()))
        {
            MenuDetail menuQuery = menuDetailCache.queryMenuByCode(queryMenuRequest.getCode());
            param.addParameter(RESULT_SINGLE, menuQuery);

        }
        else if (StringUtils.isNotNullAndEmpty(queryMenuRequest.getPath()))
        {
            Menu menu = menuMetaCache.queryMenuByPath(queryMenuRequest.getPath());
            MenuDetail menuResult = buildMenuDetail(menu);
            param.addParameter(RESULT_SINGLE, menuResult);
        }
        else
        {
            List<MenuDetail> menuResult = menuDetailCache.queryAllMenu();
            param.addParameter(RESULT_LIST, menuResult);
        }


    }

    private MenuDetail buildMenuDetail( Menu menu )
    {
        MenuDetail menuDetail = new MenuDetail();
        menuDetail.setStatus(menu.getStatus());
        menuDetail.setCode(menu.getCode());
        menuDetail.setRange(menu.getRange());
        menuDetail.setComponent(menu.getComponent());
        menuDetail.setPath(menu.getMenuPath());
        menuDetail.setName(menu.getMenuName());
        menuDetail.setKey(menu.getCode());
        menuDetail.setIcon(menu.getIcon());
        menuDetail.setRedirect(menu.getRedirect());
        Menu parent = menuMetaCache.queryMenuById(menu.getParentMenu());
        menuDetail.setParentMenu(null == parent ? null : parent.getCode());
        return menuDetail;
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        List<MenuDetail> menuResult = param.getParameter(RESULT_LIST);
        MenuDetail menuDetail = param.getParameter(RESULT_SINGLE);
        return null == menuResult ? menuDetail : menuResult;
    }
}
