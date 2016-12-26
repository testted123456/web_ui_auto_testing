package com.nonobank.apps.business.bank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.page.bank.Page_User_BindCard2;
import com.nonobank.apps.utils.data.Assertion;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.page.PageUtils;

public class Biz_User_BindCard2 {

	public static Logger logger = LogManager.getLogger(Biz_User_BindCard2.class);

	Page_User_BindCard2 page_User_BindCard2 = new Page_User_BindCard2();

	/**
	 * 绑定银行卡成功
	 * 
	 * @param bankcard_no
	 */
	public void bindCard(String bankcard_no, String validation, String expectMessage) {
		try {
			logger.info("输入银行卡号...");
			page_User_BindCard2.input_bankcard_no(bankcard_no);
			logger.info("获取短信验证码...");
			page_User_BindCard2.click_getValidate();
			page_User_BindCard2.input_validateCode(validation);
			logger.info("提交");
			page_User_BindCard2.submit();
			PageUtils.waitForPageLoad();
			handleResult(expectMessage);
		} catch (Error e) {
			handleResult(expectMessage);
		}

	}

	private void handleResult(String expectMessage) {
		switch (expectMessage) {
		case "同一银行只能绑定一张卡，若需绑定新卡，请先解绑已绑定的卡！":
		case "请输入验证码":
			String actualMessage = page_User_BindCard2.getElementText("testmsg");
			Assertion.assertEquals(expectMessage, actualMessage, Biz_Login.class, "反例-银行卡绑定失败");
			break;
		case "请输入银行卡号":
			actualMessage = page_User_BindCard2.getElementText("error");
			Assertion.assertEquals(expectMessage, actualMessage, Biz_Login.class, "反例-银行卡绑定失败");
			break;
		default:
			String actualUrl = PageUtils.getUrl();
			String expectUrl = ParseProperties.getInstance().getProperty("url") + "/User/BindCard3";
			Assertion.assertEquals(expectUrl, actualUrl, Biz_Login.class, "正例-绑卡成功");
			break;
		}
	}
}
