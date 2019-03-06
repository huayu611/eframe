package com.huayu.eframe.flow;

import com.huayu.eframe.flow.common.HttpUtils;
import com.huayu.eframe.server.common.ConfigurationUtils;
import com.huayu.eframe.flow.common.FlowConstant;
import com.huayu.eframe.server.common.Constant;
import com.huayu.eframe.server.common.restful.RestfulResponse;
import com.huayu.eframe.server.config.rest.RestErrorCodeMappingFacade;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import com.huayu.eframe.server.mvc.token.Token;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.service.spring.BeanPool;

import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by Administrator on 2018/7/17.
 */
public class Flow
{
    private static final LogDebug debug = new LogDebug(Flow.class);


    @SuppressWarnings("unchecked")
    private static Object executeService(Class serviceName, Object request)
    {
        Object result;

        try
        {

            ExecuteLogic executeLogic = BeanPool.getService("ExecuteLogic");
            ExecuteBusiness service = BeanPool.getService(serviceName);
            result = executeLogic.execute(service, request);
        }
        catch (Exception e)
        {
            debug.printErr(e);
            RestfulResponse error = new RestfulResponse();
            if (e instanceof IFPException)
            {
                Locale locale = null == LocalAttribute.getToken() ? null : LocalAttribute.getToken().getLocale();
                if(null == locale)
                {
                    locale = new Locale(HttpUtils.getBrowserLang());
                }
                debug.log(LocalAttribute.getToken());
                error.setCode(((IFPException) e).getErrorCode());
                error.setMsg(((IFPException) e).getErrorInfo(locale));
            }
            else
            {
                error.setCode(ErrorCode.UNKNOW_ERROR);
                error.setMsg(e.getMessage());
            }
            coverHttpCode(error);
            return error;
        }
        return result;
    }

    public static Object execute(Class serviceName, Object request, EasyParam easyParam)
    {

        LocalAttribute.init();

        LocalAttribute.addValue(FlowConstant.HTTP_REQUEST, easyParam.getRequest());

        LocalAttribute.addValue(FlowConstant.HTTP_RESPONSE, easyParam.getResponse());

        LocalAttribute.addValue(FlowConstant.EASY_SERVLET, easyParam);

        Token token = (Token) easyParam.getRequest().getAttribute(Constant.HTTP_TOKEN);

        LocalAttribute.addToken(token);

        debug.log(token);

        debug.log(easyParam.getRequest().getRemoteAddr());

        Object result = executeService(serviceName, request);

        debug.log(result);

        LocalAttribute.destroy();

        return result;
    }


    private static void coverHttpCode(RestfulResponse error)
    {
        String httpCode = RestErrorCodeMappingFacade.getHttpCode(error.getCode(), ConfigurationUtils.getDefaultHttpErrorStatus());
        debug.log(httpCode);
        HttpServletResponse response = LocalAttribute.getValue(FlowConstant.HTTP_RESPONSE);
        if (null == response)
        {
            debug.log("without response");
            return;
        }
        response.setStatus(Integer.valueOf(httpCode));
//        response.addHeader("code",error.getCode());
//        String msg = HttpUtils.enccodeURL(error.getMsg());
//
//        response.addHeader("msg",msg);
        debug.log(response);
    }

}
