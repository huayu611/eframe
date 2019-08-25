package com.huayu.eframe.global.dict.entity.repository;

import com.huayu.eframe.global.dict.entity.bo.DictParamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Leo on 2019/8/24.
 */
public interface DictParamEntityRepository extends JpaRepository<DictParamEntity,Long>, JpaSpecificationExecutor<DictParamEntity>
{
}
