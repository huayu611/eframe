package com.huayu.eframe.server.security.service.flow.staff;

import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.security.service.bo.Staff;
import com.huayu.eframe.server.security.service.request.ModifyStaffInformationRequest;
import com.huayu.eframe.server.security.service.response.ModifyStaffResponse;
import com.huayu.eframe.server.security.service.single.StaffService;
import com.huayu.eframe.server.security.service.single.bo.StaffDetail;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2018/9/28.
 */
@Service
public class ModifyStaffBusiness extends AbstractExecuteBusiness
{

    private static final LogDebug debug = new LogDebug(ModifyStaffBusiness.class);

    private final static String STAFF_INFO = "ModifyStaffInformationBusiness_StaffInfo";

    private final static String STAFF_DETAIL_INFO = "ModifyStaffInformationBusiness_StaffDetailInfo";


    @Autowired
    private StaffService staffService;

    @Override
    public void before(BusinessParameter param)
    {

        ModifyStaffInformationRequest request = param.getRequest();
        debug.log(request);
        String loginName = request.getLogin();
        StaffDetail staff = staffService.queryStaffDetail(loginName);
        if (null == staff)
        {
            String[] exceptionParam = new String[]{loginName};
            throw new IFPException(ErrorCode.STAFF_LOGIN_NAME_NOT_EXIST, "Login name not exist!", exceptionParam);
        }
        param.addParameter(STAFF_INFO, staff);
    }


    @Override
    public void execute(BusinessParameter param)
    {
        ModifyStaffInformationRequest modifyStaffInformationRequest = param.getRequest();
        debug.log(modifyStaffInformationRequest);
        StaffDetail staffDetail = buildStaffDetail(modifyStaffInformationRequest);
        StaffDetail newStaffDetail = staffService.updateStaff(staffDetail, modifyStaffInformationRequest.getRoles());
        param.addParameter(STAFF_DETAIL_INFO, newStaffDetail);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        StaffDetail staff = param.getParameter(STAFF_DETAIL_INFO);
        ModifyStaffResponse modifyStaffResponse = new ModifyStaffResponse();
        modifyStaffResponse.setStaff(staff);
        return modifyStaffResponse;
    }


    private StaffDetail buildStaffDetail(ModifyStaffInformationRequest request)
    {
        StaffDetail staffDetail = new StaffDetail();
        staffDetail.setLogin(request.getLogin());
        staffDetail.setEmail(request.getEmail());
        staffDetail.setEff(request.getEff());
        staffDetail.setExp(request.getExp());
        staffDetail.setStatus(request.getStatus());
        staffDetail.setLang(request.getLang());
        staffDetail.setName(request.getName());
        staffDetail.setTel(request.getTel());
        staffDetail.setRemark(request.getRemark());
        return staffDetail;
    }

}
