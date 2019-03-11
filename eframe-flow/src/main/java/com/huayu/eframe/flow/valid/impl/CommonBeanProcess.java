package com.huayu.eframe.flow.valid.impl;

import com.huayu.eframe.flow.constant.FlowErrorCode;
import com.huayu.eframe.flow.valid.ValidBeanDefined;
import com.huayu.eframe.server.common.LanguageCode;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by Leo on 2018/9/26.
 */
@Configuration
public class CommonBeanProcess
{
    private static final LogDebug debug = new LogDebug(CommonBeanProcess.class);

    //驗證語言
    @Bean("_e_validLang")
    public ValidBeanDefined validLang()
    {
        ValidBeanDefined v = (field, value, request) ->
        {

            String valueString = StringUtils.getString(value);
            if (StringUtils.isNullOrEmpty(valueString))
            {
                return;
            }
            if (value.getClass().isAssignableFrom(String.class))
            {

                debug.log(valueString);
                if (!LanguageCode.existLanguageCode(valueString))
                {

                    throw new IFPException(FlowErrorCode.LANG_CODE_INCORRECT, "Lang code incorrect", new String[]{valueString});
                }
                return;
            }
            throw new IFPException(FlowErrorCode.LANG_CODE_INCORRECT, "Lang code incorrect", new String[]{field.getName()});
        };

        return v;
    }

    @Bean("_e_validEmail")
    public ValidBeanDefined validEmail()
    {
        ValidBeanDefined v = (field, value, request) ->
        {

            String valueString = StringUtils.getString(value);
            if (StringUtils.isNullOrEmpty(valueString))
            {
                return;
            }

            if (value instanceof String)
            {

                String pattern = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
                boolean isMatch = Pattern.matches(pattern, valueString);
                if (isMatch)
                {
                    return;
                }
            }

            throw new IFPException(FlowErrorCode.EMAIL_FORMATTING_INCORRECT, "Email formatting incorrect", new String[]{field.getName()});

        };
        return v;
    }

    @Bean("_e_defaultNow")
    public ValidBeanDefined setDefaultNowDateToEffectiveTime()
    {
        ValidBeanDefined v = (field, value, request) ->
        {
            String valueString = StringUtils.getString(value);
            if (StringUtils.isNullOrEmpty(valueString))
            {
                Date now = DateUtils.getCurrentDate();
                try
                {
                    field.set(request, now);
                }
                catch (IllegalAccessException e)
                {
                    debug.errorLog("Set default effective time error");
                }
            }
        };
        return v;
    }

    @Bean("_e_defaultExpireTime")
    public ValidBeanDefined setDefaultExpireTime()
    {
        ValidBeanDefined v = (field, value, request) ->
        {
            String valueString = StringUtils.getString(value);
            if (StringUtils.isNullOrEmpty(valueString))
            {
                Date defaultExpireDate = DateUtils.getDefaultExpireDate();
                try
                {
                    field.set(request, defaultExpireDate);
                }
                catch (IllegalAccessException e)
                {
                    debug.errorLog("Set default expire time error");
                }
            }
        };

        return v;
    }

    @Bean("_e_validExpireTime")
    public ValidBeanDefined validExpireTime()
    {
        ValidBeanDefined v = (field, value, request) ->
        {
            if (value == null)
            {
                return;
            }
            Date expireDate = (Date) value;
            if (expireDate.compareTo(LocalAttribute.getNow()) <= 0)
            {
                throw new IFPException(ErrorCode.REQUEST_EXPIRETIME_BEFORE_CURRET_TIME, "Expire time can not before current time");
            }

        };
        return v;
    }

    @Bean("_e_validPhoneNumber")
    public ValidBeanDefined validPhoneNumber()
    {
        ValidBeanDefined v = (field, value, request) ->
        {

            String valueString = StringUtils.getString(value);
            if (StringUtils.isNullOrEmpty(valueString))
            {
                return;
            }

            if (value instanceof String)
            {

                String pattern = "^(1)\\d{10}$";
                boolean isMatch = Pattern.matches(pattern, valueString);
                if (isMatch)
                {
                    return;
                }
            }

            throw new IFPException(FlowErrorCode.MOBILE_NUMBER_FORMATTING_INCORRECT, "Mobile number formatting incorrect!", new String[]{field.getName()});

        };
        return v;
    }
}
