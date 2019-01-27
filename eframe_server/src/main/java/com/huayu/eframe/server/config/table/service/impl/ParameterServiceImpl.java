package com.huayu.eframe.server.config.table.service.impl;

import com.huayu.eframe.server.config.table.atom.ParameterAtom;
import com.huayu.eframe.server.config.table.bo.Parameter;
import com.huayu.eframe.server.config.table.repository.ParameterRepository;
import com.huayu.eframe.server.config.table.service.ParameterService;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class ParameterServiceImpl implements ParameterService
{
    @Autowired
    private ParameterAtom parameterAtom;

    @Override
    public Parameter getParameterByCode(String code)
    {
        return parameterAtom.getByCode(code);
    }

    @Override
    public List<Parameter> getAllParameter()
    {
        return parameterAtom.getAll();
    }

    @Override
    public Parameter updateParameter(Parameter parameter)
    {
        return parameterAtom.update(parameter);
    }

    @Override
    public Parameter save(Parameter parameter)
    {
        parameter.setLastUpdateTime(DateUtils.getCurrentDate());
        return parameterAtom.insert(parameter);
    }
}
