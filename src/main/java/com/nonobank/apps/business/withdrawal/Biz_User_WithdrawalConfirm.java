package com.nonobank.apps.business.withdrawal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.page.withdrawal.Page_User_WithdrawalConfirm;
import com.nonobank.apps.utils.data.Assertion;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.page.PageUtils;

public class Biz_User_WithdrawalConfirm {

	public static Logger logger = LogManager.getLogger(Biz_User_WithdrawalConfirm.class);
	Page_User_WithdrawalConfirm page_User_WithdrawalConfirm = new Page_User_WithdrawalConfirm();

	/**
	 * 提现
	 * 
	 * @param pay_password
	 */
	public void confirm(String pay_password, String message) {
		try {
			logger.info("提现校验...");
			page_User_WithdrawalConfirm.input_pay_password(pay_password);
			PageUtils.sleep(2000);
			page_User_WithdrawalConfirm.submit();
			PageUtils.sleep(3000);
			handleResult(message);
		} catch (Error e) {
			handleResult(message);
		}
	}

	private void handleResult(String message) {
		switch (message) {
		case "success":
			String actualUrl = PageUtils.getUrl();
			String url = "/User/withdrawalsuccess";
			Assertion.assertEquals(actualUrl.contains(url), true, Biz_Login.class,
					"提现跳转到" + actualUrl + ",判断路径是否包含" + url + "字符");
			break;
		case "请输入支付密码！":
			String error_msg = page_User_WithdrawalConfirm.getElementText("messageInfo");
			Assertion.assertEquals(message, error_msg, Biz_Login.class, "反例-校验支付密码");
			break;
		}
	}

}
