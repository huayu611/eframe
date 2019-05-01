package com.huayu.eframe.global.system.token;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.mvc.token.instance.TokenInstance;
import com.huayu.eframe.server.mvc.token.instance.TokenObjectMap;
import com.huayu.eframe.server.tool.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Leo on 2019/4/8.
 */
@Service
public class QuerySystemTokensBusiness extends AbstractExecuteBusiness
{

    @Autowired
    private TokenObjectMap tokenObjectMap;

    private final static String RESULT = "QuerySystemTokens_RESULT";


    @Override
    public void execute(BusinessParameter param)
    {

    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        List<Tokens> keys = new ArrayList<>();
        Map<String, TokenInstance> allToken = tokenObjectMap.getAllTokenList();
        MapUtils.iterator(allToken, (m, k, v) ->
        {
            Tokens tokens = new Tokens();
            tokens.setKey(k);
            tokens.setName(k);
            keys.add(tokens);
        });
        return keys;
    }
}
