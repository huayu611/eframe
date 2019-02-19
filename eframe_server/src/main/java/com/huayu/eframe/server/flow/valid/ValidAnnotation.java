package com.huayu.eframe.server.flow.valid;

import com.huayu.eframe.server.common.StaticCache;
import com.huayu.eframe.server.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.log.LogDebug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2018/8/12.
 */
@Service
public class ValidAnnotation
{
    private static final LogDebug debug = new LogDebug(ValidAnnotation.class);

    @Autowired
    private ValidProcess validProcess;

    public void checkEFrameRequest(Object request)
    {

        Map<String, EFrameRequest> annoRequest = getStringEFrameRequestMap(request);

        if (null != annoRequest && !annoRequest.isEmpty())
        {
            Set<Map.Entry<String, EFrameRequest>> annoRequestKeySet = annoRequest.entrySet();
            Iterator<Map.Entry<String, EFrameRequest>> keySetIterator = annoRequestKeySet.iterator();

            while (keySetIterator.hasNext())
            {
                Field field = null;
                Map.Entry<String, EFrameRequest> single = keySetIterator.next();
                String key = single.getKey();
                EFrameRequest annoEframe = single.getValue();

                if (null == key || "".equals(key) || null == annoEframe)
                {
                    continue;
                }
                try
                {
                    field = request.getClass().getDeclaredField(key);
                }
                catch (Exception e)
                {
                    debug.log(e);
                }

                checkAnnoRequest(request, annoEframe, field);
            }
        }
    }

    private Map<String, EFrameRequest> getStringEFrameRequestMap(Object request)
    {
        Map<String, EFrameRequest> annoRequest = StaticCache.getAnnoRequest(request.getClass());
        if (null == annoRequest)
        {
            StaticCache.putAnnoRequest(request.getClass(),null,null);
            Field[] fields = request.getClass().getDeclaredFields();
            debug.log(fields);
            for (Field field : fields)
            {
                debug.log(field);
                if (null == field)
                {
                    continue;
                }
                EFrameRequest[] eframeRequest = field.getAnnotationsByType(EFrameRequest.class);
                if (eframeRequest.length == 0)
                {
                    continue;
                }
                StaticCache.putAnnoRequest(request.getClass(), field.getName(), eframeRequest[0]);
            }
            annoRequest = StaticCache.getAnnoRequest(request.getClass());

        }
        return annoRequest;
    }


    private void checkAnnoRequest(Object request, EFrameRequest efremeRequest, Field field)
    {
        validProcess.process(efremeRequest,field,request);
    }


}
