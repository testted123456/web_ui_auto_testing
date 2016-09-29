package com.nonobank.apps.testcase.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.nonobank.apps.business.student.Biz_Register;
import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.utils.page.PageUtils;

public class NewStudentTestCase extends BaseCase {
	Biz_Register biz_register;
	public static Logger logger = LogManager.getLogger(BorrowsTestCase.class);

	@Test(dataProvider = "dataSource")
	public void test(String userName_register,String qq_register,String mobile_register,
			String checkCode_register,String password_register,String confirmPassword_register,
			String province_register,String institution_register,String schoolArea_register,
			String year_register,String education_register,String studentNumber_register,
			String realName_register,String idCard_register,String major_register,
			String channel_register,String smsCode_register) {
		logger.info("开始进行新生借款流程测试........");

		// 注册流程--注册信息
		biz_register.registerInformationBus(userName_register, qq_register, mobile_register, checkCode_register,
				smsCode_register, password_register, confirmPassword_register);
		// 注册流程--信息验证
		biz_register.informationVerifyBus(province_register, institution_register, schoolArea_register, year_register,
				education_register, studentNumber_register, realName_register, idCard_register, major_register);
		// 注册流程-了解渠道
		biz_register.channelBus(channel_register);
		// 注册流程-提交
		biz_register.submitBus();
		PageUtils.sleep(3000);
		//新生提示信息
		biz_register.newStudentPromptBus();
		
	}
}
