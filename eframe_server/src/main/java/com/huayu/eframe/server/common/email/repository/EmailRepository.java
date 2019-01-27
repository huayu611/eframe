package com.huayu.eframe.server.common.email.repository;

import com.huayu.eframe.server.common.email.bo.EmailBO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Leo on 2018/9/28.
 */
public interface EmailRepository extends JpaRepository<EmailBO, Long>
{
}
