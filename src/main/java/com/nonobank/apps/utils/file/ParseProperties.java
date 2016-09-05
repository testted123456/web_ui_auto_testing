package com.nonobank.apps.utils.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ParseProperties {

	private static Properties prop = null;
	
	private ParseProperties(){
	}
	
	public static Properties getInstance(){
		if(null == prop){
			prop = new Properties();
			try {
				String os = System.getProperties().getProperty("os.name");
				if(os.startsWith("Mac")){
//					prop.load(new FileInputStream("config/config-mac.properties"));
					prop.load(ParseProperties.class.getClassLoader().getResourceAsStream("config/config-mac.properties"));
				}else{
					prop.load(ParseProperties.class.getClassLoader().getResourceAsStream("config/config-win.properties"));
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return prop;
	}
	
	
}
