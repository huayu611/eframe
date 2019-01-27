package com.huayu.eframe.server.security.service.flow.role;

import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.security.service.request.DeleteRoleRequest;
import com.huayu.eframe.server.security.service.single.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Leo on 2018/9/29.
 */
@Service("DeleteRoleBusiness")
public class DeleteRoleBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(DeleteRoleBusiness.class);

    private static final String MULTI_VALUE_ATTRIBUTE_DELIMITERS = ",; ";

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
        for(String code : nameArr)
        {
            roleService.deleteRole(code);
        }

    }
}
