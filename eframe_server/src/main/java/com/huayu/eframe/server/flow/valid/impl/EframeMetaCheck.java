package com.huayu.eframe.server.flow.valid.impl;

import com.huayu.eframe.server.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.flow.valid.AbstractExecuteEFrameRequest;
import com.huayu.eframe.server.flow.valid.ValidAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Created by Leo on 2018/9/8.
 */
@Service
public class EframeMetaCheck extends AbstractExecuteEFrameRequest
{

    @Autowired
    private ValidAnnotation validAnnotation;

    private static Integer META_DATA_CHECK = Integer.valueOf("2");

    @Override
    public void doExtend(EFrameRequest ef, Field field, Object request) throws Exception
    {
        validAnnotation.checkEFrameRequest(request);
    }

    public boolean check(EFrameRequest ef, Field field, Object request)
    {
        boolean baseMeta = ef.baseMeta();
        Object value = getValueInField(field,request);
        if(null == value)
        {
            return false;
        }
        return baseMeta;
    }

    public int order()
    {
        return META_DATA_CHECK.intValue();
    }

}
