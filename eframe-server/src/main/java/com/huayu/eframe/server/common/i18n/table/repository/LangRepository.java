package com.huayu.eframe.server.common.i18n.table.repository;

import com.huayu.eframe.server.common.i18n.table.bo.Lang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LangRepository extends JpaRepository<Lang,Long>
{
}
