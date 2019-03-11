package com.huayu.eframe.global.email.presist.cache;

import com.huayu.eframe.global.email.presist.bo.EmailBO;

/**
 * Created by Leo on 2018/9/28.
 */
public interface EmailCache
{

    String CACHE_NAME = "SYS_EMAIL";

    EmailBO getEmailByCode(String emailCode);
}
