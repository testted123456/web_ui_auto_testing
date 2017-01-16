package com.nonobank.apps.page.bank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;

/**
 * 银行账户页面
 */
public class Page_User_Banks extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_User_Banks.class);

	// 添加银行卡
	public void click_add_bankcard() {
		WebLink link_add_bankcard = objectFactory.getWebLink("addBankCard");
		link_add_bankcard.click();
	}

	// 实名认证
	public void verifyNameID(String name, String idNo) {
		if (isElementExists("verifyrealname", WebElementType.WebInput, 15)) {
			WebInput input_realName = objectFactory.getWebInput("verifyrealname");
			input_realName.clearAndInput(name);
			WebInput input_IdNo = objectFactory.getWebInput("verifyidnum");
			input_IdNo.clearAndInput(idNo);
			WebButton button_bind = objectFactory.getWebButton("bind");
			button_bind.click();
		}
	}
}
