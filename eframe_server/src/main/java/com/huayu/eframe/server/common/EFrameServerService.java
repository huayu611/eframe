package com.huayu.eframe.server.common;

import com.huayu.eframe.server.cache.CacheProvider;
import com.huayu.eframe.server.service.spring.BeanPool;

public class EFrameServerService
{
	public static CacheProvider getCacheProvider()
	{
		CacheProvider cahceProvider = BeanPool.getService("cacheProvider");
		return cahceProvider;
	}
}
