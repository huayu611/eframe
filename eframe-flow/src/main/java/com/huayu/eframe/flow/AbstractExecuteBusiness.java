package com.huayu.eframe.flow;

import com.huayu.eframe.flow.presist.constant.LogConstants;
import com.huayu.eframe.flow.presist.logic.LogRecordLogic;
import com.huayu.eframe.flow.valid.EffExpValid;
import com.huayu.eframe.flow.valid.ValidAnnotation;
import com.huayu.eframe.server.cache.CacheObserver;
import com.huayu.eframe.server.common.ConfigurationUtils;
import com.huayu.eframe.flow.common.FlowConstant;
import com.huayu.eframe.server.common.restful.EffectiveExpireDateTime;
import com.huayu.eframe.server.common.restful.RestfulResponse;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.ExceptionCacheService;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.NumberUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * 骨架类，流程控制器
 * Created by Administrator on 2018/7/17.
 */
public abstract class AbstractExecuteBusiness extends FrameCommonAPI implements ExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(AbstractExecuteBusiness.class);


    @Autowired
    private LogRecordLogic logRecordLogic;

    @Autowired
    private ValidAnnotation validAnnotation;

    @Autowired
    private TransactionFlow transactionFlow;

    @Override
    public void process(BusinessParameter param)
    {

        recordLog(LogConstants.LOG_INIT_STATUS,param.getRequest());
        this.init(param);

        recordLog(LogConstants.LOG_RUNNING_STATUS);
        checkRequestField(param.getRequest());
        before(param);

        doTrans(param);


    }

    private void doTrans(BusinessParameter param)
    {
        if(isNeedTransaction())
        {
            transactionFlow.executeTransaction(this, param);
        }
        else
        {
            this.execute(param);
        }
    }

    @Override
    public void init(BusinessParameter param)
    {

    }

    @Override
    public void before(BusinessParameter param)
    {

    }

    @Override
    public void after(BusinessParameter param)
    {
        refreshCache();
        beforeAfter(param);
        afterAfter(param);
    }


    protected void beforeAfter(BusinessParameter param)
    {

    }


    protected void afterAfter(BusinessParameter param)
    {

    }

    @Deprecated
    protected  RestfulResponse buildSuccessRestfulResponse(Object result,List list)
    {
        RestfulResponse response = new RestfulResponse();
        response.setCode("0");
        response.setMsg(getSuccessResultDescrption());
        response.setData(result);
        if(CollectionUtils.isNotEmpty(list))
        {
            response.setTotal(NumberUtils.getLongFromObject(list.size()));
        }

        debug.log(response);
        return response;
    }

    @Deprecated
    protected  RestfulResponse buildSuccessRestfulResponse(Object result)
    {
        return buildSuccessRestfulResponse(result,null);
    }


    @Override
    public Object getResult(BusinessParameter param)
    {
        RestfulResponse response = new RestfulResponse();
        response.setCode("0");
        response.setMsg(getSuccessResultDescrption());
        response.setData(tidyData(param));
        tidyResponse(response,param);
        recordLog(LogConstants.LOG_FINISH_STATUS,response);
        return response;
    }

    @Override
    public void exception(BusinessParameter param, Exception e)
    {
        doException(param,e);
        recordLog(LogConstants.LOG_ERROR_STATUS,e);
    }

    protected void doException(BusinessParameter param, Exception e)
    {

    }

    protected Object tidyData(BusinessParameter param)
    {
        return null;
    }

    protected void tidyResponse(RestfulResponse response,BusinessParameter param)
    {
        return ;
    }

    protected String getSuccessResultDescrption()
    {
        String resultCode = "0";
        String result = "Success!";
        Locale locale = null == LocalAttribute.getToken() ? null : LocalAttribute.getToken().getLocale();
        debug.log(locale);
        try {
            resultCode = resultCode + ".ERRORDESC";
            result = ExceptionCacheService.getErrorInfo(resultCode, null, locale);
        } catch (Exception e) {
            result = "Success!";
        }
        return result;
    }


    protected void coverSuccessInResponse(RestfulResponse response)
    {
        response.setCode("0");
        response.setMsg(getSuccessResultDescrption());
    }


    private void checkRequestField(Object request)
    {
        if(null == request)
        {
            return;
        }
        //验证时间
        checkExpEffDate(request);
        //验证字段
        validAnnotation.checkEFrameRequest(request);


    }

    private void checkExpEffDate(Object request)
    {
        if (request instanceof EffectiveExpireDateTime) {
            new EffExpValid(((EffectiveExpireDateTime) request).getEff(), ((EffectiveExpireDateTime) request).getExp()).validExpireDate().validExpireAndEffective();
        }
    }

    private void refreshCache()
    {
        List<String> cacheList = LocalAttribute.getNeedRefreshCache();
        if (CollectionUtils.isEmpty(cacheList)) {
            return;
        }
        CacheObserver cacheObserevr = BeanPool.getServiceByClass(CacheObserver.class);
        Iterator<String> iteratorCache = cacheList.iterator();
        while (iteratorCache.hasNext()) {
            String cacheName = iteratorCache.next();
            cacheObserevr.refreshCache(cacheName);
        }
    }

    private void recordLog(String flow)
    {
        recordLog(flow,null);
    }
    private void recordLog(String flow,Object obj)
    {
        if(!getLogFlag())
        {
            return ;
        }
        if(StringUtils.equalStringNoCareUpperAndLower(LogConstants.LOG_INIT_STATUS,flow))
        {
            logRecordLogic.initLog(obj);
        }
        else if(StringUtils.equalStringNoCareUpperAndLower(LogConstants.LOG_RUNNING_STATUS,flow))
        {
            logRecordLogic.startLog();
        }
        else  if(StringUtils.equalStringNoCareUpperAndLower(LogConstants.LOG_FINISH_STATUS,flow))
        {
            logRecordLogic.finishLog(obj);
        }
        else  if(StringUtils.equalStringNoCareUpperAndLower(LogConstants.LOG_ERROR_STATUS,flow))
        {
            Throwable t = (Throwable)obj;
            logRecordLogic.errorLog(t);
        }

    }

    protected boolean getLogFlag()
    {
        HttpServletRequest request = LocalAttribute.getValue(FlowConstant.HTTP_REQUEST);
        String method = request.getMethod();

        List<String> methods = ConfigurationUtils.getRecordLogMethod();

        return methods.contains(method);
    }

    //v1.0.1添加，为了一些不需要事务的操作。以免系统额外开支
    protected boolean isNeedTransaction()
    {
        return true;
    }
}
