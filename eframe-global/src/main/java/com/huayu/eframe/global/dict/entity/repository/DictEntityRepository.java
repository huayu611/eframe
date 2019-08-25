package com.huayu.eframe.global.dict.entity.repository;

import com.huayu.eframe.global.dict.entity.bo.DictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Leo on 2019/8/24.
 */
public interface DictEntityRepository extends JpaRepository<DictEntity,Long>, JpaSpecificationExecutor<DictEntity>
{
}
