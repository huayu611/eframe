package com.huayu.eframe.global.dict.flow.entity;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.global.dict.entity.service.DictEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/24.
 */
@Service
public class RemoveDictEntityBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "RemoveDictEntityBusiness_RESULT";

    @Autowired
    private DictEntityService dictEntityService;

    @Override
    public void execute(BusinessParameter param)
    {
        RemoveDictEntityRequest removeDictEntityRequest = param.getRequest();
        String result = dictEntityService.removeDictEntity(removeDictEntityRequest.getDictInnerCode());
        param.addParameter(RESULT, result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }
}
