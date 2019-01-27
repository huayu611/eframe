package com.huayu.eframe.server.config.table.repository;

import com.huayu.eframe.server.config.table.bo.ParameterCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterCategoryRepository extends JpaRepository<ParameterCategory,Long>
{
}
