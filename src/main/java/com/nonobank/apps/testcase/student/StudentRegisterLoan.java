package com.nonobank.apps.testcase.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.admin.Biz_Admin_Login;
import com.nonobank.apps.business.admin.Biz_Admin_Home;
import com.nonobank.apps.business.portal.Biz_Logout;
import com.nonobank.apps.business.studentLoan.Biz_Student_Improve;
import com.nonobank.apps.business.studentLoan.Biz_Student_Apply;
import com.nonobank.apps.business.studentLoan.Biz_Register_Student;
import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.utils.data.UserInfoUtils;

public class StudentRegisterLoan extends BaseCase {
	
	public static Logger logger = LogManager.getLogger(StudentRegisterLoan.class);
	
	Biz_Register_Student biz_Register_Student;
	Biz_Student_Apply biz_Student_Apply;
	Biz_Student_Improve biz_Student_Improve;
	Biz_Admin_Login biz_Admin_Login;
	Biz_Admin_Home biz_Admin_home;
	Biz_Logout biz_Logout;
	
	@Test(dataProvider="dataSource")
	public void test(String name, String qq, String mobile, String password,String password1,
			String province, String school, String schoolArea, String year,
			String eduCode, String studentNo, String studentName, String idCard, String major,
			String purpose, String detailPurpose, String money, String productIndex, String pieces,
			String email, String address, String income_index,
			String parent_name, String parent_mobile,
			String counselor_name, String counselor_mobile,
			String friend1_name, String friend1_mobile,
			String friend2_name, String friend2_mobile,
			String friend3_name, String friend3_mobile,
			String file, String bankcard_account,
			String admin_name,String admin_password){
		logger.info("begin to test Student register and loan...");
		
		biz_Register_Student.navigateToRegister();
		String user_mobile = biz_Register_Student.registerInfo(name, qq, mobile, password, password);
		biz_Register_Student.validationInfo(province, school, schoolArea, year, eduCode, studentNo, studentName, idCard, major);
		biz_Student_Apply.loan(purpose, detailPurpose, money, productIndex, pieces);
		biz_Student_Improve.inputAllInfos(email, address, income_index, parent_name, parent_mobile, counselor_name, counselor_mobile, friend1_name, friend1_mobile, friend2_name, friend2_mobile, friend3_name, friend3_mobile, file, bankcard_account);
		biz_Admin_Login.login(admin_name, admin_password);
		String user_name = UserInfoUtils.getUsername(user_mobile);
		biz_Admin_home.navigate_to_first_audit(user_mobile, user_name);
		biz_Logout.logout();
	}
}
