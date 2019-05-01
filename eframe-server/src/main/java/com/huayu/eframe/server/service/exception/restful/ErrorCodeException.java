package com.huayu.eframe.server.service.exception.restful;

/**
 * Created by Leo on 2018/12/15.
 */
public interface ErrorCodeException
{
    String getErrorCode();

    default int getHttpCode()
    {
        return 400;
    }

    ;

    default String getHttpDesc()
    {
        return "Bad Request";
    }

    ;
}
