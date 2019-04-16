package com.huayu.eframe.timetask.execute;

import com.huayu.eframe.flow.Flow;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.timetask.common.TimeTaskEasyParam;
import com.huayu.eframe.timetask.entity.bo.TimeTaskBO;
import com.huayu.eframe.timetask.entity.bo.TimeTaskInstance;
import com.huayu.eframe.timetask.flow.ModifyTimeTaskExecuteCommandBusiness;
import com.huayu.eframe.timetask.flow.ModifyTimeTaskExecuteCommandRequest;

import java.util.Date;
import java.util.TimerTask;

/**
 * Created by Leo on 2019/4/15.
 */
public class ExecuteTimeTask extends TimerTask
{

    private final static LogDebug DEBUG = new LogDebug(ExecuteTimeTask.class);

    private Task task;

    private TimeTaskBO timeTaskBO;

    private TimeTaskInstanceExecute timeTaskInstanceExecute;

    private TimeTaskInstance timeTaskInstance;

    ExecuteTimeTask(Task task, TimeTaskBO timeTaskBO)
    {
        this.task = task;
        this.timeTaskBO = timeTaskBO;
    }

    @Override
    public void run()
    {
        //对于init失败，则不记录任何状态，直接放锁，等重试

        init();


        try
        {
            timeTaskInstance = timeTaskInstanceExecute.updateTimeTask(timeTaskInstance, TimeTaskStatus.PROCESS);
            //返回 result。预留，后面扩展部分成功失败这样的类型
            Integer result = task.execute();
            timeTaskInstanceExecute.finishTimeTask(timeTaskInstance);

        }
        catch (Exception e)
        {
            timeTaskInstanceExecute.errorTimeTask(timeTaskInstance, e);
            return;
        }
        finally
        {
            //不论成功失败都要发送消息修改状态。
            sendTimeTaskFlowEvent();

        }
    }


    private void init()
    {
        timeTaskInstanceExecute = timeTaskInstanceExecute();
        timeTaskInstance = timeTaskInstanceExecute.addTimeTaskInstance(timeTaskBO);
    }


    private TimeTaskInstanceExecute timeTaskInstanceExecute()
    {
        return BeanPool.getService(TimeTaskInstanceExecute.class);
    }

    private Date getNextTime(TimeTaskBO timeTaskBO)
    {
        TimeTaskTimeManager timeTaskTimeManager = BeanPool.getService(TimeTaskTimeManager.class);
        Date date = timeTaskTimeManager.nextTime(timeTaskBO);
        return date;
    }

    private void sendTimeTaskFlowEvent()
    {
        ModifyTimeTaskExecuteCommandRequest modifyTimeTaskExecuteCommandRequest = new ModifyTimeTaskExecuteCommandRequest();
        modifyTimeTaskExecuteCommandRequest.setTimeTaskCode(timeTaskBO.getCode());
        modifyTimeTaskExecuteCommandRequest.setNextTime(getNextTime(timeTaskBO));

        //这一步会修改定时 任务状态，所以放在锁内执行。以免串锁
        Object obj = Flow.execute(ModifyTimeTaskExecuteCommandBusiness.class, modifyTimeTaskExecuteCommandRequest, TimeTaskEasyParam.buildEasyParam());

        //不论成功和失败都要放锁。
        DEBUG.log(obj);
    }
}
