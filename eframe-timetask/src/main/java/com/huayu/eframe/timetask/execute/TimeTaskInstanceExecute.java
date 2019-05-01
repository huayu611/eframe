package com.huayu.eframe.timetask.execute;

import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.ObjectUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.timetask.entity.atom.TimeTaskInstanceAtom;
import com.huayu.eframe.timetask.entity.bo.TimeTaskBO;
import com.huayu.eframe.timetask.entity.bo.TimeTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/4/15.
 */
@Service
public class TimeTaskInstanceExecute
{

    private final static Integer MAX_LENGTH = Integer.valueOf("2047");

    @Autowired
    private TimeTaskInstanceAtom timeTaskInstanceAtom;


    public TimeTaskInstance addTimeTaskInstance(TimeTaskBO timeTaskBO)
    {
        TimeTaskInstance timeTaskInstance = new TimeTaskInstance();
        timeTaskInstance.setStartTime(DateUtils.getCurrentDate());
        timeTaskInstance.setRetryTime(0);
        timeTaskInstance.setStatus(TimeTaskStatus.START.getValue());
        timeTaskInstance.setTimeTaskId(timeTaskBO.getId());
        return timeTaskInstanceAtom.addTimeTaskInstance(timeTaskInstance);
    }

    public TimeTaskInstance updateTimeTask(TimeTaskInstance timeTaskInstance, TimeTaskStatus status)
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

    public TimeTaskInstance errorTimeTask(TimeTaskInstance timeTaskInstance, Throwable exception)
    {
        String stackError = ObjectUtils.getTrace(exception);
        timeTaskInstance.setEndTime(DateUtils.getCurrentDate());
        timeTaskInstance.setErrorStack(StringUtils.getStringByLength(stackError, MAX_LENGTH));
        timeTaskInstance.setStatus(TimeTaskStatus.FAILED.getValue());
        return timeTaskInstanceAtom.updateTimeTaskInstance(timeTaskInstance);
    }

}
