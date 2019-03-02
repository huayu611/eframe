package com.huayu.eframe.server.tool.util;

import com.huayu.eframe.server.tool.basic.Null;

import java.util.HashMap;
import java.util.Map;


public class MapUtils
{
	public static boolean isEmpty(Map<?, ?> map)
	{
		return Null.isNull(map) || map.isEmpty();
	}

	public static boolean isNotEmpty(Map<?, ?> map)
	{
		return !isEmpty(map);
	}

	public static <T, K> Map<?, ?> createHashMap()
	{
		return new HashMap<T, K>();
	}

	public static boolean isContainsKey(Map<?, ?> map, Object obj)
	{
		return isNotEmpty(map) && map.containsKey(obj);
	}

	public static <T, K> void putAllMap(Map<T, K> sorce, Map<? extends T, ? extends K> dest)
	{
		sorce.putAll(dest);
	}
	
	public static <T, K> K getValue(Map<T, K> map, T key)
	{
		if (isNotEmpty(map) && isContainsKey(map, key))
		{
			return map.get(key);
		}
		return null;
	}

}
