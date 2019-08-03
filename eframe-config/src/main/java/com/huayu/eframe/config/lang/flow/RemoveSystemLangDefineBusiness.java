package com.huayu.eframe.config.lang.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.common.i18n.table.service.LangDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/2.
 */
@Service
public class RemoveSystemLangDefineBusiness extends AbstractExecuteBusiness
{


    private final static String RESULT = "RemoveSystemLangDefineBusiness_RESULT";

    @Autowired
    private LangDefineService langDefineService;

    @Override
    public void execute(BusinessParameter param)
    {
        RemoveSystemLangDefineRequest removeSystemLangDefineRequest = param.getRequest();
        String result = langDefineService.removeLangDefine(removeSystemLangDefineRequest.getLanguageCode());
        param.addParameter(RESULT,result);

    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }
}
