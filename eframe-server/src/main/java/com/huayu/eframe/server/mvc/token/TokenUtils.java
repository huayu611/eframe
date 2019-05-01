package com.huayu.eframe.server.mvc.token;

import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.mvc.token.instance.TokenInstance;
import com.huayu.eframe.server.mvc.token.instance.TokenObjectMap;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.StringUtils;

/**
 * Created by Leo on 2019/2/22.
 */

public class TokenUtils
{

    public static TokenInstance getTokenInstance()
    {
        TokenObjectMap tokenObjectMap = BeanPool.getServiceByClass(TokenObjectMap.class);
        Token token = LocalAttribute.getToken();
        if (null == token)
        {
            return null;
        }
        TokenInstance instance = token.getTokenInstance();
        if (null == instance)
        {
            String type = token.getPrimaryType();
            if (StringUtils.isNotNullAndEmpty(type))
            {
                instance = tokenObjectMap.getTokenInstance(type);
            }
        }
        return instance;
    }

    public static TokenInstance getTokenInstanceByName(String name)
    {
        TokenObjectMap tokenObjectMap = BeanPool.getServiceByClass(TokenObjectMap.class);
        TokenInstance instance = tokenObjectMap.getTokenInstance(name);
        return instance;
    }
}
