package com.huayu.eframe.server.config.table.flow.delete;

import com.huayu.eframe.server.config.table.service.ParameterService;
import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/3/1.
 */
@Service
public class DeleteParameterBusiness  extends AbstractExecuteBusiness
{
    private final static String RESULT = "DeleteParameterBusiness_RESULT";
    @Autowired
    private ParameterService parameterService;
    @Override
    public void execute(BusinessParameter param)
    {
        String codes = param.getRequest();
        String result = deleteInBatch(code -> parameterService.deleteParameter(code),codes);
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        DeleteParameterResponse deleteParameterResponse = new DeleteParameterResponse();
        String result = param.getParameter(RESULT);
        deleteParameterResponse.setParameterCodes(result);
        return deleteParameterResponse;
    }
}
