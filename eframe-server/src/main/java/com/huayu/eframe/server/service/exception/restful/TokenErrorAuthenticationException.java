package com.huayu.eframe.server.service.exception.restful;

import com.huayu.eframe.server.service.exception.ErrorCode;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Leo on 2018/12/15.
 */
public class TokenErrorAuthenticationException extends AuthenticationException implements ErrorCodeException
{
    public TokenErrorAuthenticationException(String msg)
    {
        super(msg);
    }

    public TokenErrorAuthenticationException(String msg, Throwable t)
    {
        super(msg, t);
    }

    @Override
    public String getErrorCode()
    {
        return ErrorCode.TOKEN_COVERT_ERROR;
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
