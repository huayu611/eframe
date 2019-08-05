package com.huayu.eframe.config.lang.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.config.lang.entity.service.LangTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/3.
 */
@Service
public class RemoveTextValueBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "RemoveTextValueBusiness_RESULT";

    @Autowired
    private LangTextService langTextService;

    @Override
    public void execute(BusinessParameter param)
    {
        RemoveTextValueRequest removeLangValueRequest = param.getRequest();
        String result = langTextService.removeLangValueByCode(removeLangValueRequest.getForeignCode());
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }
}
