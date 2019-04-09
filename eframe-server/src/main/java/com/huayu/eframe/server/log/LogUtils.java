package com.huayu.eframe.server.log;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class LogUtils
{
    private static boolean debugTurn = false;

    private static ReentrantLock lock = new ReentrantLock();

    public static boolean isDebugOpen()
    {
        return debugTurn;
    }

    public static void setDebugTurn(boolean flag)
    {
        lock.lock();
        debugTurn = flag;
        lock.unlock();
    }

}
