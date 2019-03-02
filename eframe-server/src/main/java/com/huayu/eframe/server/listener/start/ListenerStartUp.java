package com.huayu.eframe.server.listener.start;

import com.huayu.eframe.server.service.spring.BeanPool;

public class ListenerStartUp
{
	private ListenerStartUp()
	{

	}

	public static void start()
	{
		StartUpConfiguration config = BeanPool.getService(StartUpConfiguration.class);
		config.paraseResource();
	}

}
