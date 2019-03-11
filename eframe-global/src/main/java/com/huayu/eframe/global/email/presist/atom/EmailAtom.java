package com.huayu.eframe.global.email.presist.atom;

import com.huayu.eframe.global.email.presist.bo.EmailBO;
import com.huayu.eframe.server.common.FramePaging;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Leo on 2018/9/28.
 */
public interface EmailAtom
{
    EmailBO insert(EmailBO emailBO);

    EmailBO update(EmailBO emailBO);

    EmailBO queryEmailByCode(String code);

    Page<EmailBO> queryEmailByPage(FramePaging fp, EmailBO email);

    List<EmailBO> getAllEmail();

    List<EmailBO> getAllValidEmail();

    void delete(EmailBO emailBO);
}
