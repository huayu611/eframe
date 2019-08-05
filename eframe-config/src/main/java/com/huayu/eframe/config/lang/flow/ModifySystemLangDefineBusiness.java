package com.huayu.eframe.config.lang.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.config.lang.entity.service.LangDefineDetail;
import com.huayu.eframe.config.lang.entity.service.LangDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/2.
 */
@Service
public class ModifySystemLangDefineBusiness  extends AbstractExecuteBusiness
{
    private final static String RESULT = "ModifySystemLangDefineBusiness_RESULT";

    @Autowired
    private LangDefineService langDefineService;

    @Override
    public void execute(BusinessParameter param)
    {
        ModifySystemLangDefineRequest modifySystemLangDefineRequest = param.getRequest();
        LangDefineDetail langDefineDetail = new LangDefineDetail(modifySystemLangDefineRequest.getLanguageCode(),modifySystemLangDefineRequest.getLanguageName());
        LangDefineDetail result = langDefineService.updateLangDefine(langDefineDetail);
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }
}
