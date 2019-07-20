package com.huayu.eframe.timetask.execute;

import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.timetask.common.Constants;
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
        if (StringUtils.equalStringNoCareUpperAndLower(Constants.TimeTaskType.ONCE, timeTaskBO.getTimeTaskType()))
        {
            return null == timeTaskBO.getNextTime() ? timeTaskBO.getEffectiveTime() : DateUtils.getDefaultExpireDate();
        }
        String unit = timeTaskBO.getUnit();
        if (StringUtils.isNullOrEmpty(unit))
        {
            //对于数据错误的，直接为2099年。数据修复后，由管理员修改数据。
            return DateUtils.getDefaultExpireDate();
        }
        //如果第一次执行。执行时间就为生效时间。
        if (null == timeTaskBO.getNextTime())
        {
            return timeTaskBO.getEffectiveTime();
        }

        //不用switch case,switch case是对大写写强匹配。这里不需要强匹配。
        // 并且当前只支持固定时间,比如生效时间为12：00：00，每天执行一次，那么就是每天12：00：00执行。这里面的问题是，如果本次没有执行完，下一次已经启动。会延迟执行。
        Date currentTime = timeTaskBO.getNextTime();
        //@2019 07-19添加INTERVAL类型，下一次的执行时间为本次执行结束的间隔
        if (StringUtils.equalStringNoCareUpperAndLower(Constants.TimeTaskType.INTERVAL, timeTaskBO.getTimeTaskType()))
        {
            currentTime = LocalAttribute.getNow();
        }

        if (StringUtils.equalStringNoCareUpperAndLower(Constants.Unit.YEAR, unit))
        {
            return DateUtils.modifyYears(currentTime, timeTaskBO.getCycle());
        }
        if (StringUtils.equalStringNoCareUpperAndLower(Constants.Unit.MONTH, unit))
        {
            return DateUtils.modifyMonths(currentTime, timeTaskBO.getCycle());
        }
        if (StringUtils.equalStringNoCareUpperAndLower(Constants.Unit.DAY, unit))
        {
            return DateUtils.modifyDays(currentTime, timeTaskBO.getCycle());
        }
        if (StringUtils.equalStringNoCareUpperAndLower(Constants.Unit.WEEK, unit))
        {
            return DateUtils.modifyWeeks(currentTime, timeTaskBO.getCycle());
        }
        if (StringUtils.equalStringNoCareUpperAndLower(Constants.Unit.HOUR, unit))
        {
            return DateUtils.modifyHours(currentTime, timeTaskBO.getCycle());
        }
        if (StringUtils.equalStringNoCareUpperAndLower(Constants.Unit.MINUTE, unit))
        {
            return DateUtils.modifyMinutes(currentTime, timeTaskBO.getCycle());
        }
        if (StringUtils.equalStringNoCareUpperAndLower(Constants.Unit.SECOND, unit))
        {
            return DateUtils.modifySeconds(currentTime, timeTaskBO.getCycle());
        }
        //如果都没有匹配上，则直接2099年。不再执行。
        return DateUtils.getDefaultExpireDate();
    }


}
