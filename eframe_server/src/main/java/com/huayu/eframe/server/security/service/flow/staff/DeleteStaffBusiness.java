package com.huayu.eframe.server.security.service.flow.staff;

import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.security.service.request.DeleteStaffRequest;
import com.huayu.eframe.server.security.service.single.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2018/9/28.
 */
@Service
public class DeleteStaffBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(DeleteStaffBusiness.class);

    private static final String MULTI_VALUE_ATTRIBUTE_DELIMITERS = ",; ";

    private final static String STAFF_INFO = "DeleteStaffBusiness_StaffInfo";
    @Autowired
    private StaffService staffService;


    @Override
    public void execute(BusinessParameter param)
    {
        DeleteStaffRequest request = param.getRequest();
        String loginCodes = request.getLogin();
        String[] codeArr = org.springframework.util.StringUtils.tokenizeToStringArray(loginCodes, MULTI_VALUE_ATTRIBUTE_DELIMITERS);
        if (codeArr.length == 0)
        {
            return;
        }
        deleteStaffInBatch(codeArr);
    }

    private void deleteStaffInBatch(String[] codeArr)
    {
        for (String loginName : codeArr)
        {
            debug.log(loginName);
            staffService.deleteStaff(loginName);

        }
    }
}
