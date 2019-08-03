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
public class AddLangValueBusiness  extends AbstractExecuteBusiness
{
    private final static String RESULT = "AddLangValueBusiness_RESULT";

    @Autowired
    private LangService langService;

    @Override
    public void execute(BusinessParameter param)
    {
        AddLangValueRequest addLangValueRequest = param.getRequest();
        LangValue result = langService.batchAddLangValue(buildLangValue(addLangValueRequest));
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }

    private LangValue buildLangValue(AddLangValueRequest addLangValueRequest)
    {
        LangValue langValue = new LangValue();
        langValue.setForeignCode(addLangValueRequest.getForeignCode());
        langValue.setValues(addLangValueRequest.getValues());
        return langValue;
    }
}
