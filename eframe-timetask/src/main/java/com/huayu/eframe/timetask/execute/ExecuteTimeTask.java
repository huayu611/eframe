package com.huayu.eframe.timetask.execute;

import com.huayu.eframe.flow.Flow;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.timetask.common.TimeTaskEasyParam;
import com.huayu.eframe.timetask.entity.atom.TimeTaskAtom;
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
    private Task task;

    private TimeTaskBO timeTaskBO;

    private TimeTaskInstanceExecute timeTaskInstanceExecute;

    private TimeTaskInstance timeTaskInstance;

    ExecuteTimeTask(Task task,TimeTaskBO timeTaskBO)
    {
        this.task = task;
        this.timeTaskBO = timeTaskBO;
    }

    @Override
    public void run()
    {
        init();
        Date nextDate = getNextTime(timeTaskBO);
        try
        {
            timeTaskInstance = timeTaskInstanceExecute.updateTimeTask(timeTaskInstance,TimeTaskStatus.PROCESS);
            Integer result = task.execute();
            timeTaskInstanceExecute.finishTimeTask(timeTaskInstance);

        }
        catch(Exception e)
        {
            timeTaskInstanceExecute.errorTimeTask(timeTaskInstance,e);
            return;
        }
        finally
        {
            sendTimeTaskFlowEvent();
            TimeTaskLock.releaseTimeTask(timeTaskBO.getId());
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
        Object obj = Flow.execute(ModifyTimeTaskExecuteCommandBusiness.class, modifyTimeTaskExecuteCommandRequest, TimeTaskEasyParam.buildEasyParam());
    }
}
