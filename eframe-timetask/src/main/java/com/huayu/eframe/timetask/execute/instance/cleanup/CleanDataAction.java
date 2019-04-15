package com.huayu.eframe.timetask.execute.instance.cleanup;

import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Leo on 2019/4/15.
 */
@Service
public class CleanDataAction
{
    private Set<CleanData> cleanDataAction = new HashSet<>();

    public CleanDataAction(Set<CleanData> cleanDataList)
    {
        if(CollectionUtils.isNotEmpty(cleanDataList))
        {
            cleanDataAction.addAll(cleanDataList);
        }
    }

    public Set<CleanData> getCleanDataAction()
    {
        return cleanDataAction;
    }
}
