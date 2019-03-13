package com.huayu.eframe.global.email.process;

//import org.springframework.stereotype.Service;

import com.huayu.eframe.global.email.presist.bo.EmailBO;
import com.huayu.eframe.global.email.presist.cache.EmailCache;
import com.huayu.eframe.global.email.presist.common.EmailUtils;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Leo on 2019/3/10.
 */
@Service
public class SendEmail
{

    private static final LogDebug debug = new LogDebug(SendEmail.class);

    @Autowired
    private EmailCache emailCache;


    public boolean send(String code,String personal,String dest,String object,String text)
    {
        EmailBO email = emailCache.getEmailByCode(code);
        JavaMailSenderImpl mailSender =  createMailSender(email);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try
        {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom(email.getEmailSender(), personal);
            messageHelper.setTo(dest);
            messageHelper.setSubject(object);
            messageHelper.setText(text, true);
            mailSender.send(mimeMessage);
            return true;
        }catch(Exception e)
        {
            debug.errorLog("Send Email Failed");
            return false;
        }
    }


    private static JavaMailSenderImpl createMailSender( EmailBO email) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(email.getHost());
        sender.setPort(email.getPort());
        sender.setUsername(email.getEmailUserName());

        sender.setPassword(EmailUtils.decodePassword(email.getEmailPassword()));
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", StringUtils.getString(email.getTimeOut()));
        if(StringUtils.isNotNullAndEmpty(email.getAuth()))
        {
            p.setProperty("mail.smtp.auth", "true");
        }
        else
        {
            p.setProperty("mail.smtp.auth", "false");
        }

        sender.setJavaMailProperties(p);
        return sender;
    }
}
