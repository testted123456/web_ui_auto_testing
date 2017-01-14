package com.nonobank.apps.business.withdrawal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.page.account.Page_Account;
import com.nonobank.apps.page.withdrawal.Page_User_Withdrawal;
import com.nonobank.apps.utils.data.Assertion;
import com.nonobank.apps.utils.page.PageUtils;

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
	public void withDrawal(String money, String message) {
		try {
			logger.info("选择银行卡、输入提现金额...");
			page_User_Withdrawal.input_money(money);
			PageUtils.sleep(2000);
			page_User_Withdrawal.goNext();
			PageUtils.sleep(2000);
			page_User_Withdrawal.closeAlert();
			PageUtils.sleep(2000);
		} catch (Error e) {
			switch (message) {
			case "提现金额必须为数字":
			case "提现金额不能小于1元！":
			case "本次最多可提现":
				String error_msg = page_User_Withdrawal.getElementText("error_msg");
				Assertion.assertEquals(error_msg.contains(message), true, Biz_Login.class, "反例-校验金额");
				
				break;
			}
		}
	}

}
