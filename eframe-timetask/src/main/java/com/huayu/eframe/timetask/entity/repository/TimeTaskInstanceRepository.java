package com.huayu.eframe.timetask.entity.repository;

import com.huayu.eframe.timetask.entity.bo.TimeTaskInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Leo on 2019/4/15.
 */
public interface TimeTaskInstanceRepository extends JpaRepository<TimeTaskInstance, Long>, JpaSpecificationExecutor<TimeTaskInstance>
{
}
