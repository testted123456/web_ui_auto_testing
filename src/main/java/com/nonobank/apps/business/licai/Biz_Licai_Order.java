package com.nonobank.apps.business.licai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.page.licai.Page_Licai_Order;

public class Biz_Licai_Order {

	public static Logger logger = LogManager.getLogger(Biz_Licai_Order.class);

	Page_Licai_Order page_Licai_Order = new Page_Licai_Order();

	/**
	 * 确认购买
	 */
	public void submit() {
		logger.info("确认购买");
		page_Licai_Order.submit();
	}
}
