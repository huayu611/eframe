package com.huayu.eframe.config.flow.addparameter;

import com.huayu.eframe.config.flow.common.ParameterResponse;
import com.huayu.eframe.config.service.ParameterDetail;
import com.huayu.eframe.config.service.ParameterService;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.log.LogDebug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Add by Leo at 2018-08-25
 */
@Service
public class AddParameterBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(AddParameterBusiness.class);

    private final static String RESULT = "AddParameterBusiness_RESULT";

    @Autowired
    private ParameterService parameterService;

    @Override
    public void before(BusinessParameter param)
    {

    }

    @Override
    public void execute(BusinessParameter param)
    {
        AddParameterRequest addParameterRequest = param.getRequest();
        ParameterDetail parameterDetail = buildParameterDetail(addParameterRequest);
        ParameterDetail addResultParameterDetail = parameterService.addParameter(parameterDetail);
        param.addParameter(RESULT,addResultParameterDetail);

    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        ParameterResponse parameterResponse = new ParameterResponse();
        ParameterDetail parameterDetail = param.getParameter(RESULT);
        parameterResponse.setDetail(parameterDetail);
        return parameterResponse;
    }

    private ParameterDetail buildParameterDetail(AddParameterRequest addParameterRequest)
    {
        ParameterDetail parameterDetail = new ParameterDetail();
        parameterDetail.setParameterValue(addParameterRequest.getParameterValue());
        parameterDetail.setParameterName(addParameterRequest.getParameterName());
        parameterDetail.setParameterCode(addParameterRequest.getParameterCode());
        return parameterDetail;
    }


}