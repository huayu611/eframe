package com.huayu.eframe.global.dict.flow.entity;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.global.dict.entity.service.DictEntityService;
import com.huayu.eframe.global.dict.entity.service.DictParamDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/25.
 */
@Service
public class AddDictParamEntityBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "AddDictParamEntityBusiness_RESULT";

    @Autowired
    private DictEntityService dictEntityService;

    @Override
    public void execute(BusinessParameter param)
    {
        AddDictParamEntityRequest addDictParamEntityRequest = param.getRequest();
        DictParamDetail dictParamDetail = buildDictEntityParamDetail(addDictParamEntityRequest);
        DictParamDetail result = dictEntityService.addDictParam(dictParamDetail);
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return  param.getParameter(RESULT);
    }

    private DictParamDetail buildDictEntityParamDetail(AddDictParamEntityRequest addDictParamEntityRequest)
    {
        DictParamDetail dictParamDetail = new DictParamDetail();
        dictParamDetail.setDictInnerCode(addDictParamEntityRequest.getDictInnerCode());
        dictParamDetail.setKey(addDictParamEntityRequest.getKey());
        dictParamDetail.setOrder(addDictParamEntityRequest.getOrder());
        dictParamDetail.setValue(addDictParamEntityRequest.getValue());
        return dictParamDetail;
    }
}
