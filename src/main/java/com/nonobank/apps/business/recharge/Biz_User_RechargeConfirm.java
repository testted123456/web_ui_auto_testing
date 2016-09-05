package com.nonobank.apps.business.recharge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import com.nonobank.apps.page.recharge.Page_User_RechargeConfirm;

public class Biz_User_RechargeConfirm {

	public static Logger logger = LogManager.getLogger(Biz_User_RechargeConfirm.class);

	Page_User_RechargeConfirm page_User_RechargeConfirm = new Page_User_RechargeConfirm();

	/**
	 * 输入提现金额、密码，并校验
	 * 
	 * @param money
	 * @param payPassword
	 */
	public void rechargeConfirm(String money, String payPassword, String smsCode) {
		logger.info("充值确认...");
		page_User_RechargeConfirm.input_money(money);
		page_User_RechargeConfirm.input_pay_password(payPassword);
		page_User_RechargeConfirm.submit();
		page_User_RechargeConfirm.input_smsCode(smsCode);
		page_User_RechargeConfirm.submit_smsCode();
	}

	/**
	 * 校验充值金额
	 */
	public void checkMoney() {
		logger.info("校验充值金额...");

		int limit_day = -1;

		String bank_name = page_User_RechargeConfirm.getBankName();

		if (bank_name.equals("中国建设银行")) {
			limit_day = 2000000;
		}

		if (bank_name.equals("中国工商银行")) {
			limit_day = 50000;
		}

		// 校验最小金额
		page_User_RechargeConfirm.input_money("9");

		String msg = page_User_RechargeConfirm.getMoneyMsg();

		Assert.assertEquals("单笔充值金额必须≧10元", msg);

		// 校验每次限额
		limit_day++;

		page_User_RechargeConfirm.input_money(String.valueOf(limit_day));

		msg = page_User_RechargeConfirm.getMoneyMsg();

		Assert.assertEquals("不能超过单笔限额", msg);

		page_User_RechargeConfirm.input_money("11");
	}

	/**
	 * 校验支付密码
	 * 
	 * @param pay_password
	 */
	public void checkPayPassword(String pay_password) {
		logger.info("校验支付密码...");
		page_User_RechargeConfirm.input_pay_password(pay_password);
		page_User_RechargeConfirm.submit();
		String msg = page_User_RechargeConfirm.getPayPasswordMsg();
		Assert.assertEquals("支付密码错误！", msg);
	}

	/**
	 * 判断金额是否成功
	 * 
	 * @param money
	 * @param pay_password
	 */
	public boolean isRechargeConfirmSuccess(String money) {
		String counterFee = page_User_RechargeConfirm.getCounterFee();
		logger.info("判断充值是否成功...");
		boolean flag = page_User_RechargeConfirm.isRechargeConfirmSuccess(money, counterFee);
		if (flag == true) {
			logger.info("充值成功...");
		} else {
			logger.info("充值失败...");
		}
		return flag;
	}
}
