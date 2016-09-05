package com.nonobank.apps.business.licai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.nonobank.apps.page.licai.Page_Licai_Payment_Successful;

public class Biz_Licai_Payment_Successful {

	public static Logger logger = LogManager.getLogger(Biz_Licai_Payment_Successful.class);

	Page_Licai_Payment_Successful page_Licai_Payment_Successful = new Page_Licai_Payment_Successful();

	// 支付是否成功
	public boolean paymentSuccessful() {
		boolean flag = page_Licai_Payment_Successful.is_record_exist();
		if (!flag) {
			Assert.fail("支付失败...");
		} else {
			logger.info("支付成功...");
		}
		return flag;
	}
}
