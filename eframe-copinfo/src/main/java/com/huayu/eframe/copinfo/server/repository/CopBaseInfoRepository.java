package com.huayu.eframe.copinfo.server.repository;

import com.huayu.eframe.copinfo.server.bo.CopBaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Leo on 2019/1/18.
 */
public interface CopBaseInfoRepository extends JpaRepository<CopBaseInfo, Long>, JpaSpecificationExecutor<CopBaseInfo>
{
}
