package com.huayu.eframe.global.email.presist.cache.impl;

import com.huayu.eframe.global.email.presist.atom.EmailAtom;
import com.huayu.eframe.global.email.presist.bo.EmailBO;
import com.huayu.eframe.global.email.presist.cache.EmailCache;
import com.huayu.eframe.server.cache.frame.AbstractFrameCache;
import com.huayu.eframe.server.cache.frame.Index;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2018/9/28.
 */
@Service
public class EmailCacheImpl extends AbstractFrameCache<EmailBO> implements EmailCache
{
    @Autowired
    private EmailAtom emailAtom;

    public EmailCacheImpl()
    {
        registerIndex(new EmailCodeIndex());
    }


    static class EmailCodeIndex implements Index<EmailBO>
    {

        @Override
        public String getIndex(EmailBO emailBO)
        {
            return emailBO.getEmailCode();
        }
    }

    @Override
    public EmailBO getEmailByCode(String emailCode)
    {
        EmailBO emailBO = new EmailBO();
        emailBO.setEmailCode(emailCode);
        List<EmailBO> result = getResultByIndex(EmailCodeIndex.class, emailBO);
        return CollectionUtils.getFirstElement(result);

    }

    @Override
    public List<EmailBO> load()
    {
        return emailAtom.getAllEmail();
    }

    @Override
    public String cacheName()
    {
        return EmailCache.CACHE_NAME;
    }
}
