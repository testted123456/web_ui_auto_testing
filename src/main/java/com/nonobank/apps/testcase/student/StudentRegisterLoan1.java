package com.nonobank.apps.testcase.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.admin.Biz_Admin_Login;
import com.nonobank.apps.business.admin.Biz_Admin_Audit_VideoAuditView;
import com.nonobank.apps.business.admin.Biz_Admin_Home;
import com.nonobank.apps.testcase.base.BaseCase;

public class StudentRegisterLoan1 extends BaseCase {
	
	public static Logger logger = LogManager.getLogger(StudentRegisterLoan1.class);
	
	@Test(dataProvider="dataSource")
	public void test(String admin_name,String admin_password, String user_mobile, String user_name){
		Biz_Admin_Login biz_Admin_Login = new Biz_Admin_Login();
		Biz_Admin_Home biz_Admin_home = new Biz_Admin_Home();
		Biz_Admin_Audit_VideoAuditView biz_Admin_Audit_VideoAuditView = new Biz_Admin_Audit_VideoAuditView();
		
		biz_Admin_Login.login(admin_name, admin_password);
		biz_Admin_home.navigate_to_first_audit(user_mobile, user_name);
		biz_Admin_Audit_VideoAuditView.first_audit_pass();
		biz_Admin_home.navigate_to_last_audit(user_mobile, user_name);
		biz_Admin_Audit_VideoAuditView.last_audit_pass();
	}
}
