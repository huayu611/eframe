package com.huayu.eframe.management.flow.staff;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.management.common.constants.ManagementErrorCode;
import com.huayu.eframe.management.request.QueryStaffRequest;
import com.huayu.eframe.management.single.StaffService;
import com.huayu.eframe.management.single.bo.StaffDetail;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2018/10/3.
 */
@Service("QueryStaffByCodeBusiness")
public class QueryStaffByCodeBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "QueryStaffByCodeBusiness_Result";

    private static final LogDebug debug = new LogDebug(QueryStaffByCodeBusiness.class);

    @Autowired
    private StaffService staffService;


    @Override
    public void before(BusinessParameter param)
    {
        QueryStaffRequest request = param.getRequest();
        String loginName = request.getLogin();
        if (StringUtils.isNullOrEmpty(loginName))
        {
            throw new IFPException(ManagementErrorCode.LOGINNAME_CANNOT_NULL, "Login name can not be null or empty!");
        }
    }


    @Override
    public void execute(BusinessParameter param)
    {
        debug.beginLog();

        QueryStaffRequest request = param.getRequest();


        StaffDetail details = staffService.queryStaffDetail(request.getLogin());


        param.addParameter(RESULT, details);

    }

    @Override
    public Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }

}
