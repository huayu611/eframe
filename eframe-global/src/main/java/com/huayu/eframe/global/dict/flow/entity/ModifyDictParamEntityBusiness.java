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
public class ModifyDictParamEntityBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "ModifyDictParamEntityBusiness_RESULT";

    @Autowired
    private DictEntityService dictEntityService;

    @Override
    public void execute(BusinessParameter param)
    {
        ModifyDictParamEntityRequest modifyDictParamEntityRequest = param.getRequest();
        DictParamDetail dictParamDetail = buildDictEntityParamDetail(modifyDictParamEntityRequest);
        DictParamDetail result = dictEntityService.updateDictParam(dictParamDetail);
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return  param.getParameter(RESULT);
    }

    private DictParamDetail buildDictEntityParamDetail(ModifyDictParamEntityRequest modifyDictParamEntityRequest)
    {
        DictParamDetail dictParamDetail = new DictParamDetail();
        dictParamDetail.setDictInnerCode(modifyDictParamEntityRequest.getDictInnerCode());
        dictParamDetail.setKey(modifyDictParamEntityRequest.getKey());
        dictParamDetail.setOrder(modifyDictParamEntityRequest.getOrder());
        dictParamDetail.setValue(modifyDictParamEntityRequest.getValue());
        dictParamDetail.setCode(modifyDictParamEntityRequest.getCode());
        return dictParamDetail;
    }
}
