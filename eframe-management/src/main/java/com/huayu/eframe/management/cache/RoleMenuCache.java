package com.huayu.eframe.management.cache;

import com.huayu.eframe.management.bo.Role;
import com.huayu.eframe.management.bo.RoleMenu;

import java.util.List;

/**
 * Created by Leo on 2019/8/5.
 */
public interface RoleMenuCache
{
    String CACHE_NAME = "SYS_ROLE_MENU";

    List<RoleMenu> queryRoleMenuByRole(Role role);

    List<RoleMenu> queryAllRoleMenu();
}
