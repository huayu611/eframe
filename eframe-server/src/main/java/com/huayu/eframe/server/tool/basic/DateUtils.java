package com.huayu.eframe.server.tool.basic;

import com.huayu.eframe.server.tool.constants.Constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils
{
    private static final Integer STRING_DATE_LENGTH = Integer.valueOf("14");

    private static final Integer SMALL_DATE = Integer.valueOf("1900");

    private static final Integer BIG_DATE = Integer.valueOf("2199");

    private static final Integer SMALL_MONTH = Integer.valueOf("1");

    private static final Integer BIG_MONTH = Integer.valueOf("12");

    private static final List<Integer> BIG_MONTH_DAY = new ArrayList<>();

    private static final List<Integer> SMALL_MONTH_DAY = new ArrayList<>();

    public static Date getCurrentDate()
    {
        return new Date();
    }

    public static Long getNonTime()
    {
        return System.nanoTime();
    }

    public static Long getCurrentTimeMillis()
    {
        return System.currentTimeMillis();
    }

    static
    {
        BIG_MONTH_DAY.add(Integer.valueOf("1"));
        BIG_MONTH_DAY.add(Integer.valueOf("3"));
        BIG_MONTH_DAY.add(Integer.valueOf("5"));
        BIG_MONTH_DAY.add(Integer.valueOf("7"));
        BIG_MONTH_DAY.add(Integer.valueOf("8"));
        BIG_MONTH_DAY.add(Integer.valueOf("10"));
        BIG_MONTH_DAY.add(Integer.valueOf("12"));

        SMALL_MONTH_DAY.add(Integer.valueOf("4"));
        SMALL_MONTH_DAY.add(Integer.valueOf("6"));
        SMALL_MONTH_DAY.add(Integer.valueOf("9"));
        SMALL_MONTH_DAY.add(Integer.valueOf("11"));
    }


    public static Date coverObjectToDate(Object obj)
    {
        return Null.isNotNull(obj) && obj instanceof Date ? (Date) obj : null;
    }

    public static String dateToString(Date date, String format)
    {
        if (Null.isNull(date))
        {
            date = getCurrentDate();
        }
        if (StringUtils.isNullOrEmpty(format))
        {
            format = Constants.DEFAULT_FORMAT_DATE;
        }
        DateFormat formatDate = new SimpleDateFormat(format);
        return formatDate.format(date);
    }

    public static String dateToString(Date date)
    {
        return dateToString(date, null);
    }

    public static Date stringToDate(String sDate)
    {
        return stringToDate(sDate, null);
    }

    public static Date stringToDate(String sDate, String format)
    {
        if (StringUtils.isNullOrEmpty(format))
        {
            format = Constants.DEFAULT_FORMAT_DATE;
        }
        if (StringUtils.isNullOrEmpty(sDate))
        {
            return null;
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        try
        {
            return formatDate.parse(sDate);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    public static Date modify(Date date, int field, int value)
    {
        if (Null.isNull(date))
        {
            date = getCurrentDate();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, value);
        return calendar.getTime();
    }

    public static Date modifyYears(Date date, int value)
    {
        return modify(date, Calendar.YEAR, value);
    }

    public static Date modifyMonths(Date date, int value)
    {
        return modify(date, Calendar.MONTH, value);
    }

    public static Date modifyDays(Date date, int value)
    {
        return modify(date, Calendar.DAY_OF_MONTH, value);
    }

    public static Date modifyHours(Date date, int value)
    {
        return modify(date, Calendar.HOUR_OF_DAY, value);
    }

    public static Date modifyMinutes(Date date, int value)
    {
        return modify(date, Calendar.MINUTE, value);
    }

    public static Date modifySeconds(Date date, int value)
    {
        return modify(date, Calendar.SECOND, value);
    }

    public static Date modifyMilliseconds(Date date, int value)
    {
        return modify(date, Calendar.MILLISECOND, value);
    }

    public static Date modifyWeeks(Date date, int value)
    {
        return modify(date, Calendar.WEEK_OF_MONTH, value);
    }

    public static boolean isBetweenDate(Date before, Date now, Date after)
    {
        if (Null.isNull(before) || Null.isNull(now) || Null.isNull(after))
        {
            return false;
        }
        return afterAndEqualsDate(now, before) && beforeAndEqualsDate(now, after);
    }

    public static boolean afterDate(Date nowDate, Date compareDate)
    {
        if (Null.isNull(nowDate) || Null.isNull(compareDate))
        {
            return false;
        }
        return nowDate.compareTo(compareDate) > 0;
    }

    public static boolean beforeDate(Date nowDate, Date compareDate)
    {
        if (Null.isNull(nowDate) || Null.isNull(compareDate))
        {
            return false;
        }
        return nowDate.compareTo(compareDate) < 0;
    }

    public static boolean equalsDate(Date nowDate, Date compareDate)
    {
        if (Null.isNull(nowDate) || Null.isNull(compareDate))
        {
            return false;
        }
        return nowDate.compareTo(compareDate) == 0;
    }

    //nowdate>=compareDate返回true
    public static boolean afterAndEqualsDate(Date nowDate, Date compareDate)
    {
        if (Null.isNull(nowDate) || Null.isNull(compareDate))
        {
            return false;
        }
        return nowDate.compareTo(compareDate) >= 0;
    }

    //nowdate<=compareDate返回true
    public static boolean beforeAndEqualsDate(Date nowDate, Date compareDate)
    {
        if (Null.isNull(nowDate) || Null.isNull(compareDate))
        {
            return false;
        }
        return nowDate.compareTo(compareDate) <= 0;
    }

    public static boolean isBetweenCurrentDate(Date date1, Date date2)
    {
        return isBetweenDate(date1, getCurrentDate(), date2);
    }

    public static Date getDefaultExpireDate()
    {
        return stringToDate(getDefaultExpireTimeString());
    }

    public static boolean isExpired(Date expireDate, Date currentTime)
    {
        if (expireDate.after(currentTime))
        {
            return false;
        }
        return true;
    }

    public static boolean isExpired(Date expireDate)
    {
        return isExpired(expireDate, getCurrentDate());
    }


    public static boolean checkStringDate(String date)
    {
        if (StringUtils.isNullOrEmpty(date))
        {
            return false;
        }
        if (!STRING_DATE_LENGTH.equals(date.length()))
        {
            return false;
        }
        if (!StringUtils.isNumber(date))
        {
            return false;
        }
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        String hour = date.substring(8, 10);
        String min = date.substring(10, 12);
        String sec = date.substring(12, 14);
        return checkArrayDate(year, month, day, hour, min, sec);
    }

    public static boolean checkArrayDate(String year, String month, String day, String hour, String min, String second)
    {

        //判断年
        Integer yearInt = Integer.valueOf(year);
        if (SMALL_DATE.intValue() > yearInt.intValue() || BIG_DATE.intValue() < yearInt.intValue())
        {
            return false;
        }
        //判断月
        Integer monthInt = Integer.valueOf(month);

        if (BIG_MONTH.intValue() < monthInt.intValue() || SMALL_MONTH.intValue() > monthInt.intValue())
        {
            return false;
        }
        //判断天
        Integer dayInt = Integer.valueOf(day);

        if (BIG_MONTH_DAY.contains(monthInt))
        {
            if (dayInt.intValue() > 31 || dayInt.intValue() < 1)
            {
                return false;
            }
        }
        else if (SMALL_MONTH_DAY.contains(monthInt))
        {
            if (dayInt.intValue() > 30 || dayInt.intValue() < 1)
            {
                return false;
            }
        }
        else
        {
            if (isRun(yearInt))
            {
                if (dayInt.intValue() > 29 || dayInt.intValue() < 1)
                {
                    return false;
                }
            }
            else
            {
                if (dayInt.intValue() > 28 || dayInt.intValue() < 1)
                {
                    return false;
                }
            }
        }

        //判断时
        Integer hourInt = Integer.valueOf(hour);
        if (24 < hourInt.intValue() || 0 > hourInt.intValue())
        {
            return false;
        }
        //判断分
        Integer minInt = Integer.valueOf(min);
        if (60 < minInt.intValue() || 0 > minInt.intValue())
        {
            return false;
        }
        Integer secInt = Integer.valueOf(second);
        if (60 < secInt.intValue() || 0 > secInt.intValue())
        {
            return false;
        }
        return true;
    }

    public static boolean isRun(int year)
    {

        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static String getDefaultExpireTimeString()
    {
        return "20990101000000";
    }


}
