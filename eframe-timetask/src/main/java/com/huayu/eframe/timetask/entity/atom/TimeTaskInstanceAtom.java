package com.huayu.eframe.timetask.entity.atom;

import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.timetask.entity.bo.TimeTaskInstance;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by Leo on 2019/4/15.
 */
public interface TimeTaskInstanceAtom
{
    TimeTaskInstance addTimeTaskInstance(TimeTaskInstance timeTaskInstance);

    TimeTaskInstance updateTimeTaskInstance(TimeTaskInstance timeTaskInstance);

    Page<TimeTaskInstance> queryTimeTaskInstanceByPage(TimeTaskInstance timeTaskInstance, FramePaging fp);

    Page<TimeTaskInstance> queryWillDeleteTaskInstanceByPage(FramePaging fp, Date deletionTime);

    void batchDeleteTaskInstance(List<TimeTaskInstance> timeTaskInstances);
}
