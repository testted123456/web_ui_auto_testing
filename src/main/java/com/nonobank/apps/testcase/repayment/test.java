package com.nonobank.apps.testcase.repayment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.nonobank.apps.business.account.Biz_Account;
import com.nonobank.apps.business.admin.Biz_Audit_VideoAuditView;
import com.nonobank.apps.business.admin.Biz_Home;
import com.nonobank.apps.business.admin.Biz_Login;
import com.nonobank.apps.business.common.Biz_Common;
import com.nonobank.apps.business.repayment.Biz_Repayment;
import com.nonobank.apps.business.student.Biz_Apply;
import com.nonobank.apps.business.student.Biz_Improve;
import com.nonobank.apps.business.student.Biz_Register;
import com.nonobank.apps.business.student.Biz_VideoSign;
import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.testcase.student.BorrowsTestCase;
import com.nonobank.apps.utils.page.PageUtils;

public class test extends BaseCase{
	Biz_Register biz_register;
	Biz_Apply biz_Apply;
	Biz_Improve biz_Improve;
	Biz_VideoSign biz_VideoSign;
	Biz_Common biz_Common;
	Biz_Account biz_Account;
	Biz_Repayment biz_Repayment;
	Biz_Audit_VideoAuditView biz_Audit_VideoAuditView;
	Biz_Home biz_Home;
	Biz_Login biz_Login;
	public static Logger logger=LogManager.getLogger(test.class);
	
	@Test(dataProvider = "dataSource")
	public void test(String mobile_register,String userName_register) {
		// 初审结束后，需要切换到后台管理系统页面
		biz_Home.switch_adminHome();
		PageUtils.sleep(5000);
		// 后台进行终审
		biz_Home.navigate_to_lastAudit(mobile_register, userName_register);
		PageUtils.sleep(10000);
		biz_Audit_VideoAuditView.last_audit_pass();
		PageUtils.sleep(60000);
		// 初审结束后，需要切换到后台管理系统页面
		biz_Home.switch_adminHome();
		PageUtils.sleep(5000);
		// 切换到后台管理系统主菜单页面
		biz_Home.switch_adminMenu();
		// 执行名校贷非V3
		biz_Home.navigate_to_v3();
		PageUtils.sleep(10000);

		// 点击用户名
		biz_Common.click_userNameBus();
		PageUtils.sleep(3000);
		// 还款流程
		biz_Repayment.repaymentBus();
	}
}
