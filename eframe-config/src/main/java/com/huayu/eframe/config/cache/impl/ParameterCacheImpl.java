package com.huayu.eframe.config.cache.impl;

import com.huayu.eframe.server.cache.frame.AbstractFrameCache;
import com.huayu.eframe.server.cache.frame.DefaultIndex;
import com.huayu.eframe.server.cache.frame.Index;
import com.huayu.eframe.config.bo.Parameter;
import com.huayu.eframe.config.cache.ParameterCache;
import com.huayu.eframe.config.service.ParameterService;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2018/9/9.
 */
@Service
public class ParameterCacheImpl extends AbstractFrameCache<Parameter> implements ParameterCache
{
    @Autowired
    private ParameterService parameterService;

    public ParameterCacheImpl()
    {
        registerIndex(new ParameterCodeIndex());
    }

    static class ParameterCodeIndex implements Index<Parameter>
    {
        @Override
        public String getIndex(Parameter lang)
        {
            return lang.getParameterCode();
        }
    }

    @Override
    public List<Parameter> load()
    {
//        return parameterService.getAllParameter();
        return new ArrayList<>();
    }

    @Override
    public String cacheName()
    {
        return "SYS_PARAMETER";
    }

    @Override
    public String getParameterValue(String parameterCode)
    {
        Parameter parameter = getParameterByParameterCode(parameterCode);
        return null == parameter? null:parameter.getValue();
    }

    @Override
    public Parameter getParameterByParameterCode(String parameterCode)
    {
        Parameter parameter = new Parameter();
        parameter.setParameterCode(parameterCode);
        List<Parameter> result = getResultByIndex(ParameterCodeIndex.class, parameter);

        return CollectionUtils.getFirstElement(result);
    }

    @Override
    public List<Parameter> getAll()
    {
        List<Parameter> result = getResultByIndex(DefaultIndex.class, null);
        return result;
    }
}