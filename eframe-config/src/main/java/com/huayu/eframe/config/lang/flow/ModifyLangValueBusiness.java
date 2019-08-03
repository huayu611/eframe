package com.huayu.eframe.config.lang.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.common.i18n.table.service.LangService;
import com.huayu.eframe.server.common.i18n.table.service.LangValue;
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
    private LangService langService;

    @Override
    public void execute(BusinessParameter param)
    {
        ModifyLangValueRequest modifyLangValueRequest = param.getRequest();
        LangValue result = langService.batchModifyLangValue(buildLangValue(modifyLangValueRequest));
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }

    private LangValue buildLangValue(ModifyLangValueRequest modifyLangValueRequest)
    {
        LangValue langValue = new LangValue();
        langValue.setForeignCode(modifyLangValueRequest.getForeignCode());
        langValue.setValues(modifyLangValueRequest.getValues());
        return langValue;
    }
}
