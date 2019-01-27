package com.huayu.eframe.server.security.service.flow.permission;

import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.security.service.request.DeletePermissionRequest;
import com.huayu.eframe.server.security.service.single.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * Created by Leo on 2018/10/17.
 */
@Service
public class DeletePermissionBusiness extends AbstractExecuteBusiness
{

    private static final String MULTI_VALUE_ATTRIBUTE_DELIMITERS = ",; ";
    @Autowired
    private PermissionService permissionService;

    @Override
    public void execute(BusinessParameter param)
    {
        DeletePermissionRequest request = param.getRequest();
        String codes = request.getCode();
        String[] nameArr = StringUtils.tokenizeToStringArray(codes, MULTI_VALUE_ATTRIBUTE_DELIMITERS);

        for(String code : nameArr)
        {
            permissionService.deletePermissionByCode(code);
        }
    }
}
