package com.nonobank.apps.business.recharge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.page.recharge.Page_User_RechargeConfirm;
import com.nonobank.apps.utils.data.Assertion;
import com.nonobank.apps.utils.file.ParseProperties;
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
			page_User_RechargeConfirm.input_pay_password(payPassword);
			page_User_RechargeConfirm.submit();
			page_User_RechargeConfirm.input_smsCode("0615");
			page_User_RechargeConfirm.submit_smsCode();
		} catch (Error e) {
			handleResult(payPassword, message);
		}

	}

	private void handleResult(String payPassword, String message) {
		switch (message) {
		case "单笔充值金额必须≧10元":
			page_User_RechargeConfirm.input_money("9");
			String msg = page_User_RechargeConfirm.getMoneyMsg();
			Assertion.assertEquals(message, msg, Biz_User_RechargeConfirm.class, "反例-校验最小金额");
			break;
		case "不能超过单笔限额":
			int limit_day = -1;

			String bank_name = page_User_RechargeConfirm.getBankName();

			if (bank_name.equals("中国建设银行")) {
				limit_day = 2000000;
			}

			if (bank_name.equals("中国工商银行")) {
				limit_day = 50000;
			}
			limit_day++;
			page_User_RechargeConfirm.input_money(String.valueOf(limit_day));
			msg = page_User_RechargeConfirm.getMoneyMsg();
			Assertion.assertEquals(message, msg, Biz_User_RechargeConfirm.class, "反例-校验单笔限额");
			break;
		case "支付密码错误！":
			page_User_RechargeConfirm.input_pay_password(payPassword);
			page_User_RechargeConfirm.submit();
			msg = page_User_RechargeConfirm.getPayPasswordMsg();
			Assertion.assertEquals(message, msg, Biz_User_RechargeConfirm.class, "反例-校验支付密码错误");
			break;
		}
	}

}
