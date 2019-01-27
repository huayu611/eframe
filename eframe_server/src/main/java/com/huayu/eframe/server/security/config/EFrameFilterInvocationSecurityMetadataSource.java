package com.huayu.eframe.server.security.config;

import com.huayu.eframe.server.config.properties.SystemConfig;
import com.huayu.eframe.server.flow.Flow;
import com.huayu.eframe.server.flow.FlowConstant;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import com.huayu.eframe.server.mvc.handler.EasyParamCreation;
import com.huayu.eframe.server.mvc.token.AuthView;
import com.huayu.eframe.server.mvc.token.Token;
import com.huayu.eframe.server.mvc.token.TokenManager;
import com.huayu.eframe.server.security.service.cache.PermissionCache;
import com.huayu.eframe.server.security.service.constant.SecurityConstant;
import com.huayu.eframe.server.security.service.flow.login.LoginBusiness;
import com.huayu.eframe.server.security.service.flow.login.LoginResponse;
import com.huayu.eframe.server.security.service.request.LoginRequest;
import com.huayu.eframe.server.service.exception.restful.NoRightAuthenticationException;
import com.huayu.eframe.server.service.exception.restful.VersionNotRightException;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.encrypt.Encrypt;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2018/7/28.
 */
@Service
public class EFrameFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource
{
    private static final LogDebug debug = new LogDebug(EFrameFilterInvocationSecurityMetadataSource.class);

    private static final String DEFAULT_VERISON_CONFIG = "sys_eframe_version";

    private static final String DEFAULT_VERISON_CONFIG_VALUE = "v1";

    private static final String DEFAULT_VERISON_HEADER_PARAME = "sys_eframe_version_header_param";

    private static final String DEFAULT_VERISON_HEADER_PARAME_DEFAULT = "EVersion";


    private static final String NEED_VALID_VERSION_HEADER = "sys_eframe_valid_version_header";

    private static final String DO_NOT_NEED_VALID_VERSION_HEADER = "false";

    @Autowired
    private PermissionCache permissionCache;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException
    {
        debug.log("getAttributes start");
        FilterInvocation filterInvocation = (FilterInvocation) object;
        HttpServletRequest request = filterInvocation.getRequest();
        checkVersion(request);
        boolean isWhite = checkWhiteListUrl(filterInvocation.getRequest());
        Object authorization = request.getHeader("Authorization");
        String tokenPackage = StringUtils.getString(authorization);

        // && !StringUtils.equalString("/manage/Login", filterInvocation.getRequestUrl())
        if (!isWhite && StringUtils.isNotNullAndEmpty(tokenPackage)) {

            Token token = getLoginName(tokenPackage, filterInvocation);
            debug.log(token);
            Collection<ConfigAttribute> attr = null;
            if (null != token) {
                attr = getListByLoginName(token);
            }
            if(null != attr)
            {
                return attr;
            }
            if (!isWhite) {
                throw new NoRightAuthenticationException("No right");
            }


        }

        List<ConfigAttribute> anoToken = new ArrayList<>();
        EFrameConfigAttribute eFrameConfigAttribute = new EFrameConfigAttribute();
        eFrameConfigAttribute.setWhite(isWhite);
        anoToken.add(eFrameConfigAttribute);
        return anoToken;
    }


    private void checkVersion(HttpServletRequest request)
    {
        String versionHeaderName = SystemConfig.getValue(DEFAULT_VERISON_HEADER_PARAME, DEFAULT_VERISON_HEADER_PARAME_DEFAULT);
        String versionConfig = SystemConfig.getValue(DEFAULT_VERISON_CONFIG, DEFAULT_VERISON_CONFIG_VALUE);
        String isValid = SystemConfig.getValue(NEED_VALID_VERSION_HEADER,DO_NOT_NEED_VALID_VERSION_HEADER);
        debug.log(versionHeaderName,versionConfig,isValid);
        if(StringUtils.equalStringNoCareUpperAndLower(isValid,DO_NOT_NEED_VALID_VERSION_HEADER))
        {
            return;
        }
        String version = request.getHeader(versionHeaderName);
        if(StringUtils.isNotNullAndEmpty(version) && StringUtils.equalStringNoCareUpperAndLower(versionConfig,version))
        {
            return;
        }
        throw new VersionNotRightException("Error Version");
    }

    private Collection<ConfigAttribute> getListByLoginName(Token token)
    {
        Collection<ConfigAttribute> allAttribute = new ArrayList<>();

        debug.log(token);
        List<AuthView> authList = token.getAllAuthView();
        debug.log(authList);
        if (CollectionUtils.isEmpty(authList)) {
            return null;
        }
        for (AuthView auth : authList) {
            allAttribute.add(new EFrameConfigAttribute(auth));
        }

        if (CollectionUtils.isEmpty(allAttribute)) {
            return null;
        }
        return allAttribute;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes()
    {
        debug.log("getAllConfigAttributes start");
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz)
    {
        return true;
    }

    private Token getLoginName(String tokenPackage, FilterInvocation filterInvocation)
    {
        String[] com = tokenPackage.split(" ");
        if (com.length < 2) {
            return null;
        }
        String flag = com[0];
        String auth = com[1];
        if (StringUtils.equalStringNoCareUpperAndLower(flag, "Bearer")) {
            Token tokenVale = TokenManager.getToken(auth);
            if(null != tokenVale)
            {
                TokenManager.updateToken(tokenVale);
            }
            filterInvocation.getHttpRequest().setAttribute(FlowConstant.HTTP_TOKEN, tokenVale);
            return tokenVale;
        } else if (StringUtils.equalStringNoCareUpperAndLower(flag, "Basic")) {
            EasyParam easyParam = EasyParamCreation.createEasyParam(filterInvocation.getHttpRequest(), filterInvocation.getHttpResponse());
            return getAuthTokenByHeaderStaffNameAndPassword(filterInvocation, auth, easyParam);
        }
        return null;
    }

    private Token getAuthTokenByHeaderStaffNameAndPassword(FilterInvocation filterInvocation, String auth, EasyParam easyParam)
    {
        String authDecode = Encrypt.decodeBase64(auth);
        String[] auths = authDecode.split(":");
        if (auths.length < 2) {
            return null;
        }
        Token token = loginInner(auths[0], auths[1], easyParam);
        return token;

    }

    private Token loginInner(String loginName, String password, EasyParam easyParam)
    {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin(loginName);
        loginRequest.setPassword(password);
        loginRequest.setType(SecurityConstant.LOGIN_TYPE.LOGIN_ONCE);
        Object obj = Flow.execute(LoginBusiness.class, loginRequest, easyParam);


        debug.log(obj);
        if (obj instanceof LoginResponse) {
            Token token = (Token) easyParam.getRequest().getAttribute(FlowConstant.HTTP_TOKEN);
            debug.log(token);
            return token;
        }
        return null;
    }

    private boolean checkWhiteListUrl(HttpServletRequest request)
    {
        return SecurityUtils.checkWhitePermission(permissionCache, request);

    }


}
