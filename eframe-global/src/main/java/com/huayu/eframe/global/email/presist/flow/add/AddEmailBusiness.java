package com.huayu.eframe.global.email.presist.flow.add;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.global.constants.GlobalErrorCode;
import com.huayu.eframe.global.email.presist.service.EmailDetail;
import com.huayu.eframe.global.email.presist.service.EmailService;
import com.huayu.eframe.server.service.exception.IFPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/3/10.
 */
@Service
public class AddEmailBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "AddEmailBusiness_RESULT";

    @Autowired
    private EmailService emailService;

    @Override
    public void before(BusinessParameter param)
    {
        AddEmailRequest addEmailRequest = param.getRequest();
        EmailDetail emailDetail = emailService.queryEmailByCode(addEmailRequest.getEmailCode());
        if (null != emailDetail)
        {
            throw new IFPException(GlobalErrorCode.EMAIL_CODE_EXIST_ALREADY, "Email exist already", new String[]{addEmailRequest.getEmailCode()});
        }
    }

    @Override
    public void execute(BusinessParameter param)
    {
        AddEmailRequest addEmailRequest = param.getRequest();
        EmailDetail emailDetail = buildEmailDetail(addEmailRequest);
        EmailDetail emailDetailResult = emailService.addEmail(emailDetail);
        param.addParameter(RESULT, emailDetailResult);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }

    private EmailDetail buildEmailDetail(AddEmailRequest addEmailRequest)
    {
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setEmailCode(addEmailRequest.getEmailCode());
        emailDetail.setEmailPassword(addEmailRequest.getEmailPassword());
        emailDetail.setHost(addEmailRequest.getHost());
        emailDetail.setProtocol(addEmailRequest.getProtocol());
        emailDetail.setAuth(addEmailRequest.getAuth());
        emailDetail.setSender(addEmailRequest.getSender());
        emailDetail.setPort(addEmailRequest.getPort());
        emailDetail.setEmailUserName(addEmailRequest.getEmailUserName());
        emailDetail.setTimeOut(addEmailRequest.getTimeOut());
        emailDetail.setName(addEmailRequest.getName());
        emailDetail.setPersonal(addEmailRequest.getPersonal());
        emailDetail.setSubject(addEmailRequest.getSubject());
        return emailDetail;
    }
}
