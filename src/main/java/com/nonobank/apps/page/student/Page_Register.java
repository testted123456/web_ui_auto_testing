package com.nonobank.apps.page.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebCommon;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.objectRepository.WebSelect;
import com.nonobank.apps.objectRepository.WebSpan;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.page.PageUtils;

/**
 * 类名称: StudentLoanPage 类描述：名校贷学生注册页面
 * 页面地址：http://www.test.nonobank.com/Student/Register
 */
public class Page_Register extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_Register.class);
	public String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// * 函数描述：跳转到注册页面
	public void navigateToRegister() {
		String url = ParseProperties.getInstance().getProperty("url");
		url = url + "/Student";
		logger.info("navigate to : " + url);
		PageUtils.navigateToURL(url);
	}

	// * 函数说明：输入用户名
	public void input_userName(String name) {
		logger.info("输入用户名....");
		WebInput input_name = objectFactory.getWebInput("用户名");
		this.username = name;
		input_name.clearAndInput(name);
	}
	
	public void click_userName(){
		logger.info("点击用户名输入框....");
		WebInput input_name = objectFactory.getWebInput("用户名");
		input_name.click();
	}

	// * 函数说明：输入qq
	public void input_qq(String qq) {
		logger.info("输入qq......");
		WebInput input_qq = objectFactory.getWebInput("QQ号");
		input_qq.clearAndInput(qq);
	}

	// * 函数说明：输入手机号
	public String input_mobile(String mobile) {
		logger.info("输入手机号码......");
		WebInput input_phone = objectFactory.getWebInput("手机号码");
		input_phone.clearAndInput(mobile);
		return mobile;
	}

	// * 函数说明：注册页面安全码
	public void input_checkCode(String checkCode) {
		logger.info("输入安全码...");
		WebInput input_check_code = objectFactory.getWebInput("安全码");
		input_check_code.clearAndInput(checkCode);
	}

	// 点击获取短信验证码
	public void click_getSmsCode() {
		logger.info("点击获取验证码......");
		WebInput get_sms_code = objectFactory.getWebInput("免费获取验证码");
		get_sms_code.click();
	}
	//获取安全码错误按钮文本信息
	public String getText_checkCodeError(){
		logger.info("获取安全码错误按钮文本信息......");
		WebInput get_sms_code = objectFactory.getWebInput("免费获取验证码");
		String checkCodeError=get_sms_code.getValue();
		return checkCodeError;
	}
	 // 输入短信验证码
	public void input_smsCode(String smsCode){
		logger.info("输入短信验证码...");
		WebInput input_sms_code = objectFactory.getWebInput("短信验证码");
		input_sms_code.clearAndInput(smsCode);
	}

	// 输入密码
	public void input_password(String password) {
		logger.info("输入密码...");
		WebInput input_password = objectFactory.getWebInput("输入密码");
		input_password.clearAndInput(password);
	}

	// 再次输入密码
	public void input_password1(String confirmassword) {
		logger.info("确认密码...");
		WebInput input_password1 = objectFactory.getWebInput("确认密码");
		input_password1.clear();
		input_password1.input(confirmassword);
	}

	// 根据下拉框的index选择省份
	public void select_provinceByIndex(int index) {
		logger.info("根据下拉框的index选择省份...");
		WebSelect select_province = objectFactory.getWebSelect("省市");
		select_province.selectByIndex(index);
	}

	// 根据输入的省份名称选择省份
	public void select_provinceByValue(String province) {
		logger.info("根据输入的省份名称选择省份...");
		WebSelect select_province = objectFactory.getWebSelect("省市");
		select_province.selectByVisibleText(province);
	}

	// 根据下拉框的index选择院校
	public void select_institutionByIndex(int index) {
		logger.info("根据下拉框的index选择院校...");
		WebSelect select_institution = objectFactory.getWebSelect("院校");
		select_institution.selectByIndex(index);
	}

	// 根据输入的院校名称选择院校
	public void select_institutionByValue(String institution) {
		logger.info("根据输入的院校名称选择院校...");
		WebSelect select_institution = objectFactory.getWebSelect("院校");
		select_institution.selectByVisibleText(institution);
	}

	// 输入校区
	public void input_schoolArea(String schoolArea) {
		logger.info("校区...");
		WebInput input_address = objectFactory.getWebInput("校区");
		input_address.clearAndInput(schoolArea);
	}

	// 根据下拉框的index选择入学年份
	public void select_intoSchoolYearByIndex(int index) {
		logger.info("根据下拉框的index选择入学年份...");
		WebSelect select_into_school_time = objectFactory.getWebSelect("入学年份");
		select_into_school_time.selectByIndex(index);
	}

	// 根据输入的年份选择入学年份
	public void select_intoSchoolYearByValue(String year) {
		logger.info("根据输入的年份选择入学年份...");
		WebSelect select_into_school_time = objectFactory.getWebSelect("入学年份");
		select_into_school_time.selectByVisibleText(year);
	}

	// 根据下拉框的index选择学历
	public void select_educationByIndex(int index) {
		logger.info("根据下拉框的index选择学历...");
		WebSelect select_education = objectFactory.getWebSelect("学历");
		select_education.selectByIndex(index);
	}

	// 根据输入的学历选择学历
	public void select_educationByValue(String education) {
		logger.info("根据输入的学历选择学历...");
		WebSelect select_education = objectFactory.getWebSelect("学历");
		select_education.selectByVisibleText(education);
	}

	// 输入学号
	public void input_studentNum(String number) {
		logger.info("输入学号...");
		WebInput input_mfd_student_sn = objectFactory.getWebInput("学号");
		input_mfd_student_sn.clearAndInput(number);
	}

	// 输入姓名
	public void input_realName(String name) {
		logger.info("输入姓名...");
		WebInput input_realname = objectFactory.getWebInput("姓名");
		input_realname.clearAndInput(name);
	}

	// 输入身份证
	public void input_idCard(String idCard) {
		logger.info("输入身份证...");
		WebInput input_idnum = objectFactory.getWebInput("身份证号");
		input_idnum.clearAndInput(idCard);
	}

	// 根据下拉框index选择专业
	public void select_majorByindex(int index) {
		logger.info("根据下拉框index选择专业...");
		WebSelect select_major = objectFactory.getWebSelect("专业");
		select_major.selectByIndex(index);
	}

	// 根据输入的专业选择专业
	public void select_majorByValue(String major) {
		logger.info("根据输入的专业选择专业...");
		WebSelect select_major = objectFactory.getWebSelect("专业");
		select_major.selectByVisibleText(major);
	}

	// 根据下拉框index选择渠道
	public void select_channelByIndex(int index) {
		logger.info("根据下拉框index选择渠道...");
		WebSelect select_my_server = objectFactory.getWebSelect("了解渠道");
		select_my_server.selectByIndex(index);
	}

	// 根据下拉框内容选择渠道
	public void select_channelByValue(String channel) {
		logger.info("根据下拉框内容选择渠道...");
		WebSelect select_my_server = objectFactory.getWebSelect("了解渠道");
		select_my_server.selectByVisibleText(channel);
	}

	// 提交
	public void click_submit() {
		logger.info("提交...");
		WebButton button_submit = objectFactory.getWebButton("提交");
		button_submit.click();
	}
	//点击语音验证码
	public void click_voiceVerificationCode(){
		logger.info("点击语音验证码...");
		WebLink click_voiceVerificationCode = objectFactory.getWebLink("语音验证码");
		click_voiceVerificationCode.click();
	}

	// 判断语音验证码按钮是否存在
	public boolean exist_voiceVerificationCode() {
		if (isElementExists("语音验证码", WebElementType.WebLink, 15)) {
			logger.info("语音验证码按钮存在...");
			return true;
		} else {
			logger.info("语音验证码按钮不存在...");
			return false;
		}
	}

	//点击登录
	public void click_login(){
		logger.info("点击登录...");
		WebLink click_login=objectFactory.getWebLink("登录");
		click_login.click();
	}
	//点击我已阅读勾选框
	public void click_checkBox(){
		logger.info("点击我已阅读勾选框...");
		WebInput click_checkBox=objectFactory.getWebInput("我已阅读勾选框");
		click_checkBox.click();
	}
	//获取新生学籍提示框内容
	public String getText_newStudentPromptContent(){
		logger.info("获取新生学籍提示框内容...");
		sleep(2000);
		if(isElementExists("新生学籍提示框", WebElementType.WebSpan, 30)){
			WebSpan getText_newStudentPromptContent=objectFactory.getWebSpan("新生学籍提示框");
			String promptContent=getText_newStudentPromptContent.getText();
			return promptContent;
		}
		return null;
	}
	//获取语音提示
	public String getText_voicePrompt(){
		logger.info("获取语音提示...");
		WebCommon getText_voicePrompt=objectFactory.getWebCommon("语音提示");
		String voicePrompt=getText_voicePrompt.getText();
		return voicePrompt;
	}
	
}
