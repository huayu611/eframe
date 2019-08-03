package com.huayu.eframe.server.common.i18n.table.repository;

import com.huayu.eframe.server.common.i18n.table.bo.Lang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LangRepository extends JpaRepository<Lang, Long> , JpaSpecificationExecutor<Lang>
{
}
