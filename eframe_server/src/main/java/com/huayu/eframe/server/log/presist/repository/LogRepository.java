package com.huayu.eframe.server.log.presist.repository;

import com.huayu.eframe.server.log.presist.bo.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Leo on 2019/2/13.
 */
public interface LogRepository extends JpaRepository<LogEntity,Long>, JpaSpecificationExecutor<LogEntity>
{
}
