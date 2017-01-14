package com.nonobank.apps.page.recharge;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.nonobank.apps.objectRepository.WebCommon;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_User_Recharge extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_User_Recharge.class);

	// 选择其他银行
	public void click_otherBank(String bankCode,String bankName) {
		// WebLink link = objectFactory.getWebLink("reSelectCard3");
		// link.click();
		WebElement elementUl = objectFactory.getWebElement(By.id("hasbind_card_list"));
		try {
			List<WebElement> elementLi = elementUl.findElements(By.xpath("//span[@class='bankIcon " + bankCode + "']"));
			for (WebElement webElement : elementLi) {
				String bankAllName = webElement.getText();
				if (bankAllName.contains(bankName)) {
					webElement.click();

					break;
				}
			}
		} catch (Error e) {
		}
	}

	// 使用新银行卡
	public void click_newBank() {
		WebLink link = objectFactory.getWebLink("reSelectCard4");
		link.click();
	}

	// 下一步按钮
	public void nextStep() {
		PageUtils.waitForPageLoad();
		sleep(1000);
		WebCommon input = objectFactory.getWebCommon("paybtn");
		input.click();
	}

	// 判断点击下一步是否成功
	public void isNextStepSuccess() {
		if (!isElementExists("paybtn", WebElementType.WebButton, 15)) {
			logger.info("点击下一步失败...");
			Assert.fail("click next step failed.");
		}
	}
}
