package com.huayu.eframe.server.common.email.atom.impl;

import com.huayu.eframe.server.common.email.atom.EmailAtom;
import com.huayu.eframe.server.common.email.bo.EmailBO;
import com.huayu.eframe.server.common.email.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2018/9/28.
 */
@Service
public class EmailAtomImpl implements EmailAtom
{
    @Autowired
    private EmailRepository emailRepository;

    @Override
    public EmailBO insert(EmailBO emailBO)
    {
        return emailRepository.save(emailBO);
    }

    @Override
    public EmailBO update(EmailBO emailBO)
    {
        return emailRepository.save(emailBO);
    }

    @Override
    public List<EmailBO> getAllEmail()
    {
        return emailRepository.findAll();
    }

    @Override
    public void delete(EmailBO emailBO)
    {
         emailRepository.delete(emailBO);
    }
}
