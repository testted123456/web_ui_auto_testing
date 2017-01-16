package com.nonobank.apps.page.recharge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebCommon;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLabel;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.objectRepository.WebSpan;
import com.nonobank.apps.page.base.BasePage;

public class Page_User_RechargeConfirm extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_User_RechargeConfirm.class);

	/**
	 * 充值金额
	 * 
	 * @param money
	 */
	public void input_money(String money) {
		WebInput input_money = objectFactory.getWebInput("amount");
		input_money.clearAndInput(money);
	}

	/**
	 * 支付密码
	 * 
	 * @param password
	 */
	public void input_pay_password(String password) {
		WebInput input_password = objectFactory.getWebInput("accountpwd");
		input_password.clearAndInput(password);
	}

	// 输入短信验证码
	public void input_smsCode(String smsCode) {
		if (isElementExists("validnum", WebElementType.WebInput, 15)) {
			WebInput input_smsCode = objectFactory.getWebInput("validnum");
			input_smsCode.input(smsCode);
		}
	}

	public void submit_smsCode() {
		if (isElementExists("btn_submit", WebElementType.WebInput, 15)) {
			WebInput submit_smsCode = objectFactory.getWebInput("btn_submit");
			submit_smsCode.click();
		}
	}

	/**
	 * 获取充值手续费
	 * 
	 * @return
	 */
	public String getCounterFee() {

		String counter_fee = null;

		if (isElementExists(By.xpath("//b[@id='counterfee']"), 15)) {
			WebElement e = objectFactory.getWebElement(By.xpath("//b[@id='counterfee']"));
			counter_fee = e.getText();
		} else {
			logger.warn("没有显示手续费...");
		}
		return counter_fee;
	}

	/**
	 * 确认充值
	 */
	public void submit() {
		WebButton button = objectFactory.getWebButton("paybtn");
		button.click();
	}

	/**
	 * 充值校验
	 * 
	 * @param money
	 * @param counterFee
	 */
	public boolean isRechargeConfirmSuccess(String money, String counterFee) {
		WebLink link_rechargebtn = objectFactory.getWebLink("rechargebtn");

		if (link_rechargebtn.isDisplayed()) {
			WebCommon number = objectFactory.getWebCommon("number");
			String m = number.getText();
			return Double.valueOf(m) == Double.valueOf(counterFee) + Double.valueOf(money) ? true : false;
		}
		return false;
	}

	/**
	 * 获取银行名称
	 * 
	 * @return
	 */
	public String getBankName() {
		WebElement element_bank = objectFactory.getWebElement(By.xpath("//span[contains(@class,'bankIcon ')]"));
		String bank_name = element_bank.getAttribute("data-bankname");
		return bank_name;
	}

	/**
	 * 获取金额提示信息
	 * 
	 * @return
	 */
	public String getMoneyMsg() {
		WebLabel label_msg = objectFactory.getWebLabel("msg");
		String msg = label_msg.getText();
		return msg;
	}

	/**
	 * 获取密码提示信息
	 * 
	 * @return
	 */
	public String getPayPasswordMsg() {
		WebSpan span = objectFactory.getWebSpan("messageInfo");
		String msg = span.getText();
		return msg;
	}
}
