package com.huayu.eframe.management.flow.role;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.management.common.constants.ManagementErrorCode;
import com.huayu.eframe.management.request.AddRoleRequest;
import com.huayu.eframe.management.single.RoleService;
import com.huayu.eframe.management.single.bo.RoleDetail;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.IFPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/8/4.
 */
@Service
public class AddRoleBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(AddRoleBusiness.class);

    private static final String RESULT = "AddRoleBusiness_RESULT";

    @Autowired
    private RoleService roleService;

    @Override
    public void before(BusinessParameter param)
    {
        debug.log("AddRoleBusiness:before");
        AddRoleRequest request = param.getRequest();

        RoleDetail roleDetail = roleService.queryRoleByCode(request.getCode());
        if (null != roleDetail)
        {
            String[] exceptionParam = {request.getCode()};
            throw new IFPException(ManagementErrorCode.ADD_ROLE_ROLE_CODE_EXIST, "Role exists already!", exceptionParam);
        }
    }

    @Override
    public void execute(BusinessParameter param)
    {
        AddRoleRequest request = param.getRequest();
        RoleDetail roleDetail = buildRoleDetail(request);
        String permissionCodes = request.getPermissions();
        RoleDetail roleDetailReturn = roleService.addRole(roleDetail, permissionCodes);
        param.addParameter(RESULT, roleDetailReturn);

    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);

    }

    private RoleDetail buildRoleDetail(AddRoleRequest request)
    {
        RoleDetail roleDetail = new RoleDetail();
        roleDetail.setCode(request.getCode());
        roleDetail.setEff(request.getEff());
        roleDetail.setExp(request.getExp());
        roleDetail.setName(request.getName());
        roleDetail.setParent(request.getParent());
        roleDetail.setRemark(request.getRemark());
        roleDetail.setStatus(request.getStatus());
        roleDetail.setAddMenus(request.getAddMenus());
        roleDetail.setRemoveMenus(request.getRemoveMenus());
        return roleDetail;
    }
}
