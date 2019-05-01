package com.huayu.eframe.management.single.impl;

import com.huayu.eframe.management.bo.Permission;
import com.huayu.eframe.management.bo.Role;
import com.huayu.eframe.management.single.bo.PermissionDetail;
import com.huayu.eframe.management.single.bo.RoleDetail;
import com.huayu.eframe.server.log.LogDebug;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/1/17.
 */
@Service
public class SecurityServiceImplUtil
{

    private static final LogDebug debug = new LogDebug(SecurityServiceImplUtil.class);


    public PermissionDetail getPermissionDetail(Permission permission)
    {
        if (null == permission)
        {
            return null;
        }
        PermissionDetail permissionDetail = new PermissionDetail();
        permissionDetail.setUrl(permission.getUrl());
        permissionDetail.setEff(permission.getEffectiveTime());
        permissionDetail.setExp(permission.getEffectiveTime());
        permissionDetail.setCode(permission.getPermissionCode());
        permissionDetail.setWhite(permission.getWhite());
        permissionDetail.setMethod(permission.getMethod());
        permissionDetail.setType(permission.getPermissionType());
        permissionDetail.setName(permission.getPermissionName());
        permissionDetail.setStatus(permission.getStatus());
        return permissionDetail;
    }

    public RoleDetail getRoleDetail(Role role)
    {
        RoleDetail roleDetail = new RoleDetail();
        roleDetail.setName(role.getName());
        roleDetail.setCode(role.getRoleCode());
        roleDetail.setEff(role.getEffectiveTime());
        roleDetail.setExp(role.getExpireTime());
        roleDetail.setStatus(role.getStatus());
//        Role parentRole = getRleByRoleID(role.getParentRoleId());
//        Role topRole = getRleByRoleID(role.getTopRoleId());
//        roleDetail.setParent(parentRole.getRoleCode());
//        roleDetail.setTop(topRole.getRoleCode());
        roleDetail.setRemark(role.getRemark());
//        roleDetail.setPermissions(coverPermissionDetailList(role.getId()));
        return roleDetail;
    }
}
