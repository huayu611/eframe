package com.huayu.eframe.server.tool.basic;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomUtils
{
    private static char[] ALL_CHAR = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


    private static AtomicInteger number = new AtomicInteger();

    public static int getRandomInteger()
    {
        Random random = new Random();
        random.nextLong();
        return random.nextInt();
    }

    public static long getRandomLong()
    {
        Random random = new Random();
        random.nextLong();
        return random.nextLong();
    }

    public static int getRandomInteger(int i)
    {
        Random random = new Random();
        return random.nextInt(i);
    }

    public static int getRandomRange(int begin, int end)
    {
        if (begin >= end)
        {
            throw new RuntimeException("Random Range Failed! end Number smaller or equals start number");
        }
        else
        {
            int range = end - begin;
            int s = getRandomInteger(range);
            return s + begin;

        }
    }

    public static boolean getRandomPercent(float f)
    {
        if (f > 100)
        {
            return true;
        }
        int length = NumberUtils.getNubmerAfterDot(f);
        if (length > 6)
        {
            throw new RuntimeException("Random Failed! The rate number can't reach 6 number after dot!");
        }
        long prefix = NumberUtils.getLongValue(f);

        long temp = NumberUtils.expandMultipleByTen(prefix, length);
        long target = NumberUtils.expandMultipleByTen(100, length);
        return getRandomInteger(NumberUtils.getIntegerFromObject(target)) > NumberUtils.getIntegerFromObject(temp) ? false
                : true;
    }

    public static String getRandomCharString(int pos)
    {
        if (pos <= 0 || pos > 50)
        {
            return "0";
        }
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < pos; i++)
        {
            int random = getRandomInteger(62);
            result.append(ALL_CHAR[random]);
        }
        return result.toString();
    }

    public static String getUUID()
    {
        return UUID.randomUUID().toString();
    }


    public static String getRandomNumberString(int pos)
    {
        if (pos <= 0 || pos > 50)
        {
            return "0";
        }
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < pos; i++)
        {
            int random = getRandomInteger(10);
            result.append(String.valueOf(random));
        }
        return result.toString();
    }

    public static String getRandomUUID()
    {
        Long now = DateUtils.getCurrentTimeMillis();
        String nowValue = StringUtils.getString(now);

        String randomNum = getRandomCharString(5);

        String createValue = StringUtils.getString(number.getAndIncrement());

        return nowValue + randomNum + createValue;

    }


}
