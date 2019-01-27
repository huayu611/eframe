package com.huayu.eframe.server.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.flow.restful.RestfulResponse;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.ExceptionCacheService;
import com.huayu.eframe.server.service.exception.restful.ErrorCodeException;
import com.huayu.eframe.server.tool.basic.JSonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

/**
 * Created by Leo on 2018/10/12.
 */
public class EFrameRestfulAuthenticationEntryPoint implements AuthenticationEntryPoint
{

    private static final LogDebug debug = new LogDebug(EFrameRestfulAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException
    {
        try
        {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/javascript;charset=utf-8");
            debug.log(authException.getMessage());
            String jsonResult = buildAuthenticationFailedDescrption(response,authException);
            response.getWriter().print(jsonResult);
        }
        catch (Exception e)
        {
            debug.log(e);
        }
        finally
        {
            response.getWriter().close();
        }

    }

    protected String buildAuthenticationFailedDescrption(HttpServletResponse response,AuthenticationException authException) throws IOException
    {
        RestfulResponse error = new RestfulResponse();
        String code = ErrorCode.UNKNOW_ERROR;
        if (authException instanceof ErrorCodeException)
        {
            ErrorCodeException exceptionErrorCode = (ErrorCodeException) authException;
            code = exceptionErrorCode.getErrorCode();
            response.sendError(exceptionErrorCode.getHttpCode(), exceptionErrorCode.getHttpDesc());

        }
        String result = "Authentication failed!";
        try
        {
            String resultCode = code + "_ERRORDESC";
            result = ExceptionCacheService.getErrorInfo(resultCode, null, null);

        }
        catch (Exception e)
        {
            result = "Error!";
        }
        error.setCode(code);
        error.setMsg(result);
        response.addHeader("code",error.getCode());
        String msg = error.getCode();
        try
        {
            msg = URLEncoder.encode(error.getMsg(),"UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
        }
        response.addHeader("msg",msg);
        String jsonResult = JSonUtils.coverToJson(error);

        debug.log(jsonResult);
        return jsonResult;
    }
}
