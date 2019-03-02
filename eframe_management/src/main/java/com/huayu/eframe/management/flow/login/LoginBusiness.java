package com.huayu.eframe.management.flow.login;

import com.huayu.eframe.management.common.token.ManagerToken;
import com.huayu.eframe.management.constant.SecurityConstant;
import com.huayu.eframe.management.request.LoginRequest;
import com.huayu.eframe.management.single.StaffService;
import com.huayu.eframe.management.single.bo.StaffDetail;
import com.huayu.eframe.server.common.ConfigurationUtils;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.flow.FlowConstant;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.mvc.token.Token;
import com.huayu.eframe.server.mvc.token.TokenManager;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by Administrator on 2018/7/21.
 */
@Service
public class LoginBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(LoginBusiness.class);

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

        LocalAttribute.addToken(etk);

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
        return ConfigurationUtils.getDefaultLocal();
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
