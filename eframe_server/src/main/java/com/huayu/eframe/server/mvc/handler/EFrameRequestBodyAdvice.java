package com.huayu.eframe.server.mvc.handler;

import com.huayu.eframe.server.log.LogDebug;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by Leo on 2019/2/18.
 */
//@Service
//@ControllerAdvice
//此类为预留，后续如果需要截取json体，需要用到，
// 当前 由于SpringMVC中对于InputStream不能复制。
//如果启用，需要替换SpringMVC中的servletRequest .并且重写input中的markdown和reset
public class EFrameRequestBodyAdvice extends RequestBodyAdviceAdapter
{
    private static final LogDebug debug = new LogDebug(EFrameRequestBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType)
    {
        return false;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter,
                                           Type targetType, Class<? extends HttpMessageConverter<?>> converterType)
            throws IOException
    {

//        InputStream inputBody = inputMessage.getBody();
//        debug.log(inputBody);
//        ByteArrayOutputStream byteInputStream = new ByteArrayOutputStream ();
//
//
//        byte[] buffer = new byte[1024];
//        int len;
//        while ((len = inputBody.read(buffer)) > -1 ) {
//            byteInputStream.write(buffer, 0, len);
//        }
//        byteInputStream.flush();
//
//
//        InputStream readJsonInLocal = new ByteArrayInputStream(byteInputStream.toByteArray());
//        InputStream sendToSpringMVC = new ByteArrayInputStream(byteInputStream.toByteArray());
//
//
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(readJsonInLocal));
//        String result = IOUtils.read(bufferedReader);
//        HttpLocalAttribute.setHttpJSONContent(result);
        return inputMessage;
    }
}
