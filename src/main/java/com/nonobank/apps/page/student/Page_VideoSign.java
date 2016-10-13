package com.nonobank.apps.page.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebCommon;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.page.base.BasePage;

public class Page_VideoSign extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_VideoSign.class);

	//判断(获得优先审核权—视频签约完成)按钮是否存在
	public boolean exist_getFirstAuditRight(){
		if(isElementExists("点击获得优先审核权—视频签约完成",WebElementType.WebLink, 15)){
			logger.info("(获得优先审核权—视频签约完成)按钮存在.....");
			return true;
		}else{
			logger.error("(获得优先审核权—视频签约完成)按钮不存在.....");
			return false;
		}
	}
	
	public String getText_name(){
		logger.info("获取用户姓名....");
		WebCommon getText_name = objectFactory.getWebCommon("姓名");
		String realName=getText_name.getText();
		return realName;
	}
	public String getText_idCard(){
		logger.info("获取身份证号码....");
		WebCommon getText_idCard = objectFactory.getWebCommon("身份证号码");
		String idCard=getText_idCard.getText();
		return idCard;
	}
	public String getText_borrowsMoney(){
		logger.info("获取借款金额....");
		WebCommon getText_borrowsMoney = objectFactory.getWebCommon("借款金额");
		String borrowsMoney=getText_borrowsMoney.getText();
		return borrowsMoney;
	}
	public String getText_consultingFee(){
		logger.info("获取咨询费....");
		WebCommon getText_getText_consultingFee = objectFactory.getWebCommon("咨询费");
		String consultingFee=getText_getText_consultingFee.getText();
		return consultingFee;
	}
	public String getText_lastViewPrompt(){
		logger.info("终审通过提示.....");
		WebCommon getText_lastViewPrompt=objectFactory.getWebCommon("审核通过提示语");
		String lastViewPrompt=getText_lastViewPrompt.getText();
		return lastViewPrompt;
	}
	
}
