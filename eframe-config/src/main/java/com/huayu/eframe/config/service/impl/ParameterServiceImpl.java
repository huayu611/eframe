package com.huayu.eframe.config.service.impl;

import com.huayu.eframe.config.atom.ParameterAtom;
import com.huayu.eframe.config.bo.Parameter;
import com.huayu.eframe.config.cache.ParameterCache;
import com.huayu.eframe.config.service.ParameterDetail;
import com.huayu.eframe.config.service.ParameterService;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        LocalAttribute.addNeedRefreshCache(ParameterCache.CACHE);
        return buildParameterDetail(newParameter);
    }

    @Override
    public ParameterDetail addParameter(ParameterDetail parameterDetail)
    {
        Parameter parameter = buildParameter(parameterDetail);
        if(null != parameter)
        {
            Parameter newParameter = parameterAtom.insert(parameter);
            LocalAttribute.addNeedRefreshCache(ParameterCache.CACHE);
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
            LocalAttribute.addNeedRefreshCache(ParameterCache.CACHE);
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
