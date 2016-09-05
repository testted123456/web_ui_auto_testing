package com.nonobank.apps.page.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_Logout extends BasePage {
	public static Logger logger = LogManager.getLogger(Page_Logout.class);
	
	public void nagivate_to_logout(){
		logger.info("退出登录...");
		String url_logout = ParseProperties.getInstance().getProperty("url") +  "/Login/logout";
		driver.navigate().to(url_logout);
		sleep(500);
		PageUtils.refreshPage();
		//尝试10次退出
		for(int i=0;i<10;i++){
			if(objectFactory.isElementExists("Login", WebElementType.WebLink)){
				WebLink link_login = objectFactory.getWebLink("Login");
				if(link_login.isDisplayed()){
					break;
				}else{
					driver.navigate().to(url_logout);
					sleep(500);
				}
			}
		}
	}

	public boolean isLoginExist(){
		if(objectFactory.isElementExists("Login", WebElementType.WebLink)){
			WebLink link_login = objectFactory.getWebLink("Login");
			if(link_login.isDisplayed()){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
}
