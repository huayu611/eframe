package com.huayu.eframe.timetask.execute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2019/4/15.
 */
public class TimeTaskLock
{
    public static final List<Long> lockedTimeTaskLocker = new ArrayList<>(16);

    public static Object obj = new Object();

    public static boolean putLock(Long timeTaskId)
    {

        synchronized (obj)
        {
            if (lockedTimeTaskLocker.contains(timeTaskId))
            {
                return false;
            }
            lockedTimeTaskLocker.add(timeTaskId);
            return true;
        }
    }

    public static void releaseTimeTask(Long timeTaskId)
    {
        lockedTimeTaskLocker.remove(timeTaskId);
    }

    public static boolean checkLock(Long timeTaskId)
    {
        return lockedTimeTaskLocker.contains(timeTaskId);
    }


}
