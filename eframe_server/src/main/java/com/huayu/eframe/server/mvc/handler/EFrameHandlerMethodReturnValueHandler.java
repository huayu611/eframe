package com.huayu.eframe.server.mvc.handler;

import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Add by Leo at 2018-08-25
 * Spring MVC 自定义参数传递
 */
public class EFrameHandlerMethodReturnValueHandler implements HandlerMethodArgumentResolver
{

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
            String value = nativeWebRequest.getHeader(headerName);
            if (StringUtils.isNotNullAndEmpty(value))
            {
                map.put(headerName, value);
            }

            String[] values = nativeWebRequest.getHeaderValues(headerName);
            if (values.length > 0)
            {
                mapValues.put(headerName, values);
            }

        }

        easyParam.setRequestHeader(map);
        easyParam.setRequestHeaders(mapValues);
        return easyParam;
    }




}
