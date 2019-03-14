package com.huayu.eframe.global.email.presist.flow.update;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.global.email.presist.service.EmailDetail;
import com.huayu.eframe.global.email.presist.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/3/11.
 */
@Service
public class ModifyEmailBusiness extends AbstractExecuteBusiness
{

    private final static String RESULT = "ModifyEmailBusiness_RESULT";

    @Autowired
    private EmailService emailService;


    @Override
    public void execute(BusinessParameter param)
    {
        ModifyEmailRequest modifyEmailRequest = param.getRequest();
        EmailDetail emailDetail = buildEmailDetail(modifyEmailRequest);
        EmailDetail updateEmailDetail = emailService.updateEmail(emailDetail);
        param.addParameter(RESULT,updateEmailDetail);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }

    private EmailDetail buildEmailDetail(ModifyEmailRequest modifyEmailRequest)
    {
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setEmailCode(modifyEmailRequest.getEmailCode());
        emailDetail.setEmailPassword(modifyEmailRequest.getEmailPassword());
        emailDetail.setHost(modifyEmailRequest.getHost());
        emailDetail.setAuth(modifyEmailRequest.getAuth());
        emailDetail.setSender(modifyEmailRequest.getSender());
        emailDetail.setPort(modifyEmailRequest.getPort());
        emailDetail.setEmailUserName(modifyEmailRequest.getEmailUserName());
        emailDetail.setTimeOut(modifyEmailRequest.getTimeOut());
        emailDetail.setProtocol(modifyEmailRequest.getProtocol());
        emailDetail.setName(modifyEmailRequest.getName());
        emailDetail.setPersonal(modifyEmailRequest.getPersonal());
        emailDetail.setSubject(modifyEmailRequest.getSubject());
        return emailDetail;
    }
}
