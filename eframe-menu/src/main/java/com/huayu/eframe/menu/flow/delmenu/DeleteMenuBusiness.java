package com.huayu.eframe.menu.flow.delmenu;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        debug.log(codes);
        String[] nameArr = StringUtils.tokenizeToStringArray(codes, MULTI_VALUE_ATTRIBUTE_DELIMITERS);
        if(nameArr.length==0)
        {
            return;
        }
        String result = "";
        for(String code : nameArr)
        {
            String roleCode = menuService.deleteMenu(code);
            result = result + roleCode + ",";
        }
        if(result.endsWith(","))
        {
            result = result.substring(0,result.length()-1);
        }
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        DeleteMenuResponse menuResponse = new DeleteMenuResponse();
        String result = param.getParameter(RESULT);
        menuResponse.setMenus(result);
        return menuResponse;
    }
}
