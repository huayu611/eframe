package com.huayu.eframe.global.email.presist.service;

import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.PagingRequest;

/**
 * Created by Leo on 2018/9/28.
 */
public interface EmailService
{
    String NORMAL_STATUS = "2";

    String DELETE_STATUS = "D";

    EmailDetail addEmail(EmailDetail emailDetail);

    EmailDetail queryEmailByCode(String emailCode);

    String deleteEmail(String emailCode);

    EmailDetail updateEmail(EmailDetail emailDetail);

    PageObject queryEmailByPage(PagingRequest pagingRequest, EmailDetail emailDetail);
}
