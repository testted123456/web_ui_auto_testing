package com.nonobank.apps.business.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.student.Page_Register;

public class Biz_Register{

	public static Logger logger = LogManager.getLogger(Biz_Register.class);
	Page_Register page_Register = new Page_Register();
	//注册流程
	public void registerBus(String userName_register,String qq_register,String mobile_register,
			String checkCode_register,String password_register,String confirmassword_register,
			String province_register,String institution_register,String schoolArea_register,
			String year_register,String education_register,String StudentNumber_register,
			String realName_register,String idCard_register,
			String major_register,String channel_register,String smsCode_register){
		// 跳转到登录页面
		page_Register.navigateToRegister();
		logger.info("--------------开始：注册信息----------------");
		page_Register.input_userName(userName_register);
		page_Register.input_qq(qq_register);
		page_Register.input_mobile(mobile_register);
		page_Register.input_checkCode(checkCode_register);
		page_Register.sleep(2000);
		// 点击获取验证码
		page_Register.click_getSmsCode();
		page_Register.sleep(3000);
		//判断语音验证码是否存在
		page_Register.exist_voiceVerificationCode();
		page_Register.input_smsCode(smsCode_register);
		page_Register.input_password(password_register);
		page_Register.input_password1(confirmassword_register);
		logger.info("--------------结束：注册信息----------------");
		logger.info("--------------开始：信息验证----------------");
		page_Register.select_provinceByValue(province_register);
		page_Register.select_institutionByValue(institution_register);
		page_Register.input_schoolArea(schoolArea_register);
		page_Register.select_intoSchoolYearByValue(year_register);
		page_Register.select_educationByValue(education_register);
		page_Register.input_studentNum(StudentNumber_register);
		page_Register.input_realName(realName_register);
		page_Register.input_idCard(idCard_register);
		page_Register.select_majorByValue(major_register);
		logger.info("--------------结束：信息验证----------------");
		logger.info("--------------开始：从哪里了解到名校贷----------------");
		page_Register.select_channelByValue(channel_register);
		logger.info("--------------结束：从哪里了解到名校贷----------------");
		page_Register.click_submit();

	}
}
