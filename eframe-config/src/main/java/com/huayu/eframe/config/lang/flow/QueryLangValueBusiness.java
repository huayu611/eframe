package com.huayu.eframe.config.lang.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.config.lang.entity.cache.LangValueCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/3.
 */
@Service
public class QueryLangValueBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "QueryLangValueBusiness_RESULT";

    @Autowired
    private LangValueCache langCache;

    @Override
    public void execute(BusinessParameter param)
    {

    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return langCache.queryAll();
    }
}
