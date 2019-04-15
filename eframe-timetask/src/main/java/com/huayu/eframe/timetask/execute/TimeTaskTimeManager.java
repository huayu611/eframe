package com.huayu.eframe.timetask.execute;

import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.timetask.entity.bo.TimeTaskBO;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Leo on 2019/4/15.
 */
@Service
public class TimeTaskTimeManager
{

    public Date nextTime(TimeTaskBO timeTaskBO)
    {
        if (StringUtils.equalStringNoCareUpperAndLower("once", timeTaskBO.getTimeTaskType()))
        {
            return null == timeTaskBO.getNextTime() ? timeTaskBO.getEffectiveTime() : DateUtils.getDefaultExpireDate();
        }
        String unit = timeTaskBO.getUnit();
        if(StringUtils.isNullOrEmpty(unit))
        {
            //对于数据错误的，直接为2099年。数据修复后，由管理员修改数据。
            return  DateUtils.getDefaultExpireDate();
        }
        //如果第一次执行。执行时间就为生效时间。
        if(null == timeTaskBO.getNextTime())
        {
            return timeTaskBO.getEffectiveTime();
        }

        //不用switch case,switch case是对大写写强匹配。这里不需要强匹配。并且当前只支持固定时间
        if(StringUtils.equalStringNoCareUpperAndLower("year", unit))
        {
            Date currentTime  =  timeTaskBO.getNextTime();
            return DateUtils.modifyYears(currentTime,timeTaskBO.getCycle());
        }
        if(StringUtils.equalStringNoCareUpperAndLower("month", unit))
        {
            Date currentTime  =  timeTaskBO.getNextTime();
            return DateUtils.modifyMonths(currentTime,timeTaskBO.getCycle());
        }
        if(StringUtils.equalStringNoCareUpperAndLower("day", unit))
        {
            Date currentTime  =  timeTaskBO.getNextTime();
            return DateUtils.modifyDays(currentTime,timeTaskBO.getCycle());
        }
        if(StringUtils.equalStringNoCareUpperAndLower("week", unit))
        {
            Date currentTime  =  timeTaskBO.getNextTime();
            return DateUtils.modifyWeeks(currentTime,timeTaskBO.getCycle());
        }
        if(StringUtils.equalStringNoCareUpperAndLower("hour", unit))
        {
            Date currentTime  =  timeTaskBO.getNextTime();
            return DateUtils.modifyHours(currentTime,timeTaskBO.getCycle());
        }
        if(StringUtils.equalStringNoCareUpperAndLower("minute", unit))
        {
            Date currentTime  =  timeTaskBO.getNextTime();
            return DateUtils.modifyMinutes(currentTime,timeTaskBO.getCycle());
        }
        if(StringUtils.equalStringNoCareUpperAndLower("second", unit))
        {
            Date currentTime  =  timeTaskBO.getNextTime();
            return DateUtils.modifySeconds(currentTime,timeTaskBO.getCycle());
        }
        //如果都没有匹配上，则直接2099年。不再执行。
        return DateUtils.getDefaultExpireDate();
    }


}
