package com.huayu.eframe.server.security.config;

import com.huayu.eframe.server.log.LogDebug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AfterInvocationManager;
import org.springframework.security.access.intercept.AfterInvocationProviderManager;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/7/28.
 */
@Service
public class EFrameFilterSecurityInterceptor extends FilterSecurityInterceptor
{
    private static final LogDebug debug = new LogDebug(EFrameFilterSecurityInterceptor.class);
    @Autowired
    private EFrameFilterInvocationSecurityMetadataSource eFrameFilterInvocationSecurityMetadataSource;

    @Autowired
    public void setMyAccessDecisionManager(EFrameAccessDecisionManager myAccessDecisionManager)
    {
        super.setAccessDecisionManager(myAccessDecisionManager);
    }

    public SecurityMetadataSource obtainSecurityMetadataSource()
    {
        debug.log(eFrameFilterInvocationSecurityMetadataSource.getClass().getName());
        return eFrameFilterInvocationSecurityMetadataSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        super.afterPropertiesSet();
        setRejectPublicInvocations(true);
    }

}
