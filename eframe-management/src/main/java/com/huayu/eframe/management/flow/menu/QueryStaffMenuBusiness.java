package com.huayu.eframe.management.flow.menu;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.management.single.StaffService;
import com.huayu.eframe.menu.flow.querymenu.QueryMenuBusiness;
import com.huayu.eframe.menu.service.MenuDetail;
import com.huayu.eframe.server.log.LogDebug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/14.
 */
@Service
public class QueryStaffMenuBusiness  extends AbstractExecuteBusiness
{

    private static final LogDebug debug = new LogDebug(QueryMenuBusiness.class);

    private static final String RESULT_SINGLE = "QueryStaffMenuBusiness_Single";

    @Autowired
    private StaffService staffService;

    @Override
    public void execute(BusinessParameter param)
    {
        MenuDetail menuQuery = staffService.queryCurrentStaffMenuDetail();
        param.addParameter(RESULT_SINGLE, menuQuery);
    }
    @Override
    protected Object tidyData(BusinessParameter param)
    {
        MenuDetail menuDetail = param.getParameter(RESULT_SINGLE);
        return menuDetail;
    }




}
