package com.huayu.eframe.config.lang.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.config.lang.entity.cache.LangTextCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/8/3.
 */
@Service
public class QueryLangTextBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "QueryLangTextBusiness_RESULT";

    @Autowired
    private LangTextCache langTextCache;

    @Override
    public void execute(BusinessParameter param)
    {

    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return langTextCache.queryAll();
    }
}
