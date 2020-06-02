package com.huayu.eframe.server.mvc.handler;

import com.alibaba.dubbo.common.utils.IOUtils;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Add by Leo at 2018-08-25
 * Spring MVC 自定义参数传递
 */
public class EFrameHandlerMethodReturnValueHandler implements HandlerMethodArgumentResolver
{

    private final static LogDebug log = new LogDebug(EFrameHandlerMethodReturnValueHandler.class);

    private static final String JSONBODYATTRIBUTE = "JSON_REQUEST_BODY";

    @Override
    public boolean supportsParameter(MethodParameter methodParameter)
    {
        return EasyParam.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception
    {
        EasyParam easyParam = new EasyParam();
        easyParam.setRequest(nativeWebRequest.getNativeRequest(HttpServletRequest.class));
        easyParam.setResponse(nativeWebRequest.getNativeResponse(HttpServletResponse.class));

        Map<String, String> map = new HashMap<>();
        Map<String, String[]> mapValues = new HashMap<>();
        Iterator<String> headers = nativeWebRequest.getHeaderNames();
        while (headers.hasNext())
        {
            String headerName = headers.next();
            log.log(headerName);
            String value = nativeWebRequest.getHeader(headerName);
            if (StringUtils.isNotNullAndEmpty(value))
            {
                map.put(headerName, value);
            }
            log.log(headerName);
            log.log(value);
            String[] values = nativeWebRequest.getHeaderValues(headerName);
            if (values.length > 0)
            {
                mapValues.put(headerName, values);
            }

        }

        easyParam.setRequestHeader(map);
        easyParam.setRequestHeaders(mapValues);

        getRequestBody(nativeWebRequest);
        return easyParam;
    }

    private String getRequestBody(NativeWebRequest webRequest)
    {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        String jsonBody = "";
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(servletRequest.getInputStream()));
            jsonBody = IOUtils.read(bufferedReader);
            webRequest.setAttribute(JSONBODYATTRIBUTE, jsonBody, NativeWebRequest.SCOPE_REQUEST);
        }
        catch (IOException e)
        {
//                throw new RuntimeException(e);
        }

        return jsonBody;
    }


}
