package com.nonobank.apps.business.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.account.Page_Account;
import com.nonobank.apps.utils.webintegration.Info;
import com.nonobank.apps.utils.webintegration.Params;
import com.nonobank.apps.utils.webintegration.Return;

@Info(desc="账户页面",dependency="com.nonobank.apps.business.admin.Biz_Audit_VideoAuditView",isDisabled=false)
public class Biz_Account {
	public static Logger logger = LogManager.getLogger(Biz_Account.class);
	public Page_Account page_Account = new Page_Account();

	@Info(desc="跳转到银行账户页面",dependency="tests()",isDisabled=false)
	public void navigate_to_userbanks() {
		logger.info("跳转到银行账户页面...");
		page_Account.click_bank_account();
	}

	@Info(desc="身份认证",dependency="navigate_to_userbanks()",isDisabled=false)
	@Params(type={"String","String"},name={"myname","identity_ID"},desc={"用户名","身份证号码"})
	@Return(type="String",desc="test")
	public String IDVerification(String myname, String identity_ID) {
		logger.info("身份实名认证...");
		page_Account.click_degreeCard();
		page_Account.input_name(myname);
		page_Account.input_mycard(identity_ID);
		page_Account.submit();

		if (page_Account.isAlertExists(10000)) {
			page_Account.closeAlert();
		} else {
			while (page_Account.isCardNoError()) {
				page_Account.input_mycard(identity_ID);
				page_Account.submit();
				if (page_Account.isAlertExists(1000)) {
					page_Account.closeAlert();
					break;
				}
			}
		}
		return "test";
	}

	// 设置支付密码
	public void setPayPassword(String payPassword, String payPassword1) {
		logger.info("设置支付密码...");
		page_Account.click_profile();
		page_Account.click_passwordSetting();
		page_Account.click_paypasswordSetting();
		page_Account.input_payPassword(payPassword);
		page_Account.input_payPassword_again(payPassword1);
		page_Account.setPasswordbtn();
		page_Account.closeAlert();
	}

	// 点击我的账单
	public void clickMyBillsBus() {
		logger.info("点击我的账单...");
		page_Account.click_myBill();
	}

	// 点击借款记录
	public void clickBorrowsRecordBus() {
		logger.info("点击借款记录...");
		page_Account.click_borrowsRecord();
	}

	// 点击借款资料
	public void clickBorrowsData() {
		logger.info("点击借款资料...");
		page_Account.click_borrowsData();
	}
}
