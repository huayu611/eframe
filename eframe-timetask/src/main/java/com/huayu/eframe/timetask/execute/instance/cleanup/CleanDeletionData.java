package com.huayu.eframe.timetask.execute.instance.cleanup;

import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import com.huayu.eframe.timetask.execute.TaskAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 清理已经删除的数据接口。
 * Created by Leo on 2019/4/15.
 */
@Service
public class CleanDeletionData extends TaskAdapter
{
    private static final LogDebug debug = new LogDebug(CleanDeletionData.class);

    @Autowired
    private CleanDataAction cleanDataAction;

    @Override
    public Integer execute()
    {
        executeAllCleanAction();
        return 0;
    }

    private void executeAllCleanAction()
    {
        Set<CleanData> allAction = cleanDataAction.getCleanDataAction();
        if (CollectionUtils.isEmpty(allAction))
        {
            return;
        }
        CollectionUtils.iterator(allAction, (c, v, i) ->
        {
            try
            {
                v.remove();
            }
            catch (Exception e)
            {
                String errorInfo = v.getClass().getName() + ": execute error ,please check and we do not stop.";
                debug.errorLog(errorInfo, e);

            }
        });
    }

    @Override
    public String getName()
    {
        return "Clean Up Deletion Data";
    }
}
