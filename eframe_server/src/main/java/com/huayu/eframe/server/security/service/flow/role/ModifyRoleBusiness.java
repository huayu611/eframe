package com.huayu.eframe.server.security.service.flow.role;

import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.security.service.request.ModifyRoleRequest;
import com.huayu.eframe.server.security.service.response.RoleResponse;
import com.huayu.eframe.server.security.service.single.RoleService;
import com.huayu.eframe.server.security.service.single.bo.RoleDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2018/10/2.
 */
@Service("ModifyRoleBusiness")
public class ModifyRoleBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(ModifyRoleBusiness.class);

    private static final String RESULT = "ModifyRoleBusiness_RESULT";

    @Autowired
    private RoleService roleService;

    @Override
    public void execute(BusinessParameter param)
    {
        ModifyRoleRequest roleRequest = param.getRequest();
        RoleDetail roleDetail = roleService.modifyRole(buildRoleDetail(roleRequest),roleRequest.getPermissions());
        param.addParameter(RESULT,roleDetail);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        RoleDetail roleDetail = param.getParameter(RESULT);
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setRole(roleDetail);
        return roleResponse;
    }

    private RoleDetail buildRoleDetail(ModifyRoleRequest roleRequest)
    {
        RoleDetail roleDetail = new RoleDetail();
        roleDetail.setCode(roleRequest.getCode());
        roleDetail.setExp(roleRequest.getExp());
        roleDetail.setName(roleRequest.getName());
        roleDetail.setParent(roleRequest.getParent());
        roleDetail.setRemark(roleRequest.getRemark());
        roleDetail.setStatus(roleRequest.getStatus());
        return roleDetail;
    }


}
