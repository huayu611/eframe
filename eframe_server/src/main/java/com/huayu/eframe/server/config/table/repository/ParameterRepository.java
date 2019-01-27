package com.huayu.eframe.server.config.table.repository;

import com.huayu.eframe.server.config.table.bo.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterRepository extends JpaRepository<Parameter,Long>
{
}
