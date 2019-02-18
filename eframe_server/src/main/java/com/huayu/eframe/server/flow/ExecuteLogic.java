package com.huayu.eframe.server.flow;

import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.service.spring.BeanPool;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/7/7.
 */
@Service("ExecuteLogic")
public class ExecuteLogic
{

    private static final LogDebug debug = new LogDebug(ExecuteLogic.class);

    public Object execute(ExecuteBusiness busniessInstance,Object request)
    {
        debug.log("start ExecuteLogic");
        debug.log("business name is " + busniessInstance);

        BusinessParameter param = new BusinessParameter();
        param.setRequest(request);
        if(null == busniessInstance)
        {
            debug.errorLog("without service");
            throw new IFPException(ErrorCode.SERVICE_NOT_EXIST,"ServiceBeanNotExist");
        }
        try
        {
            busniessInstance.process(param);
        }
        catch(IFPException e)
        {
            debug.printErr(e);
            busniessInstance.exception(param,e);
            throw e ;
        }
        catch(Exception e)
        {
            debug.printErr(e);
            busniessInstance.exception(param,e);
            throw new IFPException(ErrorCode.UNKNOW_ERROR,"Unknow Error!");
        }
        try
        {
            busniessInstance.after(param);
        }
        catch(IFPException e)
        {
            StringBuffer errorDebug = new StringBuffer("Successful operator! but post business failed;\n");
            errorDebug.append("ErrorCode is: ").append(e.getErrorCode()).append(".").append("\n");
            errorDebug.append("ErrorDesc is: ").append(e.getErrorMsg()).append(".").append("\n");
            errorDebug.append("Stack is: ").append(e.getStackTrace()).append(".").append("\n");
            debug.errorLog(errorDebug.toString());
            busniessInstance.exception(param,e);
        }
        catch(Exception e)
        {
            StringBuffer errorDebug = new StringBuffer("Successful operator! but post business failed;\n");
            errorDebug.append("ErrorCode is: ").append(e.getCause()).append(".").append("\n");
            errorDebug.append("ErrorDesc is: ").append(e.getMessage()).append(".").append("\n");
            errorDebug.append("Stack is: ").append(e.getStackTrace()).append(".").append("\n");
            debug.errorLog();
            busniessInstance.exception(param,e);
        }
        Object result = busniessInstance.getResult(param);
        debug.endLog();
        return result;
    }
}
