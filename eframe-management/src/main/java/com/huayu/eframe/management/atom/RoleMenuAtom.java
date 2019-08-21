package com.huayu.eframe.management.atom;

import com.huayu.eframe.management.bo.Role;
import com.huayu.eframe.management.bo.RoleMenu;

import java.util.List;

/**
 * Created by Leo on 2019/8/5.
 */
public interface RoleMenuAtom
{
    RoleMenu addRoleMenu(RoleMenu roleMenu);

    List<RoleMenu> batchAddRoleMenu(List<RoleMenu>  roleMenu);

    void batchRemoveRoleMenu(List<RoleMenu>  roleMenu);

    RoleMenu removeRoleMenu(RoleMenu roleMenu);

    List<RoleMenu> queryRoleMenuByRole(Role role);

    List<RoleMenu> queryAllRoleMenu();

    List<RoleMenu> queryAllRoleMenuInRoles(List<Role> role);
}
