package com.huayu.eframe.management.repository;

import com.huayu.eframe.management.bo.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/7/14.
 */
public interface PermissionRepository extends JpaRepository<Permission,Long>, JpaSpecificationExecutor<Permission>
{
    List<Permission> findByPermissionCodeLike(String name);


    @Query(value = "SELECT * FROM SYS_PERMISSION WHERE  and EXPIRE_TIME >?1 and STATUS<>'D'",
            countQuery = "SELECT COUNT(1) FROM SYS_PERMISSION WHERE  and EXPIRE_TIME >?1 and STATUS<>'D'",
            nativeQuery = true)
    Page<Permission> findAllByPage(Date currentTime, Pageable pageable);
}
