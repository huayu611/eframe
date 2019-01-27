package com.huayu.eframe.server.flow.valid.impl;

import com.huayu.eframe.server.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.flow.valid.AbstractExecuteEFrameRequest;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Created by Leo on 2018/9/8.
 */
@Service
public class EframeDefaultStringValue extends AbstractExecuteEFrameRequest
{

    private static final LogDebug debug = new LogDebug(EframeDefaultStringValue.class);

    @Override
    public void doExtend(EFrameRequest ef, Field field, Object request) throws Exception
    {
        Object value = field.get(request);
        debug.log(value);
        if (null == value || "".equals(value.toString()))
        {
            field.set(request, ef.defaultStrValue());
            debug.log(field.get(request));
        }
    }

    public boolean check(EFrameRequest efremeRequest, Field field, Object request)
    {
        if(field.getType().isAssignableFrom(String.class) && StringUtils.isNotNullAndEmpty(efremeRequest.defaultStrValue()))
        {
            Object value = getValueInField(field,request);
            String valueString = StringUtils.getString(value);
            return StringUtils.isNullOrEmpty(valueString);
        }
        return false;
    }

    public int order()
    {
        return 100;
    }
}
