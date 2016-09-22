package com.nonobank.apps.page.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebSpan;
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
		WebSpan getText_name = objectFactory.getWebSpan("姓名");
		String realName=getText_name.getText();
		return realName;
	}
	public String getText_idCard(){
		logger.info("获取身份证号码....");
		WebSpan getText_idCard = objectFactory.getWebSpan("身份证号码");
		String idCard=getText_idCard.getText();
		return idCard;
	}
	public String getText_borrowsMoney(){
		logger.info("获取借款金额....");
		WebSpan getText_borrowsMoney = objectFactory.getWebSpan("借款金额");
		String borrowsMoney=getText_borrowsMoney.getText();
		return borrowsMoney;
	}
	public String getText_consultingFee(){
		logger.info("获取咨询费....");
		WebSpan getText_getText_consultingFee = objectFactory.getWebSpan("咨询费");
		String consultingFee=getText_getText_consultingFee.getText();
		return consultingFee;
	}
	
	
}
