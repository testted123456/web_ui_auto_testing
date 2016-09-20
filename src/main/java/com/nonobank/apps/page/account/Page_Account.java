package com.nonobank.apps.page.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebCommon;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.objectRepository.WebSpan;
import com.nonobank.apps.page.base.BasePage;

public class Page_Account extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_Account.class);

	// 点击银行账户
	public void click_bank_account() {
		WebLink link_bank_account = objectFactory.getWebLink("Banks");
		link_bank_account.click();
	}

	// 点击提现
	public void click_withdrawal() {
		WebButton link_withdrawal = objectFactory.getWebButton("withdrawal");
		link_withdrawal.click();
	}

	// 点击充值
	public void click_recharge() {
		WebButton link_recharge = objectFactory.getWebButton("recharge");
		link_recharge.click();
	}

	// *****************************身份认证***************************************//
	// 点击认证管理
	public void click_degreeCard() {
		WebLink link_degreeCard = objectFactory.getWebLink("degreeCard");
		link_degreeCard.click();
	}

	// 输入真实姓名
	public void input_name(String myname) {
		WebInput input_myname = objectFactory.getWebInput("myname");
		input_myname.clearAndInput(myname);
	}

	// 输入身份证号
	public void input_mycard(String mycard) {
		WebInput input_mycard = objectFactory.getWebInput("mycard");
		input_mycard.clearAndInput(mycard);
	}

	// 认证通过
	public void submit() {
		WebInput submit = objectFactory.getWebInput("ok");
		submit.click();
	}

	// 身份证有误
	public boolean isCardNoError() {
		if (isElementExists("cardMsg", WebElementType.WebSpan, 15)) {
			WebSpan span_carMsg = objectFactory.getWebSpan("cardMsg");
			if (span_carMsg.isDisplayed()) {
				String text = span_carMsg.getText();
				if (text.equals("身份格式有误")) {
					return true;
				}
			}
		}
		return false;
	}

	// *****************************设置支付密码***************************************//

	// 点击个人设置
	public void click_profile() {
		WebLink link_profile = objectFactory.getWebLink("profile");
		link_profile.click();
	}

	// 点击密码设置
	public void click_passwordSetting() {
		WebCommon li_passwordSetting = objectFactory.getWebCommon("passwordSetting");
		li_passwordSetting.click();
	}

	// 点击密码设置
	public void click_paypasswordSetting() {
		// List<WebElement> list =
		// objectFactory.getWebElements(By.xpath("//li[@class='active']"));
		// for(WebElement element : list){
		// String text = element.getText();
		// if(text.equals("修改支付密码")){
		// element.click();
		// break;
		// }
		// }
		WebCommon li_payPasswordSetting = objectFactory.getWebCommon("payPasswordSetting");
		li_payPasswordSetting.click();
	}

	// 输入支付密码
	public void input_payPassword(String payPassword) {
		WebInput input_payPayassword = objectFactory.getWebInput("newZFPwd");
		input_payPayassword.clearAndInput(payPassword);
	}

	// 输入支付密码
	public void input_payPassword_again(String payPassword) {
		WebInput input_payPassword_again = objectFactory.getWebInput("newZFPassword");
		input_payPassword_again.clearAndInput(payPassword);
	}

	// 确认修改密码
	public void setPasswordbtn() {
		WebInput setPasswordbtn = objectFactory.getWebInput("setPasswordbtn");
		setPasswordbtn.click();
	}


}
