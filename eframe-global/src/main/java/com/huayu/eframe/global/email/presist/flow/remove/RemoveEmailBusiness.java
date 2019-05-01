package com.huayu.eframe.global.email.presist.flow.remove;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.global.email.presist.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/3/11.
 */
@Service
public class RemoveEmailBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "RemoveEmailBusiness_RESULT";

    @Autowired
    private EmailService emailService;

    @Override
    public void execute(BusinessParameter param)
    {
        RemoveEmailRequest removeEmailRequest = param.getRequest();
        String result = deleteInBatch(code -> emailService.deleteEmail(code), removeEmailRequest.getEmailCode());
        param.addParameter(RESULT, result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }
}
