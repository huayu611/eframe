package com.huayu.eframe.config.lang.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.config.lang.entity.service.LangTextService;
import com.huayu.eframe.config.lang.entity.service.LangTextValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/3.
 */
@Service
public class ModifyLangTextBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "ModifyLangTextBusiness_RESULT";

    @Autowired
    private LangTextService langTextService;

    @Override
    public void execute(BusinessParameter param)
    {
        ModifyLangTextRequest modifyLangValueRequest = param.getRequest();
        LangTextValue result = langTextService.batchModifyLangValue(buildLangTextValue(modifyLangValueRequest));
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }

    private LangTextValue buildLangTextValue(ModifyLangTextRequest modifyLangValueRequest)
    {
        LangTextValue langTextValue = new LangTextValue();
        langTextValue.setForeignCode(modifyLangValueRequest.getForeignCode());
        langTextValue.setValues(modifyLangValueRequest.getValues());
        return langTextValue;
    }
}
