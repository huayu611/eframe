package com.huayu.eframe.flow.valid.impl;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.flow.constant.FlowErrorCode;
import com.huayu.eframe.flow.valid.AbstractExecuteEFrameRequest;
import com.huayu.eframe.flow.valid.ValidBeanDefined;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.service.spring.BeanPool;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Created by Leo on 2018/10/4.
 */
@Service
public class EframeBeansProcess extends AbstractExecuteEFrameRequest
{
    @Override
    public void doExtend(EFrameRequest efremeRequest, Field field, Object request) throws Exception
    {
        String[] beanNames = efremeRequest.beans();
        for (String bean : beanNames)
        {
            ValidBeanDefined valid = BeanPool.getService(bean);
            if (null == valid)
            {
                throw new IFPException(FlowErrorCode.SERVICE_NOT_EXIST, "ServiceBeanNotExist");
            }
            valid.process(field, field.get(request), request);
        }
    }

    public boolean check(EFrameRequest efremeRequest, Field field, Object request)
    {
        String[] beanName = efremeRequest.beans();
        return beanName.length > 0;
    }
}
