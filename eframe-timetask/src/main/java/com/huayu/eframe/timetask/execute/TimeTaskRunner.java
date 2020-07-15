package com.huayu.eframe.timetask.execute;

import com.huayu.eframe.server.common.transaction.TransactionAdapter;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import com.huayu.eframe.timetask.entity.atom.TimeTaskAtom;
import com.huayu.eframe.timetask.entity.bo.TimeTaskBO;
import com.huayu.eframe.timetask.entity.cache.TimeTaskCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * Created by Leo on 2019/4/15.
 */
@Component
public class TimeTaskRunner
{
    //定时任务启动后一秒后执行
    private final static Integer DURATION = Integer.valueOf("1000");

    @Autowired
    private TaskMap taskMap;

    @Autowired
    private TimeTaskCache timeTaskCache;

    @Autowired
    private TimeTaskAtom timeTaskAtom;

    @Autowired
    private TransactionAdapter transactionAdapter;

    @Autowired
    private TimeTaskTimeManager timeTaskTimeManager;

    //每分钟扫一次。
    @Scheduled(cron = "0 */1 * * * ?")
    public void runner()
    {
        List<TimeTaskBO> allTimeTask = timeTaskCache.getAllTimeTask();
        if (null == allTimeTask)
        {
            return;
        }
        CollectionUtils.iterator(allTimeTask, (c, v, i) ->
        {
            TimeTaskBO timeTaskReal = timeTaskAtom.queryTimeTaskById(v.getId());
            if(null != timeTaskReal)
            {
                call(timeTaskReal);
            }

        });
    }

    private void call(TimeTaskBO timeTaskBO)
    {
        String serviceBeanName = timeTaskBO.getServiceBean();
        Task serviceInstance = taskMap.getTimeTaskServiceByName(serviceBeanName);
        if (null == serviceInstance)
        {
            return;
        }
        if (null == timeTaskBO.getNextTime() || DateUtils.afterAndEqualsDate(DateUtils.getCurrentDate(), timeTaskBO.getNextTime()))
        {
            boolean locked = TimeTaskLock.putLock(timeTaskBO.getId());
            if (locked)
            {
                try
                {
                    Timer timer = new Timer();
                    timer.schedule(new ExecuteTimeTask(serviceInstance, timeTaskBO), DURATION);
                }
                finally
                {
                    transactionAdapter.executeTransaction(()->{
                        Date nextTime = timeTaskTimeManager.nextTime(timeTaskBO);
                        timeTaskBO.setNextTime(nextTime);
                        timeTaskAtom.updateTimeTask(timeTaskBO);
                    });
                    TimeTaskLock.releaseTimeTask(timeTaskBO.getId());
                }
            }

        }

    }
}
