package com.huayu.eframe.server.tool.basic;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.Date;

public class ObjectUtils
{
    public static Object findObjectByPropertiesAndObject(Object obj, String param)
    {
        Object objResult = null;
        try
        {
            Class<? extends Object> clazz = obj.getClass();
            Field[] filed = clazz.getDeclaredFields();
            for (int i = 0; i < filed.length; i++)
            {
                Field filedSingle = filed[i];
                filedSingle.setAccessible(true);
                if (StringUtils.equalStringNoCareUpperAndLower(filedSingle.getName(), param))
                {
                    objResult = filedSingle.get(obj);
                }
                filedSingle.setAccessible(false);
            }
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }
        catch (IllegalAccessException e)
        {
            return null;
        }

        return objResult;
    }

    public static boolean isSimpleObject(Object obj)
    {
        if (null == obj)
        {
            return false;
        }
        return obj.getClass().isAssignableFrom(Integer.class) || obj.getClass().isAssignableFrom(Byte.class)
                || obj.getClass().isAssignableFrom(Float.class) || obj.getClass().isAssignableFrom(Boolean.class)
                || obj.getClass().isAssignableFrom(Double.class) || obj.getClass().isAssignableFrom(Short.class)
                || obj.getClass().isAssignableFrom(Character.class) || obj.getClass().isAssignableFrom(Long.class);
    }

    public static boolean isSimpleObject2(Object obj)
    {
        if (null == obj)
        {
            return false;
        }
        return isSimpleObject(obj) || obj.getClass().isAssignableFrom(Date.class)
                || obj.getClass().isAssignableFrom(String.class);
    }

    public static boolean isNull(Object obj)
    {
        return null == obj;
    }

    public static boolean isNotNull(Object obj)
    {
        return !isNull(obj);
    }

    public static String getTrace(Throwable t)
    {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }

}
