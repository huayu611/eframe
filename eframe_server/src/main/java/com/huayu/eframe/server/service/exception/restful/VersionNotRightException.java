package com.huayu.eframe.server.service.exception.restful;

import com.huayu.eframe.server.service.exception.ErrorCode;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by Leo on 2019/1/14.
 */
public class VersionNotRightException extends AuthenticationException implements ErrorCodeException
{

    public VersionNotRightException(String msg)
    {
        super(msg);
    }

    public VersionNotRightException(String msg, Throwable t)
    {
        super(msg, t);
    }
    @Override
    public String getErrorCode()
    {
        return ErrorCode.VERSION_WRONG;
    }

    @Override
    public int getHttpCode()
    {
        return 400;
    }

    @Override
    public String getHttpDesc()
    {
        return "Bad request";
    }
}
