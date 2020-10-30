package com.huayu.eframe.timetask.entity.repository;

import com.huayu.eframe.timetask.entity.bo.AsynchronousOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Leo on 2020/10/30.
 */
public interface AsynchronousOrderRepository extends JpaRepository<AsynchronousOrder, Long>, JpaSpecificationExecutor<AsynchronousOrder>
{
}
