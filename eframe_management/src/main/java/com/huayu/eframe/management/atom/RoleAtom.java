package com.huayu.eframe.management.atom;

import com.huayu.eframe.management.bo.Role;
import com.huayu.eframe.server.common.FramePaging;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by Leo on 2018/9/15.
 */
public interface RoleAtom
{
    List<Role> getAllRoles();

    Role insert(Role role);

    Role update(Role role);

    void delete(Role role);

    Role queryRoleByID(Long id);

    List<Role> queryRoles(Role role, Date now);

    List<Role> queryRolesByCode(String roleCode, Date now);

    Page<Role> queryValidRoleByPage(FramePaging fp, Date now, Role condition);
}
