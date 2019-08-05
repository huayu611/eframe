package com.huayu.eframe.config.lang.flow;

import com.huayu.eframe.config.lang.entity.service.LangShortService;
import com.huayu.eframe.config.lang.entity.service.LangShortValue;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/3.
 */
@Service
public class ModifyLangValueBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "ModifyLangValueBusiness_RESULT";

    @Autowired
    private LangShortService langService;

    @Override
    public void execute(BusinessParameter param)
    {
        ModifyLangValueRequest modifyLangValueRequest = param.getRequest();
        LangShortValue result = langService.batchModifyLangValue(buildLangValue(modifyLangValueRequest));
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }

    private LangShortValue buildLangValue(ModifyLangValueRequest modifyLangValueRequest)
    {
        LangShortValue langValue = new LangShortValue();
        langValue.setForeignCode(modifyLangValueRequest.getForeignCode());
        langValue.setValues(modifyLangValueRequest.getValues());
        return langValue;
    }
}
