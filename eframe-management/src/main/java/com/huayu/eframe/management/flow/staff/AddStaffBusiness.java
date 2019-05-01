package com.huayu.eframe.management.flow.staff;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.global.multipart.MultipartResolve;
import com.huayu.eframe.management.common.avatar.StaffAvatar;
import com.huayu.eframe.management.common.constants.ManagementErrorCode;
import com.huayu.eframe.management.request.AddStaffRequest;
import com.huayu.eframe.management.single.StaffService;
import com.huayu.eframe.management.single.bo.StaffDetail;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/7/21.
 */
@Service
public class AddStaffBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(AddStaffBusiness.class);


    private final static String STAFF_DETAIL_INFO_LOGIN = "AddStaffBusiness_StaffDetailInfo_LOGIN";
    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffAvatar staffAvatar;

    @Override
    public void before(BusinessParameter param)
    {
        debug.beginLog();
        AddStaffRequest addStaff = param.getRequest();
        Boolean result = staffService.checkExistStaff(addStaff.getLogin());
        debug.log(result);
        if (result)
        {
            String[] exceptionParam = new String[]{addStaff.getLogin()};
            throw new IFPException(ManagementErrorCode.ADD_STAFF_LOGIN_NAME_EXIST, "Login name exist already!", exceptionParam);
        }
        debug.endLog();
    }

    @Override
    public void execute(BusinessParameter param)
    {
        debug.beginLog();
        AddStaffRequest addStaff = param.getRequest();
        StaffDetail staffDetail = buildStaffDetail(addStaff);
        String roleString = addStaff.getRoles();
        StaffDetail staffReturn = staffService.addStaff(staffDetail, addStaff.getPassword(), roleString);
        if (StringUtils.isNotNullAndEmpty(addStaff.getAvatar()))
        {
            MultipartResolve.use(addStaff.getAvatar(), staffAvatar);
        }
        param.addParameter(STAFF_DETAIL_INFO_LOGIN, staffReturn);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(STAFF_DETAIL_INFO_LOGIN);

    }

    private StaffDetail buildStaffDetail(AddStaffRequest addStaff)
    {
        StaffDetail staffDetail = new StaffDetail();
        staffDetail.setEff(addStaff.getEff());
        staffDetail.setEmail(addStaff.getEmail());
        staffDetail.setExp(addStaff.getExp());
        staffDetail.setLang(addStaff.getLang());
        staffDetail.setLogin(addStaff.getLogin());
        staffDetail.setRemark(addStaff.getRemark());
        staffDetail.setName(addStaff.getName());
        staffDetail.setStatus(addStaff.getStatus());
        staffDetail.setTel(addStaff.getTel());
        staffDetail.setWechat(addStaff.getWechat());
        staffDetail.setWeibo(addStaff.getWeibo());
        staffDetail.setQq(addStaff.getQq());
        staffDetail.setAlipay(addStaff.getAlipay());
        staffDetail.setAvatar(addStaff.getAvatar());
        staffDetail.setSignature(addStaff.getSignature());
        staffDetail.setIdentityId(addStaff.getIdentityId());
        staffDetail.setRealName(addStaff.getRealName());
        staffDetail.setNickName(addStaff.getNickName());
        staffDetail.setOtherTelNumber(addStaff.getOtherTelNumber());
        staffDetail.setBirthday(addStaff.getBirthday());
        staffDetail.setGender(addStaff.getGender());

        return staffDetail;
    }

}
