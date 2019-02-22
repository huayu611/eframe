package com.huayu.eframe.server.tool.basic;

import com.huayu.eframe.server.tool.util.CollectionUtils;
import com.huayu.eframe.server.tool.util.MapUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;



public class PropertiesUtils
{
	public static Map<?, ?> readPropertiesFile(File file)
	{
		Properties properties = new Properties();
		try
		{
			properties.load(StreamUtils.coverFileToStream(file));
		}
		catch (IOException e)
		{
			return null;
		}
		return properties;
	}

	public static Map<?, ?> readPropertiesInputStream(InputStream inputStream)
	{
		Properties properties = new Properties();
		try
		{
			properties.load(inputStream);
		}
		catch (IOException e)
		{
			return null;
		}
		return properties;
	}

	/**
	 * @param files
	 * @return
	 */
	public static  Map<?, ?> readPropertiesFiles(List<File> files)
	{
		if (CollectionUtils.isEmpty(files))
		{
			return null;
		}
		Map result = null;
		for (Iterator<?> iterator = files.iterator(); iterator.hasNext();)
		{
			File file = (File) iterator.next();
			Map<?, ?> mapSingle = readPropertiesFile(file);
			if (MapUtils.isEmpty(result))
			{
				result = mapSingle;
			}
			else
			{
				MapUtils.putAllMap(result, mapSingle);
			}
		}
		return result;
	}
}

