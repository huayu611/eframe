package com.huayu.eframe.config.parameter.repository;

import com.huayu.eframe.config.parameter.bo.ParameterCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterCategoryRepository extends JpaRepository<ParameterCategory, Long>
{
}
