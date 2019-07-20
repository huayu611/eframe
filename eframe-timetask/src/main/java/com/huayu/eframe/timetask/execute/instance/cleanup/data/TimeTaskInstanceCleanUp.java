package com.huayu.eframe.timetask.execute.instance.cleanup.data;

import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.timetask.entity.atom.TimeTaskInstanceAtom;
import com.huayu.eframe.timetask.entity.bo.TimeTaskInstance;
import com.huayu.eframe.timetask.execute.instance.cleanup.CleanData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/7/19.
 */
@Service
public class TimeTaskInstanceCleanUp  implements CleanData
{

    private final static Integer BATCH_PAGE = Integer.valueOf("20");

    private final static Integer START_PAGE = Integer.valueOf("0");

    private final static Integer DELETION_DURATION = Integer.valueOf("-60");

    @Autowired
    private TimeTaskInstanceAtom timeTaskInstanceAtom;

    @Override
    public void remove()
    {
        while (true)
        {
            Page<TimeTaskInstance> timeTaskInstances = queryNeedDeleteTaskInstance();
            timeTaskInstanceAtom.batchDeleteTaskInstance(timeTaskInstances.getContent());
            if (timeTaskInstances.getTotalPages() <= 1)
            {
                break;
            }
        }
    }

    private Page<TimeTaskInstance> queryNeedDeleteTaskInstance()
    {
        FramePaging framePaging = new FramePaging();
        framePaging.setSize(BATCH_PAGE.intValue());
        framePaging.setPage(START_PAGE.intValue());
        Page<TimeTaskInstance> result = timeTaskInstanceAtom.queryWillDeleteTaskInstanceByPage(framePaging, DateUtils.modifyDays(DateUtils.getCurrentDate(), DELETION_DURATION.intValue()));
        return result;
    }
}
