package com.nonobank.apps.business.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.account.Page_DegreeCard;
import com.nonobank.apps.utils.webintegration.Info;
import com.nonobank.apps.utils.webintegration.Params;
import com.nonobank.apps.utils.webintegration.Return;

@Info(name = "Biz_DegreeCard", desc = "认证管理页面", dependency = "com.nonobank.apps.business.portal.Biz_Login", isDisabled = false)
public class Biz_DegreeCard {
	public static Logger logger = LogManager.getLogger(Biz_DegreeCard.class);
	public Page_DegreeCard page_DegreeCard = new Page_DegreeCard();

	@Info(name = "nagivate_to_login", desc = "跳转到登录页面", dependency = "", isDisabled = false)
	public void nagivate_to_degreeCard() {
		logger.info("跳转到认证管理页面............");
		page_DegreeCard.nagivate_to_degreeCard();
	}

	@Info(name = "IDVerification", desc = "身份认证", dependency = "navigate_to_userbanks()", isDisabled = false)
	@Params(type = { "String", "String" }, name = { "myname", "identity_ID" }, desc = { "用户名", "身份证号码" })
	@Return(type = "String", desc = "test")
	public void IDVerification(String myname, String identity_ID) {
		logger.info("身份实名认证............");
		nagivate_to_degreeCard();
		page_DegreeCard.input_name(myname);
		page_DegreeCard.input_mycard(identity_ID);
		page_DegreeCard.submit();
		if (page_DegreeCard.isAlertExists(10000)) {
			page_DegreeCard.closeAlert();
		}
	}
}
