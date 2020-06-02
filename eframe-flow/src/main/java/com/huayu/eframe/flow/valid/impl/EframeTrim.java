package com.huayu.eframe.flow.valid.impl;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.flow.valid.AbstractExecuteEFrameRequest;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Created by Leo on 2019/9/24.
 */
@Service
public class EframeTrim extends AbstractExecuteEFrameRequest
{
    @Override
    public void doExtend(EFrameRequest ef, Field field, Object request) throws Exception
    {
        Object value = field.get(request);
        if(value instanceof String)
        {
            String valueString = StringUtils.getString(value);
            if(StringUtils.isNotNullAndEmpty(valueString))
            {

                //重大BUG
//                valueString = valueString.replaceAll("\\s*", "");
                String valueTrim = StringUtils.getTrimString(valueString);
                field.set(request, valueTrim);
            }

        }
    }

    public boolean check(EFrameRequest efremeRequest, Field field, Object request)
    {
        boolean trimFlag = efremeRequest.trim();
        if(trimFlag)
        {
            if (field.getType().isAssignableFrom(String.class))
            {
                return true;
            }
        }
        return false;
    }
}
