package com.huayu.eframe.config.flow.modify;

import com.huayu.eframe.config.service.ParameterDetail;
import com.huayu.eframe.config.service.ParameterService;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/3/1.
 */
@Service
public class ModifyParameterBusiness  extends AbstractExecuteBusiness
{
    private final static String RESULT = "ModifyParameterBusiness_RESULT";

    @Autowired
    private ParameterService parameterService;
    @Override
    public void execute(BusinessParameter param)
    {
        ModifyParameterRequest modifyParameterRequest = param.getRequest();
        ParameterDetail parameterDetail = buildParameterDetail(modifyParameterRequest);
        ParameterDetail newParameterDetail = parameterService.updateParameter(parameterDetail);
        param.addParameter(RESULT,newParameterDetail);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
       return param.getParameter(RESULT);

    }

    private ParameterDetail buildParameterDetail(ModifyParameterRequest modifyParameterRequest)
    {
        ParameterDetail parameterDetail = new ParameterDetail();
        parameterDetail.setParameterCode(modifyParameterRequest.getParameterCode());
        parameterDetail.setParameterName(modifyParameterRequest.getParameterName());
        parameterDetail.setParameterValue(modifyParameterRequest.getParameterValue());
        return parameterDetail;
    }
}
