package com.huayu.eframe.global.email.presist.repository;

import com.huayu.eframe.global.email.presist.bo.EmailBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Leo on 2018/9/28.
 */
public interface EmailRepository extends JpaRepository<EmailBO, Long>, JpaSpecificationExecutor<EmailBO>
{
}
