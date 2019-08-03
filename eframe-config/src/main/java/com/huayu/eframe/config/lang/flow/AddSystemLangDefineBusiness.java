package com.huayu.eframe.config.lang.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.common.i18n.table.service.LangDefineDetail;
import com.huayu.eframe.server.common.i18n.table.service.LangDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/2.
 */
@Service
public class AddSystemLangDefineBusiness extends AbstractExecuteBusiness
{

    private final static String RESULT = "AddSystemLangDefineBusiness_RESULT";

    @Autowired
    private LangDefineService langDefineService;

    @Override
    public void execute(BusinessParameter param)
    {
        AddSystemLangDefineRequest addSystemLangDefineRequest = param.getRequest();
        LangDefineDetail langDefineDetail = new LangDefineDetail(addSystemLangDefineRequest.getLanguageCode(),addSystemLangDefineRequest.getLanguageName());
        LangDefineDetail result = langDefineService.addLangDefine(langDefineDetail);
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }
}
