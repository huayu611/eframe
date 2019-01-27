package com.huayu.eframe.server.flow.valid.impl;

import com.huayu.eframe.server.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.flow.valid.AbstractExecuteEFrameRequest;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.NumberUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Created by Leo on 2018/9/8.
 */
@Service
public class EframeDefaultInteger extends AbstractExecuteEFrameRequest
{
    private static final LogDebug debug = new LogDebug(EframeDefaultInteger.class);

    @Override
    public void doExtend(EFrameRequest ef, Field field, Object request) throws Exception
    {
        Object value = field.get(request);
        Integer defaultIntegerValue = ef.defaultIntegerValue();

        debug.log(value);
        if (null == value || "".equals(value.toString()))
        {
            field.set(request,defaultIntegerValue);
            debug.log(field.get(request));
        }
    }


    public  boolean check(EFrameRequest efremeRequest,Field field,Object request)
    {
        boolean minInteger = NumberUtils.isMinInteger(efremeRequest.defaultIntegerValue());
        if(field.getType().isAssignableFrom(Integer.class) && !minInteger)
        {
            Object value = getValueInField(field,request);
            return null == value;
        }
        return false;
    }

    public int order()
    {
        return 100;
    }
}
