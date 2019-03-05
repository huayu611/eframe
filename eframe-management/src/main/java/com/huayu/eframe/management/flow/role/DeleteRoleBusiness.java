package com.huayu.eframe.management.flow.role;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.management.request.DeleteRoleRequest;
import com.huayu.eframe.management.single.RoleService;
import com.huayu.eframe.server.log.LogDebug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Leo on 2018/9/29.
 */
@Service
public class DeleteRoleBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(DeleteRoleBusiness.class);

    private static final String MULTI_VALUE_ATTRIBUTE_DELIMITERS = ",; ";


    private final static String RESULT = "DeleteRoleBusiness_RESULT";
    @Autowired
    private RoleService roleService;


    @Override
    public void execute(BusinessParameter param)
    {
        DeleteRoleRequest request = param.getRequest();
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
            String roleCode = roleService.deleteRole(code);
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
        return param.getParameter(RESULT);


    }
}
