package com.huayu.eframe.config.lang.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.config.lang.entity.service.LangShortService;
import com.huayu.eframe.config.lang.entity.service.LangShortValue;
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
    private LangShortService langService;

    @Override
    public void execute(BusinessParameter param)
    {
        AddLangValueRequest addLangValueRequest = param.getRequest();
        LangShortValue result = langService.batchAddLangValue(buildLangValue(addLangValueRequest));
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }

    private LangShortValue buildLangValue(AddLangValueRequest addLangValueRequest)
    {
        LangShortValue langValue = new LangShortValue();
        langValue.setForeignCode(addLangValueRequest.getForeignCode());
        langValue.setValues(addLangValueRequest.getValues());
        return langValue;
    }
}
