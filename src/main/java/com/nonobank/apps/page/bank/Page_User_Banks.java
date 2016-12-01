package com.nonobank.apps.page.bank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.data.RandomUtils;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.page.PageUtils;

/**
 * 银行账户页面
 */
public class Page_User_Banks extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_User_Banks.class);

	/**
	 * 跳转到认证管理页面
	 */
	public void navigate_to_banks() {
		String url_login = ParseProperties.getInstance().getProperty("url") + "/User/Banks";
		driver.navigate().to(url_login);
		logger.info(PageUtils.getUrl());
	}

	// 添加银行卡
	public void click_add_bankcard() {
		WebLink link_add_bankcard = objectFactory.getWebLink("addBankCard");
		link_add_bankcard.click();
	}

	// 实名认证
	public void verifyNameID(String name, String idNo) {
		if (isElementExists("verifyrealname", WebElementType.WebInput, 15)) {
			WebInput input_realName = objectFactory.getWebInput("verifyrealname");
			if (name.equals("random")) {
				// name = RandomUtils.getInstance().generateUserName();
				name = "李白";
			}
			input_realName.clearAndInput(name);
			WebInput input_IdNo = objectFactory.getWebInput("verifyidnum");
			if (idNo.toLowerCase().trim().equals("random")) {
				idNo = RandomUtils.getInstance().generateIdCardNumberByAge(20);
			}
			input_IdNo.clearAndInput(idNo);
			WebButton button_bind = objectFactory.getWebButton("bind");
			button_bind.click();
		}
	}
}
