package com.huayu.eframe.server.security.service.flow.login;

import com.huayu.eframe.server.config.properties.SystemConfig;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.flow.FlowConstant;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.mvc.token.Token;
import com.huayu.eframe.server.mvc.token.TokenManager;
import com.huayu.eframe.server.security.config.ManagerToken;
import com.huayu.eframe.server.security.service.constant.SecurityConstant;
import com.huayu.eframe.server.security.service.request.LoginRequest;
import com.huayu.eframe.server.security.service.single.bo.StaffDetail;
import com.huayu.eframe.server.security.service.single.StaffService;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by Administrator on 2018/7/21.
 */
@Service("LoginBusiness")
public class LoginBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(LoginBusiness.class);

    private static final String SYSTEM_ENV_LANGUAGE = "sys_staff_lang_default";

    private static final String DEFAULT_SYSTEM_ENV_LANGUAGE = "zh";

    private static final String LOGIN_RESULT = "LoginBusiness_RESULT";

    @Autowired
    private StaffService staffService;

    @Override
    public void execute(BusinessParameter param)
    {
        debug.beginLog();
        LoginRequest request = param.getRequest();
        String loginName = request.getLogin();
        String password = request.getPassword();
        StaffDetail staffDetail = staffService.checkLogin(loginName, password);
        LoginResponse response = getLoginResponse(request, staffDetail);
        param.addParameter(LOGIN_RESULT,response);


        debug.endLog();
    }



    @Override
    protected Object tidyData(BusinessParameter param)
    {
        LoginResponse response = param.getParameter(LOGIN_RESULT);
        debug.log(response);
        return response;
    }


    public LoginResponse getLoginResponse( LoginRequest request, StaffDetail staff )
    {
        LoginResponse response = new LoginResponse();

        String loginType = StringUtils.isNullOrEmpty(request.getType()) ? SecurityConstant.LOGIN_TYPE.LOGIN_REFRESH_EXPIRE_TIME : request.getType();

        Token etk = TokenManager.createToken(ManagerToken.class, staff.getLogin(), loginType,getLocale(request, staff));

        httpRequestConver(etk);

        response.setToken(etk.getToken());
        response.setExpireTime(DateUtils.dateToString(etk.getExpireTime()));
        response.setStaff(staff);
        return response;
    }

    private Locale getLocale(LoginRequest request, StaffDetail staff)
    {
        if(StringUtils.isNotNullAndEmpty(request.getLang()))
        {
            return new Locale(request.getLang());
        }
        String lang = staff.getLang();
        if(StringUtils.isNotNullAndEmpty(lang))
        {
             return new Locale(lang);
        }
        String langConfig = SystemConfig.getValue(SYSTEM_ENV_LANGUAGE, DEFAULT_SYSTEM_ENV_LANGUAGE);
        return new Locale(langConfig);
    }

    private void httpRequestConver(Token etk)
    {
        HttpServletRequest httpRequest = LocalAttribute.getValue(FlowConstant.HTTP_REQUEST);
        if (null != httpRequest) {
            debug.log("Success set http token in servlet");
            httpRequest.setAttribute(FlowConstant.HTTP_TOKEN, etk);
        }
        LocalAttribute.addToken(etk);
    }

}
