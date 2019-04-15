package com.huayu.eframe.timetask.execute;

import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.ObjectUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.timetask.entity.atom.TimeTaskAtom;
import com.huayu.eframe.timetask.entity.atom.TimeTaskInstanceAtom;
import com.huayu.eframe.timetask.entity.bo.TimeTaskBO;
import com.huayu.eframe.timetask.entity.bo.TimeTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Leo on 2019/4/15.
 */
@Service
public class TimeTaskInstanceExecute
{
    @Autowired
    private TimeTaskInstanceAtom timeTaskInstanceAtom;

    @Autowired
    private TimeTaskAtom timeTaskAtom;

    private TimeTaskTimeManager timeTaskTimeManager;


    public TimeTaskInstance addTimeTaskInstance(TimeTaskBO timeTaskBO)
    {
        TimeTaskInstance timeTaskInstance = new TimeTaskInstance();
        timeTaskInstance.setStartTime(DateUtils.getCurrentDate());
        timeTaskInstance.setRetryTime(0);
        timeTaskInstance.setStatus(TimeTaskStatus.START.getValue());
        timeTaskInstance.setTimeTaskId(timeTaskBO.getId());
        return timeTaskInstanceAtom.addTimeTaskInstance(timeTaskInstance);
    }

    public TimeTaskInstance updateTimeTask(TimeTaskInstance timeTaskInstance,TimeTaskStatus status)
    {
        timeTaskInstance.setStatus(status.getValue());
        return timeTaskInstanceAtom.updateTimeTaskInstance(timeTaskInstance);
    }

    public TimeTaskInstance finishTimeTask(TimeTaskInstance timeTaskInstance)
    {
        timeTaskInstance.setEndTime(DateUtils.getCurrentDate());
        timeTaskInstance.setStatus(TimeTaskStatus.FINISH.getValue());
        return timeTaskInstanceAtom.updateTimeTaskInstance(timeTaskInstance);


    }

    public TimeTaskInstance errorTimeTask(TimeTaskInstance timeTaskInstance,Throwable exception)
    {

        String stackError = ObjectUtils.getTrace(exception);
        timeTaskInstance.setEndTime(DateUtils.getCurrentDate());
        timeTaskInstance.setErrorStack(StringUtils.getStringByLength(stackError, 1024));
        timeTaskInstance.setStatus(TimeTaskStatus.FAILED.getValue());
        return timeTaskInstanceAtom.updateTimeTaskInstance(timeTaskInstance);
    }

}
