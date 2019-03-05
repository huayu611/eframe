package com.huayu.eframe.management.flow.staff;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.management.request.DeleteStaffRequest;
import com.huayu.eframe.management.single.StaffService;
import com.huayu.eframe.server.log.LogDebug;
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

    private final static String RESULT = "DeleteStaffBusiness_RESULT";
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
        String result = deleteStaffInBatch(codeArr);
        param.addParameter(RESULT,result);
    }

    private String deleteStaffInBatch(String[] codeArr)
    {
        String result = "";
        for (String loginName : codeArr)
        {
            debug.log(loginName);
            String login = staffService.deleteStaff(loginName);
            result = result + login + ",";
        }
        if(result.endsWith(","))
        {
            result = result.substring(0,result.length()-1);
        }
        return result;
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {

       return param.getParameter(RESULT);

    }
}
