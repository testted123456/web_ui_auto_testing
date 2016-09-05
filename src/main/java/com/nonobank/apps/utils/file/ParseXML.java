package com.nonobank.apps.utils.file;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.testng.Assert;

import com.nonobank.apps.objectRepository.WebElementType;

public class ParseXML {
	
	public static Logger logger = LogManager.getLogger(ParseXML.class);
	
	public static File getFile(String filePath){
		URL url = ParseXLSX.class.getClassLoader().getResource(filePath);
		
		String path = null;
		
		try {
			path = java.net.URLDecoder.decode(url.getFile(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File file = new File(path);
		return file;
	}
	
	/**
	 * 获取单个节点的xpath
	 * @param elementName
	 * @param elementType
	 * @param file
	 * @return
	 */
	public static String getXPath(String elementName, WebElementType elementType, String file){
		SAXReader reader = new SAXReader();
		Document doc;
		Element currentElement = null;
		
		try {
			file = java.net.URLDecoder.decode(file,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		File f = getFile(file);
		
		try{
			doc = reader.read(f);
			Element root = doc.getRootElement();
			List<Element> sunElements = root.elements(elementType.toString());
			
			for(Element e : sunElements){
				if(elementName.equals(e.attribute("name").getText())){
					currentElement = e;
					break;
				}
			}
		}catch(DocumentException e){
			e.printStackTrace();
		}finally{
			doc = null;
			reader = null;
		}
		
		if(null == currentElement){
			logger.info("can't get WebElement, please check your element name and file path.");
			logger.info("element name is " +  elementName);
			logger.info("file path is " + file);
			Assert.fail();
		}
		
		String xPath = currentElement.attribute("xpath").getText();
		
		if(null != xPath){
			return xPath;
		}else{
			logger.info("can not get xpath.");
			return null;
		}
	}
}
