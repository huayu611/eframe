package com.huayu.eframe.timetask.async;

import com.huayu.eframe.server.service.spring.BeanPool;

/**
 * Created by Leo on 2020/10/30.
 */
public class AsynchronousJobUtils
{
    public static void putJob(String bean,Object param)
    {
        AsynchronousInnerJob job = BeanPool.getService(AsynchronousInnerJob.class);
        job.putJob(bean,param);
    }
}
