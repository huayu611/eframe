package com.huayu.eframe.server.config.table.service.impl;

import com.huayu.eframe.server.config.table.atom.ParameterAtom;
import com.huayu.eframe.server.config.table.bo.Parameter;
import com.huayu.eframe.server.config.table.repository.ParameterRepository;
import com.huayu.eframe.server.config.table.service.ParameterDetail;
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

import java.util.ArrayList;
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
    public List<ParameterDetail> getAllParameter()
    {
        List<Parameter> parameters = parameterAtom.getAll();
        return buildParameterDetails(parameters);
    }

    @Override
    public ParameterDetail updateParameter(ParameterDetail parameterDetail)
    {
       Parameter parameter = parameterAtom.getByCode(parameterDetail.getParameterCode());
       if(null != parameterDetail.getParameterValue())
       {
           parameter.setValue(parameterDetail.getParameterValue());
       }
        if(null != parameterDetail.getParameterName())
        {
            parameter.setParameterName(parameterDetail.getParameterName());
        }
        Parameter newParameter = parameterAtom.update(parameter);
        return buildParameterDetail(newParameter);
    }

    @Override
    public ParameterDetail addParameter(ParameterDetail parameterDetail)
    {
        Parameter parameter = buildParameter(parameterDetail);
        if(null != parameter)
        {
            Parameter newParameter = parameterAtom.insert(parameter);
            return buildParameterDetail(newParameter);
        }
        return null;
    }

    @Override
    public String deleteParameter(String parameterCode)
    {
        Parameter parameter = parameterAtom.getByCode(parameterCode);
        if(null != parameter)
        {
            parameterAtom.delete(parameter);
            return parameterCode;
        }
        return "";
    }

    private List<ParameterDetail> buildParameterDetails(List<Parameter> parameters)
    {
        List<ParameterDetail> resultParameterDetailList = new ArrayList<>();
        if(CollectionUtils.isEmpty(parameters))
        {
            return resultParameterDetailList;
        }
        for(Parameter parameter : parameters)
        {
            ParameterDetail parameterDetail  = buildParameterDetail(parameter);
            resultParameterDetailList.add(parameterDetail);
        }
        return resultParameterDetailList;
    }

    private Parameter buildParameter(ParameterDetail parameterDetail)
    {
        Parameter parameter = new Parameter();
        parameter.setParameterCode(parameterDetail.getParameterCode());
        parameter.setParameterName(parameterDetail.getParameterName());
        parameter.setValue(parameterDetail.getParameterValue());

        return parameter;
    }

    private ParameterDetail buildParameterDetail(Parameter parameter)
    {
        ParameterDetail parameterDetail = new ParameterDetail();
        parameterDetail.setParameterCode(parameter.getParameterCode());
        parameterDetail.setParameterName(parameter.getParameterName());
        parameterDetail.setParameterValue(parameter.getValue());
        return parameterDetail;
    }
}
