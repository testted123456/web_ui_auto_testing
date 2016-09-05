package com.nonobank.apps.business.licai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.page.licai.Page_Licai_FinancePlan;

public class Biz_Licai_FinancePlan {

	public static Logger logger = LogManager.getLogger(Biz_Licai_FinancePlan.class);

	Page_Licai_FinancePlan page_Licai_FinancePlan = new Page_Licai_FinancePlan();

	/**
	 * 购买理财产品
	 * 
	 * @param id
	 * @param amount
	 */
	public void purchase(String id, String amount, String newurl) {
		logger.info("购买理财产品...");

		if (id.contains(".")) {
			int index = id.indexOf('.');
			id = id.substring(0, index);
		}

		if (amount.contains(".")) {
			int index = amount.indexOf('.');
			amount = amount.substring(0, index);
		}

		page_Licai_FinancePlan.navigate_to_financePlan(id, newurl);
		page_Licai_FinancePlan.input_amount(amount);
		page_Licai_FinancePlan.submit();
	}

}
