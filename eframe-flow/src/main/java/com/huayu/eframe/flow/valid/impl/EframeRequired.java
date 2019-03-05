package com.huayu.eframe.flow.valid.impl;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.flow.constant.FlowErrorCode;
import com.huayu.eframe.flow.valid.AbstractExecuteEFrameRequest;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Created by Leo on 2018/9/8.
 */
@Service
public class EframeRequired  extends AbstractExecuteEFrameRequest

{

    private static final LogDebug debug = new LogDebug(EframeRequired.class);

    @Override
    public void doExtend(EFrameRequest ef, Field field, Object request) throws Exception
    {
        Object value = field.get(request);
        debug.log(value);
        if (null == value || "".equals(value.toString()))
        {
            String[] param = {field.getName()};
            throw new IFPException(FlowErrorCode.REQUEST_VALID_FIELD_REQUIRED_FIELD_WITHOUT_VALUE, "Field " + field.getName() + " can not be empty",param);
        }
    }


    public boolean check(EFrameRequest efremeRequest, Field field, Object request)
    {
       return efremeRequest.required();
    }

    public int order()
    {
        return 1;
    }
}
