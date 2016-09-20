package com.nonobank.apps.business.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.account.Page_Account;
import com.nonobank.apps.utils.data.IdCardGenerator;

public class Biz_Account {
	public static Logger logger = LogManager.getLogger(Biz_Account.class);
	
	public Page_Account page_Account = new Page_Account();
	
	/**
	 * 跳转到银行账户页面
	 */
	public void navigate_to_userbanks(){
		logger.info("跳转到银行账户页面...");
		page_Account.click_bank_account();
	}
	
	//身份认证
	public void IDVerification(String myname, String mycard){
		logger.info("身份实名认证...");
		page_Account.click_degreeCard();
		page_Account.input_name(myname);
		
		String newMyCard = null;
		
		if(mycard.equals("random")){
			IdCardGenerator idCardGenerator = new IdCardGenerator();
			newMyCard = idCardGenerator.generateIdCardNumberByAge(20);
		}
		
		page_Account.input_mycard(newMyCard);
		page_Account.submit();
		
		if(page_Account.isAlertExists(10000)){
			page_Account.closeAlert();
		}else{
			while(page_Account.isCardNoError()){
				if(mycard.equals("random")){
					IdCardGenerator idCardGenerator = new IdCardGenerator();
					newMyCard = idCardGenerator.generateIdCardNumberByAge(20);
				}
				page_Account.input_mycard(newMyCard);
				page_Account.submit();
				if(page_Account.isAlertExists(1000)){
					page_Account.closeAlert();
					break;
				}
			}
		}
	}
	
	//设置支付密码
	public void setPayPassword(String payPassword, String payPassword1){
		logger.info("设置支付密码...");
		page_Account.click_profile();
		page_Account.click_passwordSetting();
		page_Account.click_paypasswordSetting();
		page_Account.input_payPassword(payPassword);
		page_Account.input_payPassword_again(payPassword1);
		page_Account.setPasswordbtn();
		page_Account.closeAlert();
	}
	

}
