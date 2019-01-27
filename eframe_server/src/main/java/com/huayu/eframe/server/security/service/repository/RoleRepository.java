package com.huayu.eframe.server.security.service.repository;

import com.huayu.eframe.server.security.service.bo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2018/7/14.
 */
public interface RoleRepository extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role>
{
}
