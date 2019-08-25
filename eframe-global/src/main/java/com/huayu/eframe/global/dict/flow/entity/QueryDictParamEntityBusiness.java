package com.huayu.eframe.global.dict.flow.entity;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.global.dict.entity.service.DictEntityService;
import com.huayu.eframe.global.dict.entity.service.DictParamDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2019/8/25.
 */
@Service
public class QueryDictParamEntityBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "QueryDictParamEntityBusiness_RESULT";

    @Autowired
    private DictEntityService dictEntityService;

    @Override
    public void execute(BusinessParameter param)
    {
        QueryDictParamEntityRequest queryDictParamEntityRequest = param.getRequest();
        List<DictParamDetail> result = dictEntityService.queryDictParamByDictEntity(queryDictParamEntityRequest.getDictInnerCode());
        param.addParameter(RESULT,result);
    }



    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }
}
