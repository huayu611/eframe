package com.huayu.eframe.config.parameter.service.impl;

import com.huayu.eframe.config.parameter.bo.ParameterDescription;
import com.huayu.eframe.config.parameter.repository.ParameterDescriptionRepository;
import com.huayu.eframe.config.parameter.service.ParameterDescriptionService;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterDescriptionServiceImpl implements ParameterDescriptionService
{

    @Autowired
    private ParameterDescriptionRepository parameterDescriptionRepository;

    @Override
    public ParameterDescription getParameterDescription(String parameterCode)
    {
        ParameterDescription parameterDescription = new ParameterDescription();
        parameterDescription.setParameterCode(parameterCode);

        ExampleMatcher em = ExampleMatcher.matching();
        Example<ParameterDescription> example = Example.of(parameterDescription,em);
        List<ParameterDescription> parameterList = parameterDescriptionRepository.findAll(example);
        return CollectionUtils.getFirstElement(parameterList);
    }

    @Override
    public void deleteParameterDescription(String parameterCode)
    {
        ParameterDescription parameterDescription = new ParameterDescription();
        parameterDescription.setParameterCode(parameterCode);
        parameterDescriptionRepository.delete(parameterDescription);

    }

    @Override
    public ParameterDescription updateParameterDescription(ParameterDescription description)
    {
        return parameterDescriptionRepository.save(description);
    }

    @Override
    public ParameterDescription addParameterDescription(ParameterDescription description)
    {
        return parameterDescriptionRepository.save(description);
    }
}
