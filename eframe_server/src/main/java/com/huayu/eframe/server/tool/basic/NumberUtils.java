package com.huayu.eframe.server.tool.basic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

public class NumberUtils
{
	public static Integer getIntegerFromObject(Object obj)
	{
		String s = StringUtils.getString(obj);
		if (StringUtils.isNullOrEmpty(s))
		{
			return 0;
		}
		return Integer.valueOf(s);
	}

	public static Long getLongFromObject(Object obj)
	{
		String s = StringUtils.getString(obj);
		if (StringUtils.isNullOrEmpty(s))
		{
			return 0l;
		}
		return Long.valueOf(s);

	}

	public static boolean isInteger(Object obj)
	{
		try
		{
			Integer.valueOf(StringUtils.getString(obj));
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}

	public static boolean isLong(Object obj)
	{
		try
		{
			Long.valueOf(StringUtils.getString(obj));
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}

	public static byte getByteFromObject(Object obj)
	{
		String s = StringUtils.getString(obj);
		return Byte.valueOf(s).byteValue();
	}

	public static Float getFloatFromObject(Object obj)
	{
		String s = StringUtils.getString(obj);
		if (StringUtils.isNullOrEmpty(s))
		{
			return 0.0f;
		}
		return Float.valueOf(StringUtils.getString(obj));
	}

	public static int getNubmerAfterDot(double doubleValue)
	{
		return getNubmerAfterDot(String.valueOf(doubleValue));

	}

	public static int getNubmerAfterDot(float floatValue)
	{
		return getNubmerAfterDot(String.valueOf(floatValue));

	}

	public static int getNubmerAfterDot(String temp)
	{
		int beginIndex = temp.indexOf(".");

		return beginIndex == -1 ? 0 : temp.substring(beginIndex + 1, temp.length()).length();
	}

	public static long getLongValue(float f)
	{
		BigDecimal bigDecimal = new BigDecimal(f);
		return getLongValue(bigDecimal);
	}

	public static long getLongValue(BigDecimal big)
	{
		return big.longValue();
	}

	public static long expandMultipleByTen(long l, int mul)
	{
		return expandMultipleByRate(l, 10, mul);
	}

	public static long expandMultipleByRate(long l, int rate, int mul)
	{
		long result = l;
		for (int i = 1; i <= mul; i++)
		{
			result = result * rate;
		}
		return result;
	}

	public static float multiFloat(float ... floatArray)
	{
		if (floatArray.length == 0)
		{
			return 0f;
		}
		BigDecimal bd = new BigDecimal("1");
		for (float f : floatArray)
		{
			BigDecimal bdFloat = new BigDecimal(String.valueOf(f));
			bd = bd.multiply(bdFloat);
		}
		return bd.floatValue();
	}

	public static long multiLong(Object x1, Object x2)
	{
		BigDecimal bd1 = new BigDecimal(StringUtils.getString(x1));
		BigDecimal bd2 = new BigDecimal(StringUtils.getString(x2));
		BigDecimal bd3 = bd1.multiply(bd2);
		return bd3.longValue();
	}

	public static double divideDouble(Object x1, Object x2 ,int pos)
	{ 
		BigDecimal bd1 = new BigDecimal(StringUtils.getString(x1));
		BigDecimal bd2 = new BigDecimal(StringUtils.getString(x2));
		BigDecimal bd3 = bd1.divide(bd2, new MathContext(50));
		StringBuffer stringBuffer = new StringBuffer("0.");
		for(int i = 1;i<=pos;i++)
		{
			stringBuffer.append("0");
		}
		String lastFormatString = stringBuffer.toString();
		if(lastFormatString.endsWith("."))
		{
			lastFormatString = lastFormatString.substring(0,lastFormatString.length()-1);
		}
		DecimalFormat df = new DecimalFormat(lastFormatString);
		String lastValue = df.format(bd3.doubleValue());
		return Double.valueOf(lastValue).doubleValue();
	}

	public static boolean isMinInteger(Integer value)
	{
		return null==value?null:Integer.MIN_VALUE == value.intValue();
	}
	public static boolean isMaxInteger(Integer value)
	{
		return null==value?null:Integer.MAX_VALUE == value.intValue();
	}

	public static boolean isMinLong(Long value)
	{
		return null==value?null:Long.MIN_VALUE == value.longValue();
	}
	public static boolean isMaxLong(Long value)
	{
		return null==value?null:Long.MAX_VALUE == value.longValue();
	}

}
