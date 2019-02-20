package com.huayu.eframe.server.global.restful.current;

import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.mvc.token.Token;
import com.huayu.eframe.server.mvc.token.instance.TokenInstance;
import com.huayu.eframe.server.mvc.token.instance.TokenObjectMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/2/20.
 */
@Service
public class QueryCurrentLoginBusiness extends AbstractExecuteBusiness
{

    private final static String RESULT = "QueryCurrentLoginBusiness_RESULT";
    @Autowired
    private TokenObjectMap tokenObjectMap;
    @Override
    public void execute(BusinessParameter param)
    {
        Token token = LocalAttribute.getToken();
        if(null == token)
        {
            return ;
        }
        String loginCode = token.getPrimaryCode();
        TokenInstance instance = tokenObjectMap.getTokenInstance(token.getPrimaryType());
        Object obj =  instance.getObject(loginCode);
        param.addParameter(RESULT,obj);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
       Object obj =  param.getParameter(RESULT);
       QueryCurrentLoginResponse queryCurrentLoginResponse = new QueryCurrentLoginResponse();
       queryCurrentLoginResponse.setDetail(obj);
       return queryCurrentLoginResponse;
    }
}
