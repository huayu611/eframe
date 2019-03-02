package com.huayu.eframe.server.common.email.atom;

import com.huayu.eframe.server.common.email.bo.EmailBO;

import java.util.List;

/**
 * Created by Leo on 2018/9/28.
 */
public interface EmailAtom
{
    EmailBO insert(EmailBO emailBO);

    EmailBO update(EmailBO emailBO);

    List<EmailBO> getAllEmail();

    void delete(EmailBO emailBO);
}
