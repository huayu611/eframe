package com.huayu.eframe.config.lang.entity.repository;

import com.huayu.eframe.config.lang.entity.bo.LangDefine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Leo on 2019/7/22.
 */
public interface LangDefineRepository extends JpaRepository<LangDefine, Long>
{
}
