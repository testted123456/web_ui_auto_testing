package com.nonobank.apps.testcase.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.test.BaseTestCase;
import org.testng.annotations.Test;

import com.nonobank.apps.business.student.Biz_Apply;
import com.nonobank.apps.business.student.Biz_Improve;
import com.nonobank.apps.business.student.Biz_Register;
import com.nonobank.apps.utils.page.PageUtils;

public class BorrowsExcTestCase{

	public static Logger logger = LogManager.getLogger(BorrowsTestCase.class);

	@Test(dataProvider = "dataSource")
	public void test(String userName_exist_register, String userName_register, String qq_exist_register,
			String qq_register, String mobile_exist_register, String mobile_register, String checkCode_error_register,
			String checkCode_register, String smsCode_register, String password_error_register,
			String confirmPassword_error_register,String province_register,String institution_register,
			String schoolArea_register,String year_register,String education_register,
			String studentNumber_register,String realName_register,String idCard_exist_register,
			String major_register,String channel_register,String idCard_error_register,
			String idCard_register,String password_different_register,
			String confirmassword_different_register,
			String password_register,String confirmassword_register) {
		logger.info("begin to test...");
		Biz_Register biz_register = new Biz_Register();
		Biz_Apply biz_Apply = new Biz_Apply();
		Biz_Improve biz_Improve = new Biz_Improve();
		// 注册流程--注册信息异常
		biz_register.registerInformationExcBus(userName_exist_register, userName_register, qq_exist_register,
				qq_register, mobile_exist_register, mobile_register, checkCode_error_register, checkCode_register,
				smsCode_register, password_error_register, confirmPassword_error_register);
		// 注册流程--信息验证
		biz_register.informationVerifyBus(province_register, institution_register, schoolArea_register, year_register,
				education_register, studentNumber_register, realName_register, idCard_exist_register, major_register);
		//注册流程-了解渠道
		biz_register.channelBus(channel_register);
		//注册流程-提交
		biz_register.submitBus();
		PageUtils.sleep(3000);
		//提示：身份证号码已经存在
		biz_register.idCardExistBus(idCard_error_register);
		PageUtils.sleep(3000);
		biz_register.submitBus();
		//提示：身份证号码格式错误
		biz_register.idCardFormatErrorBus(idCard_register);
		PageUtils.sleep(3000);
		biz_register.submitBus();
		//提示：密码格式错误
		biz_register.passwordErrorBus(password_different_register, confirmassword_different_register);
		PageUtils.sleep(3000);
		biz_register.submitBus();
		//提示密码不一致
		biz_register.passwordDifferentBus(password_register, confirmassword_register);
		PageUtils.sleep(3000);
		biz_register.submitBus();
		//注册成功信息验证
		biz_register.registerPromptBus();
		PageUtils.sleep(10000);
		
		//申请流程--
		
	}
}
