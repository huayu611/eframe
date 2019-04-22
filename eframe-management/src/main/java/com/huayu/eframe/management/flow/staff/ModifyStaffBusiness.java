package com.huayu.eframe.management.flow.staff;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.management.common.constants.ManagementErrorCode;
import com.huayu.eframe.management.request.ModifyStaffInformationRequest;
import com.huayu.eframe.management.single.StaffService;
import com.huayu.eframe.management.single.bo.StaffDetail;
import com.huayu.eframe.server.log.LogDebug;
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
            throw new IFPException(ManagementErrorCode.STAFF_LOGIN_NAME_NOT_EXIST, "Login name not exist!", exceptionParam);
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
         return param.getParameter(STAFF_DETAIL_INFO);
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
        staffDetail.setWechat(request.getWechat());
        staffDetail.setWeibo(request.getWeibo());
        staffDetail.setQq(request.getQq());
        staffDetail.setAlipay(request.getAlipay());
        staffDetail.setAvatar(request.getAvatar());
        staffDetail.setSignature(request.getSignature());
        staffDetail.setIdentityId(request.getIdentityId());
        staffDetail.setRealName(request.getRealName());
        staffDetail.setNickName(request.getNickName());
        staffDetail.setOtherTelNumber(request.getOtherTelNumber());
        staffDetail.setBirthday(request.getBirthday());
        staffDetail.setGender(request.getGender());
        return staffDetail;
    }

}
