package com.huayu.eframe.flow.valid;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.IFPException;

import java.lang.reflect.Field;

/**
 * Created by Leo on 2018/9/8.
 */
public abstract class AbstractExecuteEFrameRequest
{

    private static final LogDebug debug = new LogDebug(AbstractExecuteEFrameRequest.class);

    public AbstractExecuteEFrameRequest()
    {

    }


    public void execute(EFrameRequest ef, Object request, Field field)
    {


        boolean accessible = field.isAccessible();
        if (!accessible) {
            field.setAccessible(true);
        }
        try {
            doExtend(ef, field, request);
        } catch (Exception e) {
            debug.log(e);
            if (e instanceof IFPException) {
                throw (IFPException) e;
            }

        } finally {
            field.setAccessible(accessible);
        }

    }

    public abstract void doExtend(EFrameRequest ef, Field field, Object request) throws Exception;

    public boolean check(EFrameRequest ef, Field field, Object request)
    {
        return true;
    }

    public int order()
    {
        return Integer.MAX_VALUE;
    }

    protected Object getValueInField(Field field, Object request)
    {

        if (null != field && null != request) {
            boolean accessible = field.isAccessible();
            if (!accessible) {
                field.setAccessible(true);
            }
            Object value = null;
            try {
                value = field.get(request);
            } catch (Exception e) {
                debug.log("Get Value failed ,"+ field.getName());
            } finally {
                field.setAccessible(accessible);
            }
            return value;
        }

        return null;
    }

}
