package com.huayu.eframe.flow;

import com.huayu.eframe.server.log.LogDebug;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Created by Leo on 2018/9/30.
 */
@Service
public class TransactionFlow
{

    private static final LogDebug debug = new LogDebug(TransactionFlow.class);


    @Transactional(rollbackFor = Exception.class,propagation= Propagation.REQUIRES_NEW)
    public void executeTransaction(ExecuteBusiness business,BusinessParameter param)
    {
        debug.log("Start transaction");
        Assert.notNull(business,"Service can't be null");
        business.execute(param);
        debug.log("End transaction");
    }
}
