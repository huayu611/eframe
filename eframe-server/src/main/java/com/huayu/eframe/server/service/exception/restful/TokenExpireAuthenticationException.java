package com.huayu.eframe.server.service.exception.restful;

import com.huayu.eframe.server.service.exception.ErrorCode;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Leo on 2018/12/15.
 */
public class TokenExpireAuthenticationException extends AuthenticationException implements ErrorCodeException
{
    public TokenExpireAuthenticationException(String msg)
    {
        super(msg);
    }

    public TokenExpireAuthenticationException(String msg, Throwable t)
    {
        super(msg, t);
    }

    @Override
    public String getErrorCode()
    {
        return ErrorCode.REST_SESSION_EXPIRE;
    }

    @Override
    public int getHttpCode()
    {
        return HttpServletResponse.SC_UNAUTHORIZED;
    }

    @Override
    public String getHttpDesc()
    {
        return "Access Denied";
    }
}
