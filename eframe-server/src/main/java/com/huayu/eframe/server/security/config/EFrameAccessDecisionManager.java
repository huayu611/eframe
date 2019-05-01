package com.huayu.eframe.server.security.config;

import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.restful.NoRightAuthenticationException;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by Administrator on 2018/7/28.
 */
@Component("EFrameAccessDecisionManager")
public class EFrameAccessDecisionManager implements AccessDecisionManager
{
    private static final LogDebug debug = new LogDebug(EFrameAccessDecisionManager.class);

    @Autowired
    private WhiteRequestCheck whiteRequestCheck;

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException
    {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String url = ((FilterInvocation) object).getRequestUrl();
        debug.log(url);
        if (checkIsStatic(filterInvocation.getRequest()))
        {
            debug.log("It's static resource");
            return;
        }
        if (CollectionUtils.isEmpty(collection))
        {
            throw new NoRightAuthenticationException("no right");
        }

        AntPathRequestMatcher matcher = null;
        for (ConfigAttribute attr : collection)
        {
            if (attr instanceof EFrameConfigAttribute)
            {
                EFrameConfigAttribute eframeAttr = (EFrameConfigAttribute) attr;
                if (eframeAttr.isWhite())
                {
                    return;
                }
                String authView = eframeAttr.getAttribute();
                debug.log(authView);
                if (StringUtils.isNotNullAndEmpty(authView))
                {
                    debug.log(authView);
                    String[] urlMethod = authView.split("\\;");

                    matcher = "ALL".equalsIgnoreCase(urlMethod[1]) ? new AntPathRequestMatcher(urlMethod[0]) : new AntPathRequestMatcher(urlMethod[0], urlMethod[1]);
                    if (matcher.matches(filterInvocation.getRequest()))
                    {
                        return;
                    }
                }
            }
        }
        throw new NoRightAuthenticationException("no right");
    }


    private boolean checkIsStatic(HttpServletRequest url)
    {

        AntPathRequestMatcher matcher = new AntPathRequestMatcher("/main/**");
        return matcher.matches(url);
    }


    @Override
    public boolean supports(ConfigAttribute attribute)
    {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz)
    {
        return true;
    }
}
