package com.huayu.eframe.timetask.entity.cache.impl;

import com.huayu.eframe.server.cache.frame.AbstractFrameCache;
import com.huayu.eframe.server.cache.frame.DefaultIndex;
import com.huayu.eframe.server.common.standard.StandardFacade;
import com.huayu.eframe.timetask.entity.atom.TimeTaskAtom;
import com.huayu.eframe.timetask.entity.bo.TimeTaskBO;
import com.huayu.eframe.timetask.entity.cache.TimeTaskCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2019/4/15.
 */
@Service
public class TimeTaskCacheImpl extends AbstractFrameCache<TimeTaskBO>  implements TimeTaskCache
{
    @Autowired
    private TimeTaskAtom timeTaskAtom;

    @Override
    public List<TimeTaskBO> getAllTimeTask()
    {
        TimeTaskBO timeTaskBO = new TimeTaskBO();
        List<TimeTaskBO> result = getResultByIndex(DefaultIndex.class, timeTaskBO);
        List<TimeTaskBO> resultValid = StandardFacade.getValidItem(result);
        return resultValid;
    }

    @Override
    public List<TimeTaskBO> load()
    {
        return timeTaskAtom.queryAllTimeTask();
    }

    @Override
    public String cacheName()
    {
        return CACHE_NAME;
    }
}
