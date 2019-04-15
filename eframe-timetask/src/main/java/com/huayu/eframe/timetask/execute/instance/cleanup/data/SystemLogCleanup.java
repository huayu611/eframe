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
        framePaging.setSize(20);
        framePaging.setPage(0);
        Page<LogEntity> result = logAtom.queryWillDeleteLog(framePaging, DateUtils.modifyDays(DateUtils.getCurrentDate(), -40));
        return result;
    }
}
