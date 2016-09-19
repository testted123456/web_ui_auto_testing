package com.nonobank.apps.page.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.page.base.BasePage;

public class Page_VideoSign extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_VideoSign.class);

	//判断(获得优先审核权—视频签约完成)按钮是否存在
	public boolean exist_getFirstAuditRight(){
		if(objectFactory.isElementExists("点击获得优先审核权—视频签约完成",WebElementType.WebLink)){
			logger.info("(获得优先审核权—视频签约完成)按钮存在.....");
			return true;
		}else{
			logger.info("(获得优先审核权—视频签约完成)按钮不存在.....");
			return false;
		}
	}

}
