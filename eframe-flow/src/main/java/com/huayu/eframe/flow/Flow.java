package com.huayu.eframe.flow;

import com.huayu.eframe.flow.common.FlowConstant;
import com.huayu.eframe.server.common.ConfigurationUtils;
import com.huayu.eframe.server.common.Constant;
import com.huayu.eframe.server.common.restful.RestfulResponse;
import com.huayu.eframe.server.config.properties.SystemConfig;
import com.huayu.eframe.server.config.rest.RestErrorCodeMappingFacade;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import com.huayu.eframe.server.mvc.token.Token;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/17.
 */
public class Flow
{
    private static final LogDebug debug = new LogDebug(Flow.class);

    private static final String MULTI_LANGUAGE = "sys_eframe_language_request_header";

    private static final String MULTI_LANGUAGE_DEFAULT = "ELang";
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
                if (null == locale)
                {
                    locale = ConfigurationUtils.getDefaultLocal();
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

        //对于easy frame，要提防定制边对request进行定制。无法构造真实的easy param.
        if (null != easyParam.getRequest())
        {
            Token token = (Token) easyParam.getRequest().getAttribute(Constant.HTTP_TOKEN);

            LocalAttribute.addToken(token);

            debug.log(token);
        }

        if( null != easyParam.getRequestHeader())
        {
            Map<String, String> header = easyParam.getRequestHeader();
            String headLang = SystemConfig.getValue(MULTI_LANGUAGE, MULTI_LANGUAGE_DEFAULT);
            String customerLang = header.get(headLang);
            if(StringUtils.isNullOrEmpty(customerLang))
            {
                customerLang = "zh";
            }
            LocalAttribute.addValue(Constant.HEAD_CUSTOMER_REQUEST_LANGUAGE, customerLang);
        }

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
