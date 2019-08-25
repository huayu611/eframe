package com.huayu.eframe.global.dict.flow.entity;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.global.dict.entity.service.DictEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/25.
 */
@Service
public class RemoveDictParamEntityBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "RemoveDictParamEntityBusiness_RESULT";

    @Autowired
    private DictEntityService dictEntityService;

    @Override
    public void execute(BusinessParameter param)
    {
        RemoveDictParamEntityRequest removeDictParamEntityRequest = param.getRequest();
        String result = dictEntityService.removeDictParam(removeDictParamEntityRequest.getCode());
        param.addParameter(RESULT, result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }
}
