package com.huayu.eframe.server.tool.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlUtils
{

	public static NodeList paraseXMLForNodeListByTagNameAndFileName(String filePath, String tagName)
	{

		File file = new File(filePath);

		NodeList nodeList = paraseXMLForNodeListByTagName(tagName, file,null);

		return nodeList;
	}
	
	public static NodeList paraseXMLForNodeListByTagNameAndInputStream(InputStream inputStream, String tagName)
	{

		NodeList nodeList = paraseXMLForNodeListByTagName(tagName, null,inputStream);

		return nodeList;
	}

	public static NodeList paraseXMLForNodeListByTagName(String tagName, File file ,InputStream inputStream)
	{
		NodeList nodeList = null;
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = factory.newDocumentBuilder();

			Document doc = null;
			if(null == file)
			{
				doc = db.parse(inputStream);
			}
			else
			{
				doc = db.parse(file);
			}
			
			Element elmtInfo = doc.getDocumentElement();

			nodeList = elmtInfo.getElementsByTagName(tagName);

		}
		catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nodeList;
	}

	public static NodeList paraseXMLForNodeListByNodeAndTagName(Node node, String tagName)
	{
		if (node.getNodeName().equals(tagName))
		{
			return node.getChildNodes();
		}
		return null;
	}

	public static String paraseXMLForTextContentByNodeAndTagName(Node node, String tagName)
	{
		if (node.getNodeName().equals(tagName))
		{
			return node.getTextContent();
		}
		return "";
	}
	
	public static String paraseXMLForNodeValueByNodeAndTagName(Node node ,String attrItem)
	{
		if (node.getNodeName().equals(attrItem))
		{
			return node.getNodeValue();
		}
		return "";
	}
	
	public static <T> T paraseXMLFiles(Class<T> clazz, File file)
	{
		try
		{
			return paraseXMLFiles(clazz, new FileInputStream(file));
		}
		catch (FileNotFoundException e1)
		{
			return null;
		}
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public static <T> T paraseXMLFiles(Class<T> clazz,InputStream inputStream)
	{
		T obj = null;
		try
		{
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			obj =  (T)jaxbUnmarshaller.unmarshal(inputStream);
			
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		finally
		{
			return obj;
		}
	}

	public static void main(String[] args)
	{
//		String path = "C:\\Staffs\\Administrator\\Desktop\\exception.xml";
//		NodeList nodeList = XmlUtils.paraseXMLForNodeListByTagNameAndFileName(path,"exception");
//
//		for(int i = 0;i<nodeList.getLength();i++)
//		{
//
//			Node item = nodeList.item(i);
//			Node languageNode = item.getAttributes().getNamedItem("language");
//			String language = languageNode.getNodeValue();
//
//			NodeList childNodeList = item.getChildNodes();
//			for(int j = 0;j<childNodeList.getLength();j++)
//			{
//				Node childItem = childNodeList.item(j);
//				System.out.println(childItem.getNodeName());
//				System.out.println(childItem.getAttributes().getNamedItem("name").getNodeValue());
//			}
//		}
		//		ExceptionStartUp exception =  new ExceptionStartUp();
		//        System.out.print("start");
		//		exception.setFile(new File("E:\\product\\idea\\eframe\\eframe_server\\src\\main\\resources\\META-INF\\exception\\ipf_common_exception.xml"));
		//		exception.read();
//		File file = new File("E:\\product\\idea\\eframe\\eframe_server\\src\\main\\resources\\META-INF\\exception\\ipf_common_exception.xml");
//		XMLExceptionBOMapping mapping = XmlUtils.paraseXMLFiles(XMLExceptionBOMapping.class,file);
//		System.out.print(mapping.getException().get(0).getSingleException().get(1).getErrorCode());
	}
}
