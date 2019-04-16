package com.huayu.eframe.timetask.execute.instance.cleanup.data;

import com.huayu.eframe.flow.presist.atom.LogAtom;
import com.huayu.eframe.flow.presist.bo.LogEntity;
import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.timetask.execute.instance.cleanup.CleanData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/4/15.
 * 日志数据保存三个月
 */
@Service
public class SystemLogCleanup implements CleanData
{

    private final static Integer BATCH_PAGE = Integer.valueOf("20");

    private final static Integer START_PAGE = Integer.valueOf("0");

    private final static Integer DELETION_DURATION = Integer.valueOf("-60");

    @Autowired
    private LogAtom logAtom;

    @Override
    public void remove()
    {

        while(true)
        {
            Page<LogEntity> logEntitiesPage = queryLogByPage();
            logAtom.remove(logEntitiesPage.getContent());
            if(logEntitiesPage.getTotalPages() <= 1)
            {
                break;
            }
        }
    }

    public Page<LogEntity> queryLogByPage()
    {
        FramePaging framePaging = new FramePaging();
        framePaging.setSize(BATCH_PAGE.intValue());
        framePaging.setPage(START_PAGE.intValue());
        Page<LogEntity> result = logAtom.queryWillDeleteLog(framePaging, DateUtils.modifyDays(DateUtils.getCurrentDate(), DELETION_DURATION.intValue()));
        return result;
    }
}
