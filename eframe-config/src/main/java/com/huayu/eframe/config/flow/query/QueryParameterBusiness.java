package com.huayu.eframe.config.flow.query;

import com.huayu.eframe.config.service.ParameterDetail;
import com.huayu.eframe.config.service.ParameterService;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        param.addParameter(RESULT, parameters);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {

        return param.getParameter(RESULT);

    }
}
