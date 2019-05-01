package com.huayu.eframe.server.tool.basic;

public class StringUtils
{
    private static final String BLANK = "";

    public static String getString(Object value)
    {
        return Null.isNull(value) ? BLANK : String.valueOf(value);
    }

    public static boolean isBlank(String value)
    {
        return BLANK.equals(getString(value));
    }

    public static boolean isNullOrEmpty(String value)
    {
        return null == value || ("").equals(value);
    }

    public static boolean isNotNullAndEmpty(String value)
    {
        return !isNullOrEmpty(value);
    }

    public static String leaveLastString(String value)
    {
        if (isNullOrEmpty(value))
        {
            return value;
        }
        return value.substring(0, value.length() - 1);
    }

    public static String leaveLastStringForLastChar(String value, String ch)
    {
        if (isNullOrEmpty(value))
        {
            return value;
        }
        return value.endsWith(ch) ? leaveLastString(value) : value;
    }

    public static void leaveLastStringBuffer(StringBuffer value)
    {
        if (Null.isNotNull(value))
        {
            value.delete(value.length() - 1, value.length());
        }
    }

    public static void leaveLastStringBufferForLastChar(StringBuffer value, String ch)
    {
        if (Null.isNotNull(value))
        {
            if (String.valueOf(value).endsWith(ch))
            {
                value.delete(value.length() - 1, value.length());
            }
        }
    }

    public static boolean equalStringNoCareUpperAndLower(String orign, String des)
    {
        if (isNullOrEmpty(orign) || isNullOrEmpty(des))
        {
            return false;
        }
        return orign.equalsIgnoreCase(des);

    }

    public static boolean equalString(String orign, String des)
    {
        if (isNullOrEmpty(orign) || isNullOrEmpty(des))
        {
            return false;
        }
        return orign.equals(des);
    }

    public static boolean isNumber(String value)
    {
        if (isNullOrEmpty(value))
        {
            return false;
        }
        return value.matches("^[0-9]*$");
    }

    public static int getLength(String value)
    {
        return isNullOrEmpty(value) ? 0 : value.length();
    }

    public static String format(String format, Object... obj)
    {
        return format(format, 0, obj);
    }

    public static String format(String format, int cycle, Object... obj)
    {
        String result = "";
        if (format.contains("{") && format.contains("}"))
        {
            int first = format.indexOf("{");
            int last = format.indexOf("}");
            String nowIndex = format.substring(first + 1, last);
            if (!nowIndex.equals(String.valueOf(nowIndex)))
            {
                throw new RuntimeException("Format Failed");
            }
            String repleaseWord = format.substring(first, last + 1);
            result = format.replace(repleaseWord, " " + String.valueOf(obj[cycle]));
            return format(result, cycle + 1, obj);
        }
        else
        {
            if (cycle != obj.length)
            {
                throw new RuntimeException("Format Failed");
            }
        }
        return format;
    }

    public static int countChar(String src, char c)
    {
        char[] charSrc = src.toCharArray();
        int result = 0;
        for (char d : charSrc)
        {
            if (d == c)
            {
                result++;
            }
        }
        return result;
    }

    public static String getTrimString(String value)
    {
        return null == value ? "" : value.trim();
    }

    public static int getIndexStringByNum(String source, String p, int num)
    {
        String program = String.valueOf(source);
        int nowSize = 0;
        for (int i = 1; i <= num; i++)
        {
            int end = program.indexOf(p);
            if (i == num)
            {
                return end + nowSize;
            }
            if (end == -1)
            {
                return -1;
            }
            nowSize = nowSize + end + 1;
            program = program.substring(end + 1);
        }
        return -1;
    }

    public static boolean isExistValue(String... strings)
    {
        for (String v : strings)
        {
            if (isNotNullAndEmpty(v))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isAllNotExist(String... strings)
    {
        return !isExistValue(strings);
    }

    public static boolean isString(Object obj)
    {
        return null == obj ? false : obj.getClass().isAssignableFrom(String.class);
    }

    public static String getStringByLength(String value, int length)
    {
        if (null == value)
        {
            return null;
        }
        if (value.length() <= length)
        {
            return value;
        }
        return value.substring(0, length);
    }

    public static String removeLastString(String value, String remove)
    {
        if (isNotNullAndEmpty(value) && value.endsWith(remove))
        {
            int pos = value.lastIndexOf(remove);
            return value.substring(0, pos);
        }
        return value;
    }

    public static String cutLengthString(String value, int pos)
    {
        if (isNullOrEmpty(value))
        {
            return value;
        }
        int length = getLength(value);
        if (length <= pos)
        {
            return value;
        }
        return value.substring(0, pos);
    }

    public static String cutLastLengthString(String value, int pos)
    {
        if (isNullOrEmpty(value))
        {
            return value;
        }
        int length = getLength(value);
        if (length <= pos)
        {
            return value;
        }

        return value.substring(pos);
    }

    public static String cutPrefix(String value, String prefix)
    {
        if (isNullOrEmpty(value) || isNullOrEmpty(prefix))
        {
            return value;
        }
        if (value.length() < prefix.length())
        {
            return value;
        }
        if (!value.startsWith(prefix))
        {
            return value;
        }
        return cutLengthString(value, prefix.length());
    }


    public static String hiddenPart(String value, int start, int end)
    {
        if (start >= end)
        {
            return value;
        }
        if (isNullOrEmpty(value))
        {
            return value;
        }
        if (value.length() < start)
        {
            return value;
        }
        if (value.length() <= end)
        {
            String hiddenChar = value.substring(start).replaceAll(".+?", "*");
            return cutLengthString(value, start) + hiddenChar;
        }
        String hiddenChar = value.substring(start, end).replaceAll(".+?", "*");
        return cutLengthString(value, start) + hiddenChar + cutLastLengthString(value, end);
    }

    public static String getLikeString(String content)
    {
        if (null != content)
        {
            return "%" + content + "%";
        }
        return null;
    }
}
