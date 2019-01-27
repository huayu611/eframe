package com.huayu.eframe.server.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//import org.springframework.web.util.Log4jWebConfigurer;

//import com.huayu.eframe.server.listener.log4j.Log4j2Manager;
import com.huayu.eframe.server.service.spring.SpringInitForServlet;

public class IFPListener implements ServletContextListener
{

	public void contextDestroyed(ServletContextEvent event)
	{

		// TODO Auto-generated method stub
		// 关闭日志
//		Log4jWebConfigurer.shutdownLogging(event.getServletContext());
//		Log4j2Manager.contextDestroyed(event);
		// 关闭spring
		SpringInitForServlet.stopSpring(event);

		// 关闭ServletContextEvent
		ServletContextSave.setServletContextEvent(null);
	}

	public void contextInitialized(ServletContextEvent event)
	{
		// 设置ServletContextEvent
		ServletContextSave.setServletContextEvent(event);
		
		// TODO Auto-generated method stub
		// 启动日志
//		Log4jWebConfigurer.initLogging(event.getServletContext());
//		Log4j2Manager.contextInitialized(event);
		// Spring加载
		SpringInitForServlet.startSpring(event);
	
		//启动IFP
		StartIFP.startUp(event);
	}

}
