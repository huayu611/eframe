package com.huayu.eframe.server.service.spring;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.util.WebUtils;

public class SpringInitForServlet
{
	public static void startSpring(ServletContextEvent event)
	{
		BeanInpouring.setApplicationContext(getContextLoader().initWebApplicationContext(event.getServletContext()));
		// 加载WEBROOT
		WebUtils.setWebAppRootSystemProperty(event.getServletContext());
	}

	public static void stopSpring(ServletContextEvent event)
	{
		getContextLoader().closeWebApplicationContext(event.getServletContext());
		cleanupAttributes(event.getServletContext());

		WebUtils.removeWebAppRootSystemProperty(event.getServletContext());
	}

	private static ContextLoader getContextLoader()
	{
		return new ContextLoader();
	}

	private static void cleanupAttributes(ServletContext sc)
	{
		Enumeration attrNames = sc.getAttributeNames();
		while (attrNames.hasMoreElements())
		{
			String attrName = (String) attrNames.nextElement();
			if (attrName.startsWith("org.springframework."))
			{
				Object attrValue = sc.getAttribute(attrName);
				if (attrValue instanceof DisposableBean)
				{
					try
					{
						((DisposableBean) attrValue).destroy();
					}
					catch (Throwable ex)
					{
					}
				}
			}
		}
	}
}
