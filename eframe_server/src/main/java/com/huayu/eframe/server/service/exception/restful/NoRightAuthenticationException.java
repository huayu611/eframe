package com.huayu.eframe.server.service.exception.restful;

import com.huayu.eframe.server.service.exception.ErrorCode;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Leo on 2018/12/15.
 */
public class NoRightAuthenticationException extends AuthenticationException implements ErrorCodeException
{
    public NoRightAuthenticationException(String msg)
    {
        super(msg);
    }

    public NoRightAuthenticationException(String msg, Throwable t)
    {
        super(msg, t);
    }

    @Override
    public String getErrorCode()
    {
        return ErrorCode.AUTHENTICATION_FAILED;
    }


    @Override
    public int getHttpCode()
    {
        return HttpServletResponse.SC_FORBIDDEN;
    }

    @Override
    public String getHttpDesc()
    {
        return "Access Denied";
    }
}
