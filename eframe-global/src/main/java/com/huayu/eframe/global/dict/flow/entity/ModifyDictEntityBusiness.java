package com.huayu.eframe.global.dict.flow.entity;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.global.dict.entity.service.DictEntityDetail;
import com.huayu.eframe.global.dict.entity.service.DictEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/24.
 */
@Service
public class ModifyDictEntityBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "ModifyDictEntityBusiness_RESULT";

    @Autowired
    private DictEntityService dictEntityService;

    @Override
    public void execute(BusinessParameter param)
    {
        ModifyDictEntityRequest modifyDictEntityRequest = param.getRequest();
        DictEntityDetail dictEntityDetail = buildDictEntityDetail(modifyDictEntityRequest);
        DictEntityDetail result = dictEntityService.updateDictEntity(dictEntityDetail);
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return  param.getParameter(RESULT);
    }

    private DictEntityDetail buildDictEntityDetail(ModifyDictEntityRequest modifyDictEntityRequest)
    {
        DictEntityDetail dictEntityDetail = new DictEntityDetail();
        dictEntityDetail.setDictInnerCode(modifyDictEntityRequest.getDictInnerCode());
        dictEntityDetail.setDictCode(modifyDictEntityRequest.getDictCode());
        dictEntityDetail.setDictName(modifyDictEntityRequest.getDictName());
        return dictEntityDetail;
    }
}
