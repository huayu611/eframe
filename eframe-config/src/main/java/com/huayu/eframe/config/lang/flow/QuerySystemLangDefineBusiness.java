package com.huayu.eframe.config.lang.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.common.i18n.table.cache.LangDefineCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/2.
 */
@Service
public class QuerySystemLangDefineBusiness extends AbstractExecuteBusiness
{
    @Autowired
    private LangDefineCache langDefineCache;

    @Override
    public void execute(BusinessParameter param)
    {

    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return langDefineCache.queryAllDefineLanguage();
    }
}
