package com.huayu.eframe.management.repository;

import com.huayu.eframe.management.bo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2018/7/14.
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role>
{
}
