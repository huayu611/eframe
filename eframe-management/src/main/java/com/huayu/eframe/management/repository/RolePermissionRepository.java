package com.huayu.eframe.management.repository;

import com.huayu.eframe.management.bo.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/7/14.
 */
public interface RolePermissionRepository extends JpaRepository<RolePermission,Long>
{
}
