package com.nonobank.apps.page.licai;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebCheckBox;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.data.IdBankGenerator;

public class Page_Licai_Payment extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_Licai_Payment.class);

	

	// 下一步
	public void click_nextStep() {
		sleep(1000);
		WebButton button_nextStep = objectFactory.getWebButton("下一步");
		button_nextStep.click();
	}

	// 输入支付密码
	public void input_payPassword(String payPassword) {
		WebInput input_payPassword = objectFactory.getWebInput("支付密码");
		input_payPassword.clearAndInput(payPassword);
	}

	// 确认支付
	public void submit() {
		WebButton submit = objectFactory.getWebButton("确认支付");
		submit.click();
	}

	// 不勾选“账户余额”
	public void unCheckBanlance() {
		WebCheckBox checkbox = objectFactory.getWebCheckBox("账户余额");
		checkbox.uncheck();
	}

	// *********************************************************************使用其他银行卡**********************************************************************************

	// 点击其他银行卡按钮
	public void click_otherCard() {
		WebLink link_reSelectCard = objectFactory.getWebLink("选择其他银行卡");
		link_reSelectCard.click();
	}

	/**
	 * 根据卡的尾号选择其他银行卡
	 * 
	 * @param cardno
	 */
	public void select_OldCard(String cardno) {
		List<WebElement> elements = objectFactory.getWebElements(By.xpath("//small[contains(@class,'ml')]"));
		String text = null;

		int len = cardno.length();
		String tail = cardno.substring(len - 4, len);

		// 选择银行卡
		for (WebElement e : elements) {
			text = e.getText();
			if (text.endsWith(tail)) {
				e.click();
				break;
			}
		}

		// 判断选择银行卡是否成功
		if (!text.endsWith(tail)) {
			logger.info("选择银行卡失败...");
			Assert.fail("选择银行卡失败.");
		}
	}

	// 使用提前银行卡，输入短信验证码
	public void input_smsCodeForOldBankCard(String smsCode) {
		WebInput input_smsCode = objectFactory.getWebInput("短信验证码");
		input_smsCode.clearAndInput(smsCode);
	}

	// 使用老银行卡，提交短信验证码
	public void submit_smsCodeForOldBankCard() {
		WebButton button_smsCode = objectFactory.getWebButton("提交短信验证码");
		button_smsCode.click();
	}

	// 使用老银行卡，输入支付密码
	public void input_paypasswordForOldBankCard(String payPassword) {
		WebInput input_payPassword = objectFactory.getWebInput("老银行卡支付密码");
		input_payPassword.clearAndInput(payPassword);
	}

	// *********************************************************************使用新银行卡**********************************************************************************

	public boolean is_NewBankCard_exist() {
		if (isElementExists("使用新银行卡", WebElementType.WebLink, 5)) {
			WebLink link_reSelectCard = objectFactory.getWebLink("使用新银行卡");
			if (link_reSelectCard.isDisplayed()) {
				return true;
			}
		}
		return false;
	}

	// 点击使用新银行卡
	public void click_newBankCard() {
		WebLink link_reSelectCard = objectFactory.getWebLink("使用新银行卡");
		link_reSelectCard.click();
	}

	// 选择新银行卡
	public void select_newBank(String bank_name) {
		String bank_code = IdBankGenerator.map.get(bank_name);

		String xpath = null;

		xpath = "//span[@class='bank " + bank_code + "']";

		List<WebElement> lst = objectFactory.getWebElements(By.xpath(xpath));
		for (WebElement webElement : lst) {
			if (webElement.getText().equals(bank_name)) {
				webElement.click();
				break;
			}
		}

	}

	// 使用新银行卡，点下一步
	public void click_nextStepForNewCard() {
		WebButton button = objectFactory.getWebButton("选择新银行卡下一步");
		button.click();
	}

	// 输入新银行卡卡号
	public void input_newBankCard(String cardNO) {
		WebInput input_cardno = objectFactory.getWebInput("新银行卡卡号");
		input_cardno.clearAndInput(cardNO);
	}

	// 输入新银行卡卡号，获取验证码
	public void click_validationCodeForNewCard() {
		WebLink link_validationCode = objectFactory.getWebLink("获取验证码");
		link_validationCode.click();
	}

	// 输入新银行卡卡号，输入验证码
	public void input_validationCodeForNewCard(String validationCode) {
		WebInput input_validationCode = objectFactory.getWebInput("输入验证码");
		input_validationCode.clearAndInput(validationCode);
	}

	// 输入新银行卡卡号，输入支付密码
	public void input_payPasswordForNewCard(String payPassword) {
		WebInput input_payPassword = objectFactory.getWebInput("新银行卡支付密码");
		input_payPassword.clearAndInput(payPassword);
	}

	// 输入新银行卡卡号，同意协议并付款
	public void submitForNewCard() {
		WebButton button = objectFactory.getWebButton("同意协议并付款");
		button.click();
		sleep(3000);
	}
}
