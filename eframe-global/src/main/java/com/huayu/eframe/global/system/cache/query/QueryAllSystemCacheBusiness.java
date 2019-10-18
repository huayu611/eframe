package com.huayu.eframe.global.system.cache.query;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.cache.frame.AbstractFrameType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/10/18.
 */
@Service
public class QueryAllSystemCacheBusiness extends AbstractExecuteBusiness
{

    @Autowired
    private AbstractFrameType abstractFrameType;

    @Override
    public void execute(BusinessParameter param)
    {

    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        QueryAllSystemCacheRequest queryAllSystemCacheRequest = param.getRequest();
        return abstractFrameType.getAllCache();

    }
}
