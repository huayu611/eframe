package com.huayu.eframe.flow.valid.impl;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.flow.constant.FlowErrorCode;
import com.huayu.eframe.flow.valid.AbstractExecuteEFrameRequest;
import com.huayu.eframe.flow.valid.ValidBeanDefined;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

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
        ValidBeanDefined valid = BeanPool.getService(StringUtils.getTrimString(beanName));

        if(null == valid)
        {
            throw new IFPException(FlowErrorCode.SERVICE_NOT_EXIST,"ServiceBeanNotExist");
        }
        valid.process(field,field.get(request),request);
    }

    public  boolean check(EFrameRequest efremeRequest,Field field,Object request)
    {
        String beanName = efremeRequest.bean();
        return !StringUtils.isNullOrEmpty(beanName);
    }
}
