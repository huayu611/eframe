package com.huayu.eframe.config.parameter.service.impl;

import com.huayu.eframe.config.parameter.bo.ParameterCategory;
import com.huayu.eframe.config.parameter.repository.ParameterCategoryRepository;
import com.huayu.eframe.config.parameter.service.ParameterCategoryService;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterCategoryServiceImpl implements ParameterCategoryService
{

    @Autowired
    private ParameterCategoryRepository parameterCategoryRepository;

    public ParameterCategory getParameterCategory(String categoryCode)
    {
        ParameterCategory parameterCategory = new ParameterCategory();
        parameterCategory.setCategoryCode(categoryCode);

        ExampleMatcher em = ExampleMatcher.matching();
        Example<ParameterCategory> example = Example.of(parameterCategory, em);
        List<ParameterCategory> categoryList = parameterCategoryRepository.findAll(example);
        return CollectionUtils.getFirstElement(categoryList);
    }

    public void deleteParameterCategory(String categoryCode)
    {
        ParameterCategory parameterCategory = new ParameterCategory();
        parameterCategory.setCategoryCode(categoryCode);
        parameterCategoryRepository.delete(parameterCategory);
    }

    @Override
    public ParameterCategory updateParameterCategory(ParameterCategory description)
    {
        return parameterCategoryRepository.save(description);
    }

    @Override
    public ParameterCategory addParameterCategory(ParameterCategory description)
    {
        return parameterCategoryRepository.save(description);
    }
}
