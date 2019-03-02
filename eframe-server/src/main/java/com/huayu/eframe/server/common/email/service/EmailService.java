package com.huayu.eframe.server.common.email.service;

import com.huayu.eframe.server.common.email.bo.EmailBO;

/**
 * Created by Leo on 2018/9/28.
 */
public interface EmailService
{
    EmailBO save(EmailBO emailBO);

    void delete(EmailBO emailBO);

    EmailBO update(EmailBO emailBO);
}
