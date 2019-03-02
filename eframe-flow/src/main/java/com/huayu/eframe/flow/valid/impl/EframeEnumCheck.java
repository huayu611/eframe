package com.huayu.eframe.flow.valid.impl;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.flow.valid.AbstractExecuteEFrameRequest;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Leo on 2018/9/8.
 */
@Service
public class EframeEnumCheck extends AbstractExecuteEFrameRequest
{
    private static final LogDebug debug = new LogDebug(EframeEnumCheck.class);
    @Override
    public void doExtend(EFrameRequest ef, Field field, Object request) throws Exception
    {
        String[] enumValue = ef.enumValue();
        List<String> enumList = Arrays.asList(enumValue);
        Object value = getValueInField(field,request);
        if(!enumList.contains(StringUtils.getString(value)))
        {
            throw new IFPException(ErrorCode.REQUEST_VALID_FIELD_VALUE_NOT_INCORRECT, "Field " + field.getName() + " value is illegal");
        }

    }

    public boolean check(EFrameRequest ef, Field field, Object request)
    {
        if(field.getType().isAssignableFrom(String.class) && ef.enumValue().length>0)
        {
            Object value = getValueInField(field,request);
            String valueString = StringUtils.getString(value);
            return StringUtils.isNotNullAndEmpty(valueString);
        }
        return false;
    }

}
