package com.huayu.eframe.server.security.config;

import com.huayu.eframe.server.config.properties.PropertiesLoadService;
import com.huayu.eframe.server.config.properties.SystemConfig;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.mvc.token.AbstractTokenMirror;
import com.huayu.eframe.server.mvc.token.AuthView;
import com.huayu.eframe.server.security.service.bo.*;
import com.huayu.eframe.server.security.service.cache.*;
import com.huayu.eframe.server.tool.basic.NumberUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2018/9/15.
 */
@Service
public class ManagerToken extends AbstractTokenMirror
{
    private static final LogDebug debug = new LogDebug(ManagerToken.class);

    private static final String MANAGER_SESSION_TIMEOUT = "sys_manager_session_timeout";

    private static final String MANAGER_SESSION_TIMEOUT_DEFAULT_VALUE = "3600";

    @Autowired
    private PropertiesLoadService propertiesLoadService;

    @Autowired
    private StaffCache staffCache;

    @Autowired
    private RoleStaffCache roleStaffCache;

    @Autowired
    private RoleCache roleCache;

    @Autowired
    private RolePermissionCache rolePermissionCache;

    @Autowired
    private PermissionCache permissionCache;




    @Override
    public List<AuthView> loadAuthView(String loginName)
    {
        List<AuthView> authViewList = new ArrayList<>();

        Staff staff = staffCache.getStaffByLoginName(loginName);

        debug.log(staff);

        List<RoleStaff> roleStaffList = roleStaffCache.getRoleStaffByStaffId(staff.getId());

        if (CollectionUtils.isNotEmpty(roleStaffList)) {
            for (RoleStaff roleStaff : roleStaffList) {
                Role role = roleCache.getRoleById(roleStaff.getRoleId());


                List<RolePermission> rolePermission = rolePermissionCache.getRolePermissionByRoleId(role.getId());
                if (CollectionUtils.isNotEmpty(rolePermission)) {
                    for (RolePermission rolePermissionTemp : rolePermission) {
                        Permission permission = permissionCache.getPermissionById(rolePermissionTemp.getPermissionId());
                        if(null != permission) {
                            AuthView authView = new AuthView();
                            authView.setMethod(permission.getMethod());
                            authView.setPermissionID(permission.getId());
                            authView.setUrl(permission.getUrl());
                            authViewList.add(authView);
                        }
                    }
                }

            }
        }
        return authViewList;

    }

    protected boolean needStartTimeTaskCleanSession()
    {
        return true;
    }

    @Override
    public int cycle()
    {
        String timeoutStr = SystemConfig.getValueByService(MANAGER_SESSION_TIMEOUT, MANAGER_SESSION_TIMEOUT_DEFAULT_VALUE,propertiesLoadService);
        int timeout = NumberUtils.getIntegerFromObject(timeoutStr);
        return timeout;
    }
}
