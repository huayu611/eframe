package com.huayu.eframe.timetask.entity.repository;

import com.huayu.eframe.timetask.entity.bo.TimeTaskBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by Leo on 2019/4/15.
 */
public interface TimeTaskRepository extends JpaRepository<TimeTaskBO, Long>, JpaSpecificationExecutor<TimeTaskBO>
{
    //这个SQL只是用来处理定时任务完成，更新 次数用，单独给他起一个事务。
    @Modifying
    @Query(value = "update sys_timetask set execute_count = execute_count+1, next_execute_time=:nextTime where id=:id",nativeQuery = true)
    void updateTimeTaskAddExecuteCount(@Param("id") Long id,@Param("nextTime") Date nextTime);
}
