package com.huayu.eframe.timetask.flow;

import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.timetask.common.TimeTaskErrorCode;
import com.huayu.eframe.timetask.entity.service.TimeTaskDetail;

/**
 * Created by Leo on 2019/4/15.
 */
public class TimeTaskRequestValid
{

    public static void checkTimeTaskCycleData(TimeTaskDetail timeTaskBO)
    {
        if(null == timeTaskBO)
        {
            return ;
        }
        if(StringUtils.equalStringNoCareUpperAndLower(timeTaskBO.getTimeTaskType(),"cycle"))
        {
            if(StringUtils.isNullOrEmpty(timeTaskBO.getUnit())|| null == timeTaskBO.getCycle() || timeTaskBO.getCycle().intValue()<1)
            {
                throw new IFPException(TimeTaskErrorCode.TIME_TASK_TYPE_NEED_CYLE_UNIT,"time task is cycle and need cycle and unit");
            }
        }
    }
}
