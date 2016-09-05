package com.nonobank.apps.page.recharge;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_User_Recharge extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_User_Recharge.class);

	/**
	 * 跳转到充值页面
	 */
	public void navigate_to_recharge() {
		String url_recharge = ParseProperties.getInstance().getProperty("url_recharge");
		driver.navigate().to(url_recharge);

		// 判断跳转充值页面是否成功
		if (!objectFactory.isElementExists("pay_btn", WebElementType.WebButton)) {
			logger.info("跳转充值页面失败...");
			Assert.fail("navigate to recharge page failed.");
		}
	}

	/**
	 * 根据卡的尾号选择银行卡
	 * 
	 * @param cardno
	 */
	public void select_card(String cardno) {
		WebLink link_reSelectCard = objectFactory.getWebLink("reSelectCard3");
		link_reSelectCard.click();
		List<WebElement> elements = objectFactory.getWebElements(By.xpath("//small[contains(@class,'ml')]"));
		String text = null;
		// 选择银行卡
		for (WebElement e : elements) {
			text = e.getText();
			if (text.endsWith(cardno)) {
				e.click();
				break;
			}
		}
		// 判断选择银行卡是否成功
		if (!text.endsWith(cardno)) {
			logger.info("选择银行卡失败...");
			Assert.fail("select bankcard failed.");
		}
	}

	// 下一步按钮
	public void nextStep() {
		PageUtils.waitForPageLoad();
		sleep(1000);
		WebButton button = objectFactory.getWebButton("pay_btn");
		button.click();
	}

	// 判断点击下一步是否成功
	public void isNextStepSuccess() {
		if (!objectFactory.isElementExists("paybtn", WebElementType.WebButton)) {
			logger.info("点击下一步失败...");
			Assert.fail("click next step failed.");
		}
	}
}
