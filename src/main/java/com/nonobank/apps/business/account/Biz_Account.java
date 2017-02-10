package com.nonobank.apps.business.account;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.business.admin.Biz_Debt;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.business.student.Biz_VideoSign;
import com.nonobank.apps.page.account.Page_Account;
import com.nonobank.apps.utils.data.Assertion;
import com.nonobank.apps.utils.data.ConstantUtils;
import com.nonobank.apps.utils.db.DBUtils;
import com.nonobank.apps.utils.page.PageUtils;
import com.nonobank.apps.utils.webintegration.Info;

@Info(name = "Biz_Account", desc = "账户页面", dependency = "com.nonobank.apps.business.admin.Biz_Audit_VideoAuditView", isDisabled = false)
public class Biz_Account {
	public static Logger logger = LogManager.getLogger(Biz_Account.class);
	public Page_Account page_Account = new Page_Account();

	public void navigate_to_profile() {
		logger.info("点击个人设置......");
		page_Account.click_profile();
		PageUtils.sleep(3000);
	}

	public void navigate_to_degreeCard() {
		logger.info("点击认证管理......");
		page_Account.click_degreeCard();
	}

	public void navigate_to_banks() {
		logger.info("点击银行账户......");
		page_Account.click_banks();
		PageUtils.sleep(3000);
	}

	public void navigate_to_recharge() {
		logger.info("点击充值......");
		page_Account.click_recharge();
		if (1 != 0) {

		}
		PageUtils.waitForPageLoad();
	}

	public void navigate_to_withDrawal(String message) {
		logger.info("点击提现......");
		page_Account.click_withDrawal();
		if (message.equals("您的账户无法进行本操作！")) {
			Assertion.assertEquals(page_Account.getAlertText(), "您的账户无法进行本操作！", Biz_Login.class, "校验逾期用户");
			PageUtils.sleep(2000);
			page_Account.acceptAlert();
			ConstantUtils.setIs_dunning(true);
		}
		PageUtils.sleep(3000);
	}

	public void recharge(String mobile_num) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "update finance_account set balance = 1000000 where role_id= 7 and user_id = (SELECT id from user_info where mobile_num = "
				+ mobile_num + ")";
		DBUtils.updateOneObject(con, sql);
	}

	public void exec_re(String mobile_num) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "update borrows_repayment set br_time = CURDATE() where user_id = (SELECT id from user_info where mobile_num = "
				+ mobile_num + ")";
		DBUtils.updateOneObject(con, sql);
	}

	public void exec_over(String mobile_num) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "update borrows_repayment set br_time = CURDATE()+1 where user_id = (SELECT id from user_info where mobile_num = "
				+ mobile_num + ")";
		DBUtils.updateOneObject(con, sql);
	}

	public void checkSuccess(String mobile) {
		String sql = "SELECT id from invt_debt_sale_task where mobile_num=" + mobile;
		String ibat_sql = "SELECT id from invt_borrows_accept_task where user_id =(" + sql + ")";
		String ba_sql = "SELECT id from borrows_accept where is_pay=0 and user_id =(" + sql + ")";
		try {
			List<Object> ibat_ids = DBUtils.getMulLineValues("nono", ibat_sql);
			List<Object> ba_ids = DBUtils.getMulLineValues("nono", ba_sql);
			Assertion.assertEquals(ibat_ids.size() > 0, true, Biz_VideoSign.class, "校验invt_borrows_accept_task表中插入数据");
			Assertion.assertEquals(ba_ids.size() > 0, true, Biz_VideoSign.class, "校验invt_borrows_accept_task表中插入数据");
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}
}
