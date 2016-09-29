package com.nonobank.apps.page.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.objectRepository.WebCommon;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;

public class Page_CreditBookSearch extends BasePage{
	
	public static Logger logger = LogManager.getLogger(Page_Apply.class);
	public String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	//获取大学生信用等级证书
	public String getText_universityCredit(){
		logger.info("获取大学生信用等级证书...");
		WebCommon getText_universityCredit=objectFactory.getWebCommon("大学生信用等级证书");
		String universityCreditContent=getText_universityCredit.getText();
		return universityCreditContent;
	}
	//输入证书编号
	public void input_creditCode(String creditCode){
		logger.info("输入证书编号..."); 
		WebInput input_creditCode=objectFactory.getWebInput("输入证书编号");
		input_creditCode.clearAndInput(creditCode);
	}
	//点击证书查询
	public void click_creditQuery(){
		logger.info("点击证书查询..."); 
		WebLink click_creditQuery=objectFactory.getWebLink("证书查询");
		click_creditQuery.click();
	}
		
}
