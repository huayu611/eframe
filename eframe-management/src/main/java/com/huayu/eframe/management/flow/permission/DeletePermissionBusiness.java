package com.huayu.eframe.management.flow.permission;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.management.request.DeletePermissionRequest;
import com.huayu.eframe.management.single.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Leo on 2018/10/17.
 */
@Service
public class DeletePermissionBusiness extends AbstractExecuteBusiness
{

    private static final String RESULT = "DeletePermissionBusiness_RESULT";
    @Autowired
    private PermissionService permissionService;

    @Override
    public void execute(BusinessParameter param)
    {
        DeletePermissionRequest request = param.getRequest();
        String codes = request.getCode();
        String result = deleteInBatch(code -> permissionService.deletePermissionByCode(code), codes);
        param.addParameter(RESULT, result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }
}
