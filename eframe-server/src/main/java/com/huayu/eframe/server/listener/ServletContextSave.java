package com.huayu.eframe.server.listener;

import javax.servlet.ServletContextEvent;

public class ServletContextSave
{
	private static ServletContextEvent contextEvent ;
	
	static void setServletContextEvent(ServletContextEvent event)
	{
		contextEvent = event;
	}
	
	public static ServletContextEvent getServletContextEvent()
	{
		return contextEvent;
	}
}
