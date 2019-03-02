package com.huayu.eframe.config.repository;

import com.huayu.eframe.config.bo.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterRepository extends JpaRepository<Parameter,Long>
{
}
