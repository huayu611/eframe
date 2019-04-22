package com.huayu.eframe.management.repository;

import com.huayu.eframe.management.bo.StaffExt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Leo on 2019/4/22.
 */
public interface StaffExtRepository  extends JpaRepository<StaffExt,Long>, JpaSpecificationExecutor<StaffExt>
{
}
