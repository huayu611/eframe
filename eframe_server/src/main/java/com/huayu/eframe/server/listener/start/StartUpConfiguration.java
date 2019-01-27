package com.huayu.eframe.server.listener.start;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.huayu.eframe.server.tool.basic.NumberUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.basic.XmlUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.core.io.Resource;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.huayu.eframe.server.log.Debug;


public class StartUpConfiguration
{
	private static final Debug debug = new Debug(StartUpConfiguration.class);

	private Resource[] resource;

	public Resource[] getResource()
	{
		return resource;
	}

	public void setResource(Resource[] resource)
	{
		this.resource = resource;
	}

	public void paraseResource()
	{
		debug.debug("Begin startup");

		List<StartConfig> startConfigList = readConfig();

		sort(startConfigList);

		invoke(startConfigList);

		debug.debug("End startup");

	}

	private void sort(List<StartConfig> startConfigList)
	{
		if (CollectionUtils.isEmpty(startConfigList))
		{
			return;
		}
		Collections.sort(startConfigList, new Comparator<StartConfig>()
		{
			public int compare(StartConfig start1, StartConfig start2)
			{
				return start1.getOrder() - start2.getOrder();
			}

		});
	}

	private List<StartConfig> readConfig()
	{
		List<StartConfig> startUpConfig = new ArrayList<StartConfig>();
		System.out.println(resource.length);
		if (resource.length == 0)
		{
			return startUpConfig;
		}

		for (Resource res : resource)
		{
			try
			{
				NodeList nodeList = XmlUtils.paraseXMLForNodeListByTagNameAndInputStream(res.getInputStream(),
						"startup");
				for (int i = 0; i < nodeList.getLength(); i++)
				{
					Node node = nodeList.item(i);
					String context = XmlUtils.paraseXMLForTextContentByNodeAndTagName(node, "startup").trim();
					NamedNodeMap nodeAttr = node.getAttributes();
					Node nodeAttrIdValue = nodeAttr.getNamedItem("startupid");
					Node nodeAttrOrderValue = nodeAttr.getNamedItem("order");
					String order = StringUtils.getTrimString(nodeAttrOrderValue.getTextContent());
					String id = StringUtils.getTrimString(nodeAttrIdValue.getTextContent());
					Integer orderSequnece = StringUtils.isNumber(order) ? NumberUtils.getIntegerFromObject(order) : Integer
							.valueOf("9999");
					orderSequnece = orderSequnece > Integer.valueOf("9999") ? Integer.valueOf("9999") : orderSequnece;
					orderSequnece = orderSequnece < Integer.valueOf("1") ? Integer.valueOf("1") : orderSequnece;
					if (StringUtils.isNotNullAndEmpty(id) && StringUtils.isNotNullAndEmpty(context))
					{
						StartConfig config = new StartConfig();
						config.setClazz(context);
						config.setId(id);
						config.setOrder(orderSequnece);
						startUpConfig.add(config);
					}
				}
			}
			catch (IOException e)
			{
				debug.warm(res.getFilename() + " Startup failed!");
			}

		}
		return startUpConfig;

	}

	private void invoke(List<StartConfig> startConfigList)
	{
		if (CollectionUtils.isEmpty(startConfigList))
		{
			return;
		}
		for (StartConfig startConfig : startConfigList)
		{
			String clazzName = startConfig.getClazz();
			try
			{
				Class clazz = Class.forName(clazzName);
				Object obj = clazz.newInstance();
				Start start = (Start) obj;
				start.start();

			}
			catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				debug.warm(startConfig.getId() + " execute failed!");
			}
			catch (InstantiationException e)
			{
				// TODO Auto-generated catch block
				debug.warm(startConfig.getId() + " execute failed!");
			}
			catch (IllegalAccessException e)
			{
				// TODO Auto-generated catch block
				debug.warm(startConfig.getId() + " execute failed!");
			}
		}

	}
}
