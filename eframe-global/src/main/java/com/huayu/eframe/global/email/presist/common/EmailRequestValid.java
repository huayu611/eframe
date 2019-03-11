package com.huayu.eframe.global.email.presist.common;

import com.huayu.eframe.flow.valid.ValidBeanDefined;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Leo on 2019/3/10.
 */
@Configuration
public class EmailRequestValid
{
    private static final LogDebug debug = new LogDebug(EmailRequestValid.class);

    @Bean("_e_email_password_encrypt")
    public ValidBeanDefined generateTemplateCode()
    {
        ValidBeanDefined v = (field, value, request) ->
        {
            String valueString = StringUtils.getString(value);
            if (StringUtils.isNotNullAndEmpty(valueString))
            {
                String nowStr = EmailUtils.encryptPassword(valueString);
                try
                {
                    field.set(request, nowStr);
                }
                catch (IllegalAccessException e)
                {
                    debug.log("Set default generate code fault");
                }
            }
        };
        return v;
    }
}
