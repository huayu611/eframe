package com.huayu.eframe.server.security.service.repository;

import com.huayu.eframe.server.security.service.bo.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/7/14.
 */
public interface StaffRepository extends JpaRepository<Staff,Long>, JpaSpecificationExecutor<Staff>
{
    @Query(value = "select * from SYS_STAFF where LOGIN_NAME=:loginName",nativeQuery = true)
    public List<Staff> findStaffByLoginName(@Param("loginName") String staffname);
}
