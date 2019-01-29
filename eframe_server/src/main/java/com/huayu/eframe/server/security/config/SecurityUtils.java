package com.huayu.eframe.server.security.config;

import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.security.service.bo.Permission;
import com.huayu.eframe.server.security.service.cache.PermissionCache;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Leo on 2018/9/28.
 */
public class SecurityUtils
{
    private static final LogDebug debug = new LogDebug(SecurityUtils.class);

    public static boolean checkWhitePermission(PermissionCache permissionCache, HttpServletRequest request)
    {


        List<Permission> permissions = permissionCache.getWhitePermission(request.getMethod());
        boolean flag = checkCurrentMethodPermission(request, permissions);
        if(!flag)
        {
            List<Permission> allPermissions = permissionCache.getWhitePermission("ALL");
            flag = checkCurrentMethodPermission(request, allPermissions);
        }
       return flag;
    }

    private static boolean checkCurrentMethodPermission(HttpServletRequest request,  List<Permission> permissions)
    {
        boolean result = false;
        debug.log(request.getMethod());
        debug.log(permissions);
        if (CollectionUtils.isEmpty(permissions))
        {
            debug.log("without white list");
            return false;
        }
        for (Permission permission : permissions)
        {
            if (null != permission)
            {
                debug.log(permission.getMethod());
                debug.log(permission.getUrl());
                AntPathRequestMatcher ant = "ALL".equalsIgnoreCase(permission.getMethod()) ? new AntPathRequestMatcher(permission.getUrl()) : new AntPathRequestMatcher(permission.getUrl(), permission.getMethod());
                result = ant.matches(request);
                if (result)
                {
                    return result;
                }
            }
        }
        StringBuilder urlPermissionLog = new StringBuilder();
        urlPermissionLog.append("URL : ").append(request.getRequestURL()).append(" check white url result is ").append(result);
        debug.log(urlPermissionLog.toString());
        return result;
    }

}
