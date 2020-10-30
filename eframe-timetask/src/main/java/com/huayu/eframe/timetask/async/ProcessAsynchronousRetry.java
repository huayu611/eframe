package com.huayu.eframe.timetask.async;

import com.huayu.eframe.server.common.transaction.TransactionAdapter;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.JSonUtils;
import com.huayu.eframe.server.tool.basic.ObjectUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CalculateNumber;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import com.huayu.eframe.timetask.entity.atom.AsynchronousOrderAtom;
import com.huayu.eframe.timetask.entity.bo.AsynchronousOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Leo on 2020/10/30.
 */
@Service
public class ProcessAsynchronousRetry
{
    @Autowired
    private TransactionAdapter transactionAdapter;

    @Scheduled(cron = "0 */2 * * * ?")
    public void runner()
    {
        AsynchronousOrderAtom asynchronousOrderAtom = BeanPool.getService(AsynchronousOrderAtom.class);
        Date now = DateUtils.getCurrentDate();
        AsynchronousOrder asynchronousOrder = new AsynchronousOrder();
        asynchronousOrder.setStatus("1");
        List<AsynchronousOrder> needExecute = asynchronousOrderAtom.queryNeedExecuteOrder(asynchronousOrder, now);
        if(CollectionUtils.isEmpty(needExecute))
        {
            try
            {
                Thread.sleep(3*60*1000);
            }
            catch(Exception e)
            {

            }

        }
        CollectionUtils.iterator(needExecute,(c,v,i)->{
            try{
                AsynchronousProcess process = BeanPool.getService(v.getExecuteBean());
                if(null != process)
                {
                    Object obj = JSonUtils.jsonToObject(v.getParam(),process.getCovertObject());
                    transactionAdapter.executeTransaction(() ->
                    {
                        process.process(obj);
                        v.setNextExecuteTimes(DateUtils.getDefaultExpireDate());
                        v.setLastExecuteTime(DateUtils.getCurrentDate());
                        v.setStatus("2");
                        v.setLastExecuteError("");
                        asynchronousOrderAtom.saveAsynchronousOrder(v);
                    });

                }
            }
            catch(Exception e)
            {
                String stackError = ObjectUtils.getTrace(e);
                Date okNow = DateUtils.getCurrentDate();
                int nextTimes = CalculateNumber.getInstance().add(v.getExecuteTimes()).add(1).getInteger();
                double times = Math.pow(2d, CalculateNumber.getInstance().add(nextTimes).getDouble());
                int nextTimesRetry = CalculateNumber.getInstance().add(times).getInteger();
                Date nextTimesRetryDate = DateUtils.modifyMinutes(okNow,nextTimesRetry);
                v.setNextExecuteTimes(nextTimesRetryDate);
                v.setLastExecuteTime(DateUtils.getCurrentDate());
                v.setStatus("1");
                v.setExecuteTimes(nextTimes);
                v.setLastExecuteError(StringUtils.getStringByLength(stackError, 1000));
                transactionAdapter.executeTransaction(() -> asynchronousOrderAtom.saveAsynchronousOrder(v));
            }
        });
    }
}
