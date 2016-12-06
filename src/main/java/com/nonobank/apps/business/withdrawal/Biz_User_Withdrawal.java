package com.nonobank.apps.business.withdrawal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import com.nonobank.apps.page.account.Page_Account;
import com.nonobank.apps.page.withdrawal.Page_User_Withdrawal;

public class Biz_User_Withdrawal {
	public static Logger logger = LogManager.getLogger(Biz_User_Withdrawal.class);
	Page_User_Withdrawal page_User_Withdrawal = new Page_User_Withdrawal();
	Page_Account page_Account = new Page_Account();

	/**
	 * 输入提现金额
	 * 
	 * @param cardno
	 * @param money
	 * @return ： 返回手续费：到账金额
	 */
	public String withDrawal(String cardno, String money) {
		logger.info("选择银行卡、输入提现金额...");
		page_User_Withdrawal.select_card(cardno);
		page_User_Withdrawal.input_money(money);
		String fee = page_User_Withdrawal.getFee();
		String amount = page_User_Withdrawal.get_amount();
		page_User_Withdrawal.goNext();
		page_User_Withdrawal.closeAlert();
		return fee + ":" + amount;
	}

	/**
	 * 校验提现金额
	 */
	public void checkWithdrawalCash() {
		logger.info("校验提现金额...");
		String username = page_User_Withdrawal.getUsername();
		// 校验余额
		String balance = page_User_Withdrawal.getBalance();
		String balance_from_db = page_User_Withdrawal.getBalance(username);

		if (null == balance_from_db) {
			logger.error("can't get balance from database.");
			Assert.fail();
		}
		Assert.assertEquals(Double.valueOf(balance_from_db), Double.valueOf(balance));
		page_User_Withdrawal.input_money("1");
		String error_msg = page_User_Withdrawal.getErrorMsg();
		Assert.assertEquals(error_msg, "本次最多可提现0元");
		// page_User_Withdrawal.select_random_card(username);
		page_User_Withdrawal.input_money("-1");
		error_msg = page_User_Withdrawal.getErrorMsg();
		Assert.assertEquals(error_msg, "提现金额不能小于1元！");
		page_User_Withdrawal.input_money("%");
		error_msg = page_User_Withdrawal.getErrorMsg();
		Assert.assertEquals(error_msg, "提现金额必须为数字");
		page_User_Withdrawal.input_money(String.valueOf((Double.valueOf(balance) + 10)));
		error_msg = page_User_Withdrawal.getErrorMsg();
		Assert.assertTrue(error_msg.contains("本次最多可提现"));
	}
}
