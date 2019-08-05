package com.huayu.eframe.config.lang.entity.repository;

import com.huayu.eframe.config.lang.entity.bo.LangText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LangTextRepository extends JpaRepository<LangText, Long> , JpaSpecificationExecutor<LangText>
{
}
