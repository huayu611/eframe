package com.huayu.eframe.config.parameter.repository;

import com.huayu.eframe.config.parameter.bo.ParameterDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterDescriptionRepository extends JpaRepository<ParameterDescription, Long>
{
}
