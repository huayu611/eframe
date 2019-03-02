package com.huayu.eframe.server.tool.basic;

public class ArrayUtils
{
	public static String[] splitArrayWithoutBlank(String source, String plit)
	{
		if (StringUtils.isNullOrEmpty(source) || StringUtils.isNullOrEmpty(plit))
		{
			return new String[]
			{ source };
		}
		String[] arrayNew = source.split(plit);
		for (int i = 0; i < arrayNew.length; i++)
		{
			String array = arrayNew[i];
			array = array.trim();

		}
		return arrayNew;
	}

	public static String[] removeIndex(String[] obj, int index)
	{
		String[] objResult = new String[obj.length - 1];
		int size = 0;
		for (int k = 0; k < obj.length; k++)
		{
			if (k == index)
			{
				continue;
			}
			objResult[size] = obj[k];
			size++;
		}
		return objResult;
	}

}
