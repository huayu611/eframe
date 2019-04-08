package com.huayu.eframe.server.mvc.token.instance;

import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Leo on 2019/2/14.
 */
@Service
public class TokenObjectMap
{
    private Map<String,TokenInstance> storeInstanceList = new HashMap<>();

    public TokenObjectMap(@Nullable List<TokenInstance> tokenInstanceList)
    {
        if(CollectionUtils.isNotEmpty(tokenInstanceList))
        {
            for(TokenInstance tokenInstance:tokenInstanceList)
            {
                storeInstanceList.put(tokenInstance.getType(),tokenInstance);
            }
        }
    }

    public Map<String,TokenInstance> getAllTokenList()
    {
        return storeInstanceList;
    }

    public TokenInstance getTokenInstance(String type)
    {
        return storeInstanceList.get(type);
    }
}
