package com.huayu.eframe.server.security.service.cache;

import com.huayu.eframe.server.security.service.bo.Role;

import java.util.List;

/**
 * Created by Leo on 2018/9/15.
 */
public interface RoleCache
{

    String CACHE_NAME = "SYS_ROLE";

    Role getRoleByRoleCode(String roleCode);

    Role getRoleById(Long roleID);

    List<Role> getAllRole();
}
