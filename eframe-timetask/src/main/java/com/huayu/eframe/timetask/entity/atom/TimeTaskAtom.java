package com.huayu.eframe.timetask.entity.atom;

import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.timetask.entity.bo.TimeTaskBO;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by Leo on 2019/4/15.
 */
public interface TimeTaskAtom
{
    TimeTaskBO addTimeTask(TimeTaskBO timeTaskBO);

    TimeTaskBO updateTimeTask(TimeTaskBO timeTaskBO);

    TimeTaskBO queryTimeTaskByCode(String code);

    Page<TimeTaskBO> queryTimeTaskByPage(TimeTaskBO timeTaskBO, FramePaging fp);

    List<TimeTaskBO> queryAllTimeTask();

    void updateTimeTaskAddExecuteTime(Long id,Date nextDate);

}
