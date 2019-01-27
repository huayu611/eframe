package com.huayu.eframe.server.common.email.service.impl;

import com.huayu.eframe.server.common.email.atom.EmailAtom;
import com.huayu.eframe.server.common.email.bo.EmailBO;
import com.huayu.eframe.server.common.email.cache.EmailCache;
import com.huayu.eframe.server.common.email.service.EmailService;
import com.huayu.eframe.server.context.LocalAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2018/9/28.
 */
@Service
public class EmailServiceImpl implements EmailService
{
    @Autowired
    private EmailAtom emailAtom;
    @Override
    public EmailBO save(EmailBO emailBO)
    {
        EmailBO result =  emailAtom.insert(emailBO);
        LocalAttribute.addNeedRefreshCache(EmailCache.CACHE_NAME);
        return result;
    }

    @Override
    public void delete(EmailBO emailBO)
    {
        emailAtom.delete(emailBO);
        LocalAttribute.addNeedRefreshCache(EmailCache.CACHE_NAME);
    }

    @Override
    public EmailBO update(EmailBO emailBO)
    {
        EmailBO result = emailAtom.update(emailBO);
        LocalAttribute.addNeedRefreshCache(EmailCache.CACHE_NAME);
        return result;
    }
}
