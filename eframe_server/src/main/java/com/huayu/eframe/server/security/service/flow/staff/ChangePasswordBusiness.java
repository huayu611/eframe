package com.huayu.eframe.server.security.service.flow.staff;

import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.security.service.request.ModifyPasswordRequest;
import com.huayu.eframe.server.security.service.single.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2018/12/23.
 */
@Service
public class ChangePasswordBusiness extends AbstractExecuteBusiness
{
    @Autowired
    private StaffService staffService;

    @Override
    public void before(BusinessParameter param)
    {
        ModifyPasswordRequest modifyPasswordRequest = param.getRequest();
        staffService.checkLogin(modifyPasswordRequest.getLogin(),modifyPasswordRequest.getOldPassword());
    }

    @Override
    public void execute(BusinessParameter param)
    {
        ModifyPasswordRequest modifyPasswordRequest = param.getRequest();
        staffService.changePassword(modifyPasswordRequest.getLogin(),modifyPasswordRequest.getNewPassword());
    }
}
