package com.huayu.eframe.flow.valid;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Leo on 2018/9/8.
 */
@Service
public class ValidProcess
{
    private static final LogDebug debug = new LogDebug(ValidProcess.class);

    private List<AbstractExecuteEFrameRequest> requestValidList;

    public ValidProcess(List<AbstractExecuteEFrameRequest> requestValid)
    {
        if (CollectionUtils.isNotEmpty(requestValid))
        {
            requestValidList = requestValid;
            requestValid.sort(Comparator.comparingInt(AbstractExecuteEFrameRequest::order));
        }
        else
        {
            requestValidList = new ArrayList<>();
        }
    }

    public void process(EFrameRequest ef, Field field, Object request)
    {
        if (CollectionUtils.isNotEmpty(requestValidList))
        {
            Iterator<AbstractExecuteEFrameRequest> validIterator = requestValidList.iterator();
            while (validIterator.hasNext())
            {

                AbstractExecuteEFrameRequest valid = validIterator.next();
                if (valid.check(ef, field, request))
                {
                    debug.log(valid.getClass(), field.getName());
                    valid.execute(ef, request, field);
                }
            }
        }
    }
}
