package com.huayu.eframe.flow.valid.impl;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.flow.valid.AbstractExecuteEFrameRequest;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Created by Leo on 2018/9/28.
 */
@Service
public class EframeCheckLength extends AbstractExecuteEFrameRequest
{
    private static final LogDebug debug = new LogDebug(EframeCheckLength.class);
    @Override
    public void doExtend(EFrameRequest ef, Field field, Object request) throws Exception
    {
        String value = StringUtils.getString(field.get(request));
        int fieldLength = StringUtils.getLength(value);
        debug.log(value,ef.length());
        if( ef.length() < fieldLength)
        {
            String[] exceptionParam = new String[]{field.getName(),value,StringUtils.getString(fieldLength),StringUtils.getString(ef.length())};
            throw new IFPException(ErrorCode.REQUEST_FIELD_LENGTH_REACH_MAX_LENGTH, "The field reach max length",exceptionParam);
        }
    }

    public  boolean check(EFrameRequest eFrameRequest,Field field,Object request)
    {
      return !(eFrameRequest.length() == -1);

    }
}
