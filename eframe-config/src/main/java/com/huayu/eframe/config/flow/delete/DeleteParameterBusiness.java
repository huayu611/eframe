package com.huayu.eframe.config.flow.delete;

import com.huayu.eframe.config.service.ParameterService;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/3/1.
 */
@Service
public class DeleteParameterBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "DeleteParameterBusiness_RESULT";
    @Autowired
    private ParameterService parameterService;

    @Override
    public void execute(BusinessParameter param)
    {
        String codes = param.getRequest();
        String result = deleteInBatch(code -> parameterService.deleteParameter(code), codes);
        param.addParameter(RESULT, result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {

        String result = param.getParameter(RESULT);
        return result;
    }
}
