package com.huayu.eframe.global.system.lang;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.config.lang.entity.cache.LangDefineCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/7/22.
 */
@Service
public class QuerySystemLangBusiness extends AbstractExecuteBusiness
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
