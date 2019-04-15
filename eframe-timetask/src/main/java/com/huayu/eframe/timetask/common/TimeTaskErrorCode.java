package com.huayu.eframe.timetask.common;

/**
 * Created by Leo on 2019/4/15.
 */
public interface TimeTaskErrorCode
{
    String TIME_TASK_NOT_EXIST_WHEN_UPDATING = "16000";

    String TIME_TASK_NOT_EXIST_WHEN_DELETING = "16001";

    String ADD_TIME_TASK_ERROR = "16002";

    String UPDATE_TIME_TASK_ERROR = "16003";

    String DELETING_TIME_TASK_ERROR = "16004";

    String TIME_TASK_TYPE_NEED_CYLE_UNIT = "16005";

    String RUNTING_TIME_TASK_CODE_NOT_EXIST = "16006";
}
