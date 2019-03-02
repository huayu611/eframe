package com.huayu.eframe.server.listener;

import javax.servlet.ServletContextEvent;

import com.huayu.eframe.server.listener.start.ListenerStartUp;

public class StartIFP
{
	public static void startUp(ServletContextEvent servlet)
	{
		saveServerPath(servlet);

		ListenerStartUp.start();

	}

	public static void close(ServletContextEvent servlet)
	{
		closeServerPath();
	}

	private static void saveServerPath(ServletContextEvent servlet)
	{
		String serverBinPath = servlet.getServletContext().getRealPath("\\");

		System.setProperty("project.path", serverBinPath);
	}

	private static void closeServerPath()
	{

	}

}
