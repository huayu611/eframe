package com.huayu.eframe.server.common.i18n.table.repository;

import com.huayu.eframe.server.common.i18n.table.bo.Lang;
import com.huayu.eframe.server.common.i18n.table.bo.LangText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LangTextRepository extends JpaRepository<LangText, Long> , JpaSpecificationExecutor<LangText>
{
}
