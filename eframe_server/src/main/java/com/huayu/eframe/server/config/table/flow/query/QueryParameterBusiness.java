package com.huayu.eframe.server.config.table.flow.query;

import com.huayu.eframe.server.config.table.service.ParameterDetail;
import com.huayu.eframe.server.config.table.service.ParameterService;
import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

/**
 * Created by Leo on 2019/2/28.
 */
@Service
public class QueryParameterBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "QueryParameterBusiness_RESULT";

    @Autowired
    private ParameterService parameterService;
    @Override
    public void execute(BusinessParameter param)
    {
        List<ParameterDetail> parameters = parameterService.getAllParameter();
        param.addParameter(RESULT,parameters);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        QueryParameterResponse queryParameterResponse = new QueryParameterResponse();
        List<ParameterDetail> parameters = param.getParameter(RESULT);
        queryParameterResponse.setParameters(parameters);
        return queryParameterResponse;
    }
}
