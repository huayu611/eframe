package com.huayu.eframe.timetask.entity.cache;

import com.huayu.eframe.timetask.entity.bo.TimeTaskBO;

import java.util.List;

/**
 * Created by Leo on 2019/4/15.
 */
public interface TimeTaskCache
{
    String CACHE_NAME = "SYS_TIMETASK";

    List<TimeTaskBO> getAllTimeTask();
}
