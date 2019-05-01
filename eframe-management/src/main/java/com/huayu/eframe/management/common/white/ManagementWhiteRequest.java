package com.huayu.eframe.management.common.white;

import com.huayu.eframe.management.bo.Permission;
import com.huayu.eframe.management.cache.PermissionCache;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.security.config.WhiteRequest;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Leo on 2019/3/1.
 */
@Service
public class ManagementWhiteRequest implements WhiteRequest
{
    private static final LogDebug debug = new LogDebug(ManagementWhiteRequest.class);

    @Autowired
    private PermissionCache permissionCache;

    @Override
    public boolean checkWhite(HttpServletRequest httpServletRequest)
    {
        List<Permission> permissions = permissionCache.getWhitePermission(httpServletRequest.getMethod());
        boolean flag = checkCurrentMethodPermission(httpServletRequest, permissions);
        if (!flag)
        {
            List<Permission> allPermissions = permissionCache.getWhitePermission("ALL");
            flag = checkCurrentMethodPermission(httpServletRequest, allPermissions);
        }
        return flag;
    }

    private static boolean checkCurrentMethodPermission(HttpServletRequest request, List<Permission> permissions)
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
