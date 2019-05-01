package com.huayu.eframe.flow.valid.impl;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.flow.valid.AbstractExecuteEFrameRequest;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.NumberUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Created by Leo on 2018/9/8.
 */
@Service
public class EframeDefaultLong extends AbstractExecuteEFrameRequest
{
    private static final LogDebug debug = new LogDebug(EframeDefaultLong.class);

    @Override
    public void doExtend(EFrameRequest ef, Field field, Object request) throws Exception
    {
        Object value = field.get(request);
        debug.log(value);
        if (null == value || "".equals(value.toString()))
        {
            field.set(request, ef.defaultLongValue());
            debug.log(field.get(request));
        }
    }

    public boolean check(EFrameRequest efremeRequest, Field field, Object request)
    {
        boolean minLong = NumberUtils.isMinLong(efremeRequest.defaultLongValue());
        if (field.getType().isAssignableFrom(Long.class) && !minLong)
        {
            Object value = getValueInField(field, request);
            return null == value;
        }
        return false;
    }

    public int order()
    {
        return 100;
    }
}
