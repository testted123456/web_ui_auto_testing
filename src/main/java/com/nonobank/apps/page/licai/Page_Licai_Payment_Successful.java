package com.nonobank.apps.page.licai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.base.BasePage;

public class Page_Licai_Payment_Successful extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_Licai_Payment_Successful.class);

	// 查看投资记录是否存在
	public boolean is_record_exist() {
		String message = objectFactory.getWebLabel("查看投资记录").getText();
		if (message.contains("成功")) {
			return true;
		}
		return false;
	}

}
