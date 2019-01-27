package com.huayu.eframe.server.tool.basic;

public class Null
{
	public static boolean isNull(Object obj)
	{
		return null == obj;
	}

	public static boolean isNotNull(Object obj)
	{
		return !isNull(obj);
	}

	public static void notNull(Object obj, String value)
	{
		if (isNull(obj))
		{
			if (StringUtils.isNotNullAndEmpty(value))
			{
				throw new RuntimeException(value);
			}
			else
			{
				throw new RuntimeException();
			}

		}
	}

	public static void notNull(Object obj)
	{
		notNull(obj, null);
	}

	public static void needNull(Object obj, String value)
	{
		if (isNotNull(obj))
		{
			if (StringUtils.isNotNullAndEmpty(value))
			{
				throw new RuntimeException(value);
			}
			else
			{
				throw new RuntimeException();
			}

		}
	}

	public static void needNull(Object obj)
	{
		needNull(obj, null);
	}

}
