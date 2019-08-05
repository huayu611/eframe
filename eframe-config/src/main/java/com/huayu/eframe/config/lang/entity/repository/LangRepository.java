package com.huayu.eframe.config.lang.entity.repository;

import com.huayu.eframe.config.lang.entity.bo.LangValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LangRepository extends JpaRepository<LangValue, Long> , JpaSpecificationExecutor<LangValue>
{
}
