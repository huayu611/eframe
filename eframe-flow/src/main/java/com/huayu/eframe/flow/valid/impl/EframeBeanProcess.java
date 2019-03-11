package com.huayu.eframe.flow.valid.impl;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.flow.constant.FlowErrorCode;
import com.huayu.eframe.flow.valid.AbstractExecuteEFrameRequest;
import com.huayu.eframe.flow.valid.ValidBeanDefined;
import com.huayu.eframe.flow.valid.ValidBeanParamDefined;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Leo on 2018/9/26.
 */
@Service
public class EframeBeanProcess extends AbstractExecuteEFrameRequest
{
    private static final LogDebug debug = new LogDebug(EframeBeanProcess.class);
    @Override
    public void doExtend(EFrameRequest efremeRequest, Field field, Object request) throws Exception
    {
        String beanName = efremeRequest.bean();
        String[] beanNameWithParameter = parseBeanWith(beanName);
        if(StringUtils.isNullOrEmpty(beanNameWithParameter[1]))
        {
            processNoParameterBean(field, request, beanNameWithParameter);
        }
        else
        {
            processWithParameterBean(field, request, beanNameWithParameter);
        }
    }

    private void processNoParameterBean(Field field, Object request, String[] beanName) throws IllegalAccessException
    {
        ValidBeanDefined valid = BeanPool.getService(StringUtils.getTrimString(beanName[0]));

        if(null == valid)
        {
            throw new IFPException(FlowErrorCode.SERVICE_NOT_EXIST,"ServiceBeanNotExist");
        }
        valid.process(field,field.get(request),request);
    }

    private void processWithParameterBean(Field field, Object request, String[] beanName) throws IllegalAccessException
    {
        ValidBeanParamDefined valid = BeanPool.getService(StringUtils.getTrimString(beanName[0]));

        if(null == valid)
        {
            throw new IFPException(FlowErrorCode.SERVICE_NOT_EXIST,"ServiceBeanNotExist");
        }
        valid.process(field,field.get(request),request,beanName[1]);
    }

    public  boolean check(EFrameRequest efremeRequest,Field field,Object request)
    {
        String beanName = efremeRequest.bean();
        return !StringUtils.isNullOrEmpty(beanName);
    }

    private String[] parseBeanWith(String bean)
    {
        String[] beanParse = new String[2];
        if(bean.contains("(")&&bean.contains(")"))
        {
            Pattern t=Pattern.compile("(.*?)\\((.*?)\\)");
            Matcher m = t.matcher(bean);
            while (m.find())
            {
                String beanName = m.group(1);
                String paramName = m.group(2);
                beanParse[0] = beanName;
                beanParse[1] = paramName;
                break;
            }
            if(StringUtils.isNullOrEmpty(beanParse[0]))
            {
                beanParse[0] = bean;
                beanParse[1] = "";
            }
        }
        else
        {
            beanParse[0] = bean;
        }
        return beanParse;
    }


}
