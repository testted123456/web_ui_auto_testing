package com.nonobank.apps.page.licai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_Licai_FinancePlan extends BasePage {
	public static Logger logger = LogManager.getLogger(Page_Licai_FinancePlan.class);

	// 跳转到理财页面
	public void navigate_to_financePlan(String id, String newurl) {
		String url = ParseProperties.getInstance().getProperty("url");
		url = url +newurl + id;
		PageUtils.openPage(url);
		System.out.println("*************************url="+url);
		PageUtils.waitForPageLoad();
	}

	// 输入购买份数
	public void input_amount(String amount) {
		WebInput input_amount = objectFactory.getWebInput("购买份数");
		input_amount.clearAndInput(amount);
	}

	// 购买
	public void submit() {
		WebButton button_purchase = objectFactory.getWebButton("购买");
		button_purchase.click();
	}
}
