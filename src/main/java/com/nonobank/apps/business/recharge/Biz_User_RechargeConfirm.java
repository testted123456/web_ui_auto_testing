package com.nonobank.apps.business.recharge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.page.recharge.Page_User_RechargeConfirm;
import com.nonobank.apps.utils.data.Assertion;
import com.nonobank.apps.utils.page.PageUtils;

public class Biz_User_RechargeConfirm {

	public static Logger logger = LogManager.getLogger(Biz_User_RechargeConfirm.class);

	Page_User_RechargeConfirm page_User_RechargeConfirm = new Page_User_RechargeConfirm();

	/**
	 * 输入提现金额、密码，并校验
	 * 
	 * @param money
	 * @param payPassword
	 */
	public void rechargeConfirm(String money, String payPassword, String message) {
		try {
			logger.info("充值确认...");
			page_User_RechargeConfirm.input_money(money);
			PageUtils.sleep(2000);
			page_User_RechargeConfirm.input_pay_password(payPassword);
			PageUtils.sleep(2000);
			page_User_RechargeConfirm.submit();
			PageUtils.sleep(2000);
			page_User_RechargeConfirm.input_smsCode("0615");
			PageUtils.sleep(2000);
			page_User_RechargeConfirm.submit_smsCode();
			PageUtils.sleep(5000);
			handleResult(payPassword, message);
		} catch (Error e) {
			handleResult(payPassword, message);
		}

	}

	private void handleResult(String payPassword, String message) {
		switch (message) {
		case "success":
			String actualUrl = PageUtils.getUrl();
			String url = "/User/RechargeSuccess";
			Assertion.assertEquals(actualUrl.contains(url), true, Biz_Login.class,
					"充值跳转到" + actualUrl + ",判断路径是否包含"+url+"字符");
			break;
		case "单笔充值金额必须≧10元":
		case "不能超过单笔限额":
			String msg = page_User_RechargeConfirm.getMoneyMsg();
			Assertion.assertEquals(message, msg, Biz_User_RechargeConfirm.class, "反例-校验金额");
			break;
		case "支付密码错误！":
			msg = page_User_RechargeConfirm.getPayPasswordMsg();
			Assertion.assertEquals(message, msg, Biz_User_RechargeConfirm.class, "反例-校验支付密码错误");
			break;
		}
	}

}
