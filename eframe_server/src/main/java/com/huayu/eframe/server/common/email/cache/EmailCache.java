package com.huayu.eframe.server.common.email.cache;

import com.huayu.eframe.server.common.email.bo.EmailBO;

/**
 * Created by Leo on 2018/9/28.
 */
public interface EmailCache
{

    String CACHE_NAME = "SYS_EMAIL";

    EmailBO getEmailByCode(String emailCode);
}
