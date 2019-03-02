package com.huayu.eframe.management.atom;

import com.huayu.eframe.management.bo.Permission;
import com.huayu.eframe.server.common.FramePaging;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by Leo on 2018/9/9.
 */
public interface PermissionAtom
{
    Permission insert(Permission permission);

    List<Permission> getAll();

    Permission update(Permission permission);

    void delete(Permission permission);

    Permission getPermissionByID(Long id);

    List<Permission> getPermissionByCode(String code, Date now);

    List<Permission> queryPermission(Permission permission, Date now);

    Page<Permission> queryValidPermissionByPage(FramePaging fp, Date now, Permission condition);
}
