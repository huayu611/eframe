package com.huayu.eframe.timetask.async;

/**
 * Created by Leo on 2020/10/30.
 */
public interface AsynchronousProcess
{
    void process(Object obj);

    Class getCovertObject();
}
