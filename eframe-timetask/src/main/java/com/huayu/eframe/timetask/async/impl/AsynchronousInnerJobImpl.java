package com.huayu.eframe.timetask.async.impl;

import com.huayu.eframe.server.common.transaction.TransactionAdapter;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.*;
import com.huayu.eframe.timetask.async.AsynchronousInnerJob;
import com.huayu.eframe.timetask.async.AsynchronousProcess;
import com.huayu.eframe.timetask.entity.atom.AsynchronousOrderAtom;
import com.huayu.eframe.timetask.entity.bo.AsynchronousOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Leo on 2020/10/30.
 */
@Service
public class AsynchronousInnerJobImpl implements AsynchronousInnerJob
{
    @Autowired
    private AsynchronousOrderAtom asynchronousOrderAtom;

    @Autowired
    private TransactionAdapter transactionAdapter;
    @Override
    @Async
    public void putJob(String bean,Object param)
    {
        AsynchronousProcess process =  BeanPool.getService(bean);
        if(null == process)
        {
            return;
        }
        try
        {
            transactionAdapter.executeTransaction(() -> process.process(param));

        }
        catch(Exception e)
        {
            String stackError = ObjectUtils.getTrace(e);
            AsynchronousOrder asynchronousOrder = new AsynchronousOrder();
            Date now = DateUtils.getCurrentDate();
            Date next = DateUtils.modifyMinutes(now,2);
            asynchronousOrder.setCode("ASYNC_" + RandomUtils.getRandomUUID());
            asynchronousOrder.setCreateTime(now);
            asynchronousOrder.setExecuteBean(bean);
            asynchronousOrder.setLastExecuteError(StringUtils.getStringByLength(stackError, 1000));
            asynchronousOrder.setExecuteTimes(1);
            asynchronousOrder.setNextExecuteTimes(next);
            asynchronousOrder.setStatus("1");
            asynchronousOrder.setParam(JSonUtils.coverToJson(param));
            asynchronousOrder.setLastExecuteTime(now);
            transactionAdapter.executeTransaction(() ->asynchronousOrderAtom.saveAsynchronousOrder(asynchronousOrder));

        }


    }
}
