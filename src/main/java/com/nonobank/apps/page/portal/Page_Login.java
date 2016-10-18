package com.nonobank.apps.page.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_Login extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_Login.class);

	public void nagivate_to_login() {
		String url_login = ParseProperties.getInstance().getProperty("url") + "/Login";
		driver.navigate().to(url_login);
		logger.info(PageUtils.getUrl());
	}

	public void input_username(String usernmae, String param) {
		WebInput input_username = objectFactory.getWebInput("loginname");
		input_username.clearAndInput(usernmae);
	}

	public void input_password(String password) {
		WebInput input_password = objectFactory.getWebInput("loginpwd");
		input_password.clearAndInput(password);
	}

	public void input_checkCode() {
		if (isElementExists("checkCode", WebElementType.WebInput, 15)) {
			WebInput input_checkCode = objectFactory.getWebInput("checkCode");
			input_checkCode.clearAndInput("a1b0");
		}
		sleep(6000);
	}

	public void submit() {
		WebButton button = objectFactory.getWebButton("btnlogin");
		button.click();
		sleep(1000);

		if (isElementExists("logout", WebElementType.WebLink, 15)) {
			WebLink logout = objectFactory.getWebLink("logout");
			if (logout.isDisplayed()) {
				return;
			}
		}
		PageUtils.waitForPageLoad();
	}

}
