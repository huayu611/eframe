package com.huayu.eframe.server.security.service.repository;

import com.huayu.eframe.server.security.service.bo.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/7/14.
 */
public interface RolePermissionRepository extends JpaRepository<RolePermission,Long>
{
}
