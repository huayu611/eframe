package com.huayu.eframe.config.atom.impl;

import com.huayu.eframe.config.atom.ParameterAtom;
import com.huayu.eframe.config.bo.Parameter;
import com.huayu.eframe.config.repository.ParameterRepository;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2018/9/9.
 */
@Service
public class ParameterAtomImpl implements ParameterAtom
{
    @Autowired
    private ParameterRepository parameterRepository;

    @Override
    public List<Parameter> getAll()
    {
        return parameterRepository.findAll();
    }

    @Override
    public Parameter getByCode(String parameterCode)
    {
        if(StringUtils.isNullOrEmpty(parameterCode))
        {
            return null;
        }
        Parameter parameter = new Parameter();
        parameter.setParameterCode(parameterCode);
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<Parameter> example = Example.of(parameter ,matcher);

        List<Parameter> parameters = parameterRepository.findAll(example);
        return CollectionUtils.getFirstElement(parameters);
    }

    @Override
    public void delete(Parameter parameter)
    {
        parameterRepository.delete(parameter);
    }

    @Override
    public Parameter update(Parameter parameter)
    {
        if(null == parameter )
        {
            return null;
        }
        if(null == parameter.getId())
        {
            return insert(parameter);
        }
        return parameterRepository.save(parameter);
    }

    @Override
    public Parameter insert(Parameter parameter)
    {
        if(null == parameter )
        {
            return null;
        }
        return  parameterRepository.save(parameter);
    }
}
