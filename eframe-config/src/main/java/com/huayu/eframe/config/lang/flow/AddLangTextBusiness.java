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
public class AddLangTextBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "AddLangTextBusiness_RESULT";

    @Autowired
    private LangTextService langTextService;

    @Override
    public void execute(BusinessParameter param)
    {
        AddLangTextRequest addLangTextRequest = param.getRequest();
        LangTextValue result = langTextService.batchAddLangValue(buildLangValue(addLangTextRequest));
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }

    private LangTextValue buildLangValue(AddLangTextRequest addLangValueRequest)
    {
        LangTextValue langTextValue = new LangTextValue();
        langTextValue.setForeignCode(addLangValueRequest.getForeignCode());
        langTextValue.setValues(addLangValueRequest.getValues());
        return langTextValue;
    }
}
