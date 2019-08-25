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
public class AddDictEntityBusiness  extends AbstractExecuteBusiness
{
    private final static String RESULT = "AddDictEntityBusiness_RESULT";

    @Autowired
    private DictEntityService dictEntityService;

    @Override
    public void execute(BusinessParameter param)
    {
        AddDictEntityRequest addDictEntityRequest = param.getRequest();
        DictEntityDetail dictEntityDetail = buildDictEntityDetail(addDictEntityRequest);
        DictEntityDetail result = dictEntityService.addDictEntity(dictEntityDetail);
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return  param.getParameter(RESULT);
    }

    private DictEntityDetail buildDictEntityDetail(AddDictEntityRequest addDictEntityRequest)
    {
        DictEntityDetail dictEntityDetail = new DictEntityDetail();
        dictEntityDetail.setDictInnerCode(addDictEntityRequest.getDictInnerCode());
        dictEntityDetail.setDictName(addDictEntityRequest.getDictName());
        dictEntityDetail.setDictCode(addDictEntityRequest.getDictCode());

        return dictEntityDetail;
    }
}
