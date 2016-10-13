package com.nonobank.apps.business.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import com.nonobank.apps.page.student.Page_Register;
import com.nonobank.apps.utils.page.PageUtils;

public class Biz_Register {

	public static Logger logger = LogManager.getLogger(Biz_Register.class);
	Page_Register page_Register = new Page_Register();
	// 注册流程--注册信息
	public void registerInformationBus(String userName_register,String qq_register,String mobile_register,
			String checkCode_register,String smsCode_register,String password_register,
			String confirmPassword_register) {
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
		// 判断语音验证码是否存在
		page_Register.exist_voiceVerificationCode();
		page_Register.input_smsCode(smsCode_register);
		page_Register.input_password(password_register);
		page_Register.input_password1(confirmPassword_register);
		logger.info("--------------结束：注册信息----------------");
	}
	// 注册流程--注册信息异常
	public void registerInformationExcBus(String userName_exist_register,String userName_register,
			String qq_exist_register,String qq_register,
			String mobile_exist_register,String mobile_register,
			String checkCode_error_register,String checkCode_register,
			String smsCode_register,String password_register,
			String confirmPassword_register) {
		// 跳转到登录页面
		page_Register.navigateToRegister();
		logger.info("--------------开始：注册信息----------------");
		logger.info("-----开始：用户名检查------");
		page_Register.input_userName(userName_exist_register);
		PageUtils.sleep(3000);
		PageUtils.sendKeys(Keys.TAB);
		PageUtils.sleep(5000);
		String userNamePrompt=page_Register.getAlertText();
		Assert.assertEquals(userNamePrompt,"用户名已被注册");
		PageUtils.sleep(3000);
		page_Register.closeAlert();
		PageUtils.sleep(3000);
		page_Register.input_userName(userName_register);
		logger.info("-----结束：用户名检查------");
		logger.info("-----开始：QQ检查------");
		page_Register.input_qq(qq_exist_register);
		PageUtils.sleep(3000);
		PageUtils.sendKeys(Keys.TAB);
		PageUtils.sleep(3000);
		String qqPrompt=page_Register.getAlertText();
		Assert.assertEquals(qqPrompt, "QQ号已被注册");
		page_Register.closeAlert();
		PageUtils.sleep(3000);
		page_Register.input_qq(qq_register);
		PageUtils.sleep(3000);
		logger.info("-----结束：QQ检查------");
		logger.info("-----开始：手机号检查------");
		page_Register.input_mobile(mobile_exist_register);
		PageUtils.sendKeys(Keys.TAB);
		PageUtils.sleep(3000);
		String mobliePrompt=page_Register.getAlertText();
		Assert.assertEquals(mobliePrompt, "手机号已被注册");
		page_Register.closeAlert();
		PageUtils.sleep(3000);
		page_Register.input_mobile(mobile_register);
		logger.info("-----结束：手机号检查------");
		logger.info("-----开始：安全码检查------");
		page_Register.input_checkCode(checkCode_error_register);
		page_Register.sleep(2000);
		page_Register.click_getSmsCode();
		page_Register.sleep(3000);
		String checkCodeError=page_Register.getText_checkCodeError();
		Assert.assertEquals(checkCodeError, "安全码输入错误");
		page_Register.sleep(3000);
		page_Register.input_checkCode(checkCode_register);
		page_Register.sleep(2000);
		page_Register.click_getSmsCode();
		page_Register.sleep(3000);
		// 判断语音验证码是否存在
		page_Register.exist_voiceVerificationCode();
		logger.info("-----结束：安全码检查------");
		page_Register.input_smsCode(smsCode_register);
		page_Register.input_password(password_register);
		page_Register.input_password1(confirmPassword_register);
		logger.info("--------------结束：注册信息----------------");
	}
	//注册流程--信息验证
	public void informationVerifyBus(String province_register,String institution_register,
			String schoolArea_register,String year_register,String education_register,
			String studentNumber_register,String realName_register,String idCard_register,
			String major_register
			){
		logger.info("--------------开始：信息验证----------------");
		page_Register.select_provinceByValue(province_register);
		page_Register.sleep(1000);
		page_Register.select_institutionByValue(institution_register);
		page_Register.sleep(1000);
		page_Register.input_schoolArea(schoolArea_register);
		page_Register.sleep(1000);
		page_Register.select_intoSchoolYearByValue(year_register);
		page_Register.sleep(1000);
		page_Register.select_educationByValue(education_register);
		page_Register.sleep(1000);
		page_Register.input_studentNum(studentNumber_register);
		page_Register.sleep(1000);
		page_Register.input_realName(realName_register);
		page_Register.sleep(1000);
		page_Register.input_idCard(idCard_register);
		page_Register.sleep(1000);
		page_Register.select_majorByValue(major_register);
		logger.info("--------------结束：信息验证----------------");
	}
	//注册流程-了解渠道
	public void channelBus(String channel_register){
		logger.info("--------------开始：从哪里了解到名校贷----------------");
		page_Register.select_channelByValue(channel_register);
		logger.info("--------------结束：从哪里了解到名校贷----------------");
	}
	//注册流程-提交
	public void submitBus(){
		page_Register.click_submit();
	}
	//身份证号码已经存在
	public void idCardExistBus(String idCard_register){
		String idCardPrompt=page_Register.getAlertText();
		Assert.assertEquals(idCardPrompt, "身份证已被注册");
		page_Register.closeAlert();
		PageUtils.sleep(3000);
		page_Register.input_idCard(idCard_register);
	}
	//身份证号码格式不正确
	public void idCardFormatErrorBus(String idCard_register){
		String idCardPrompt=page_Register.getAlertText();
		Assert.assertEquals(idCardPrompt, "身份证格式不正确，请重新输入");
		page_Register.closeAlert();
		PageUtils.sleep(3000);
		page_Register.input_idCard(idCard_register);		
	}
	//密码不符合要求
	public void passwordErrorBus(String password_register,String confirmassword_register){
		String passwordPrompt=page_Register.getAlertText();
		Assert.assertEquals(passwordPrompt, "密码必须为6-16的字母和数字组合");
		page_Register.closeAlert();
		PageUtils.sleep(3000);
		page_Register.input_password(password_register);
		page_Register.input_password1(confirmassword_register);
	}
	//两次密码不一致
	public void passwordDifferentBus(String password_register,String confirmassword_register){
		String passwordPrompt=page_Register.getAlertText();
		Assert.assertEquals(passwordPrompt, "两次输入的密码不一致！");
		page_Register.closeAlert();
		PageUtils.sleep(3000);
		page_Register.input_password(password_register);
		page_Register.input_password1(confirmassword_register);
	}
	//未勾选我已阅读勾选框
	public void checkBoxErrorBus(){
		page_Register.click_checkBox();
		page_Register.click_submit();
		page_Register.sleep(2000);
		String checkBoxPrompt=page_Register.getAlertText();
		Assert.assertEquals(checkBoxPrompt, "请确认您已阅读并已同意诺诺镑客《服务协议》！");
		page_Register.closeAlert();
		PageUtils.sleep(3000);
		page_Register.click_checkBox();
	}
	//注册成功提示框关闭
	public void registerPromptBus(){
		if(!page_Register.isAlertExists(10000)){
			logger.error("注册成功提示框不存在...");
			Assert.fail();
		}
		String registerPromptBus=page_Register.getAlertText();
		Assert.assertEquals(registerPromptBus, "诺诺提醒：您已注册成功；信息验证通过");
		page_Register.closeAlert();
	}
	//登录流程
	public void loginBus() {
		logger.info("--------------开始：点击登录----------------");
		page_Register.click_login();
		logger.info("--------------结束：点击登录----------------");
	}
	//新生提示信息
	public void newStudentPromptBus(){
		logger.info("--------------开始：新生提示信息----------------");
		String newStudentPrompt=page_Register.getText_newStudentPromptContent();
		Assert.assertEquals(newStudentPrompt, "今年入学的新童鞋，您已注册成功！新生申请借款，请使用名校贷app哦~");
		logger.info("--------------结束：新生提示信息----------------");
	}
}
