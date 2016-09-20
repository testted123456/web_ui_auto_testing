package com.nonobank.apps.page.withdrawal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.page.base.BasePage;

public class Page_User_WithdrawalConfirm extends BasePage {
	
	public static Logger logger = LogManager.getLogger(Page_User_WithdrawalConfirm.class);
	
	/**
	 * 输入支付密码
	 * @param password
	 */
	public void input_pay_password(String password){
		logger.info("begin to input pay password...");
		WebInput input_password = objectFactory.getWebInput("accountpwd");
		input_password.clearAndInput(password);
	}
	
	/**
	 * 提交
	 */
	public void submit(){
		logger.info("begin to submit...");
		WebButton button = objectFactory.getWebButton("t_con_btn");
		button.click();
	}
	
	/**
	 * 校验提交是否成功
	 */
	public void isWithDrawalConfirmSuccess(){
		//继续提现连接是否存在
		if(isElementExists("withdrawal", WebElementType.WebLink, 15)){
			logger.info("widthdrawal successful.");
		}else{
			logger.error("widthdrawal failed.");
			Assert.fail();
		}
	}
}
