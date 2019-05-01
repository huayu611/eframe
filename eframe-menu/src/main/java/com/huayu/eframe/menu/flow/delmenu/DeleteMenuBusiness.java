package com.huayu.eframe.menu.flow.delmenu;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.menu.service.MenuService;
import com.huayu.eframe.server.log.LogDebug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/2/12.
 */
@Service
public class DeleteMenuBusiness extends AbstractExecuteBusiness
{

    private static final LogDebug debug = new LogDebug(DeleteMenuBusiness.class);

    private static final String RESULT = "DeleteMenuBusiness_RESULT";

    private static final String MULTI_VALUE_ATTRIBUTE_DELIMITERS = ",; ";


    @Autowired
    private MenuService menuService;

    @Override
    public void execute(BusinessParameter param)
    {
        DeleteMenuRequest request = param.getRequest();
        String codes = request.getCodes();
        String result = deleteInBatch(code -> menuService.deleteMenu(code), codes);
        param.addParameter(RESULT, result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);

    }
}
