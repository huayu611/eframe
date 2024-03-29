package com.huayu.eframe.config.lang.flow;

import com.huayu.eframe.config.lang.entity.service.LangShortService;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/3.
 */
@Service
public class RemoveLangValueBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "RemoveLangValueBusiness_RESULT";

    @Autowired
    private LangShortService langService;

    @Override
    public void execute(BusinessParameter param)
    {
        RemoveLangValueRequest removeLangValueRequest = param.getRequest();
        String result = langService.removeLangValueByCode(removeLangValueRequest.getForeignCode());
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }
}
