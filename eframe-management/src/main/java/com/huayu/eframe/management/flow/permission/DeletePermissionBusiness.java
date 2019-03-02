package com.huayu.eframe.management.flow.permission;

import com.huayu.eframe.management.request.DeletePermissionRequest;
import com.huayu.eframe.management.single.PermissionService;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
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
