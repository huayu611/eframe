package com.huayu.eframe.server.flow;

import com.huayu.eframe.server.cache.CacheObserver;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.flow.restful.EffectiveExpireDateTime;
import com.huayu.eframe.server.flow.restful.RestfulResponse;
import com.huayu.eframe.server.flow.valid.EffExpValid;
import com.huayu.eframe.server.flow.valid.ValidAnnotation;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.ExceptionCacheService;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.NumberUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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
    private ValidAnnotation validAnnotation;

    @Autowired
    private TransactionFlow transactionFlow;

    @Override
    public void process(BusinessParameter param)
    {

        init(param);
        checkRequestField(param.getRequest());
        before(param);

        doTrans(param);

    }

    private void doTrans(BusinessParameter param)
    {
        transactionFlow.executeTransaction(this,param);
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
        return response;
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
            resultCode = resultCode + "_ERRORDESC";
            result = ExceptionCacheService.getErrorInfo(resultCode, null, LocalAttribute.getToken().getLocale());
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

}
