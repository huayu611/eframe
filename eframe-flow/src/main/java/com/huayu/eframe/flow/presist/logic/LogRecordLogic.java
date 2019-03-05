package com.huayu.eframe.flow.presist.logic;

/**
 * Created by Leo on 2019/2/17.
 */
public interface LogRecordLogic
{
    void initLog(Object request);

    void startLog();

    void finishLog(Object response);

    void errorLog(Throwable exception);

}
