package com.huayu.eframe.timetask.execute;

import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import com.huayu.eframe.timetask.entity.bo.TimeTaskBO;
import com.huayu.eframe.timetask.entity.cache.TimeTaskCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    //每分钟扫一次。
    @Scheduled(cron="0 */1 * * * ?")
    public void runner()
    {
        List<TimeTaskBO> allTimeTask = timeTaskCache.getAllTimeTask();
        if(null == allTimeTask)
        {
            return;
        }
        CollectionUtils.iterator(allTimeTask,(c,v,i)->{
            call(v);
        });
    }

    private void call(TimeTaskBO timeTaskBO)
    {
        String serviceBeanName = timeTaskBO.getServiceBean();
        Task serviceInstance = taskMap.getTimeTaskServiceByName(serviceBeanName);
        if(null == serviceInstance)
        {
            return;
        }
        if(DateUtils.afterAndEqualsDate(DateUtils.getCurrentDate(),timeTaskBO.getNextTime()))
        {
            boolean locked = TimeTaskLock.putLock(timeTaskBO.getId());
            if(locked)
            {
                try
                {
                    Timer timer = new Timer();
                    timer.schedule(new ExecuteTimeTask(serviceInstance, timeTaskBO), DURATION);
                }
                finally
                {
                    TimeTaskLock.releaseTimeTask(timeTaskBO.getId());
                }
            }

        }

    }
}
