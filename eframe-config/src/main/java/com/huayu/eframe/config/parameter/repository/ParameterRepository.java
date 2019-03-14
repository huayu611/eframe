package com.huayu.eframe.config.parameter.repository;

import com.huayu.eframe.config.parameter.bo.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterRepository extends JpaRepository<Parameter,Long>
{
}
