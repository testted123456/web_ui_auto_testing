package com.nonobank.apps.page.student;

import java.sql.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLabel;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.objectRepository.WebSelect;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.data.RandomUtils;
import com.nonobank.apps.utils.db.DBUtils;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.page.PageUtils;

/**
 * 类名称: StudentLoanPage
 * 类描述：名校贷学生注册页面
 * 页面地址：http://www.test.nonobank.com/Student/Register
 */
public class Page_Register_Student extends BasePage{
	
	public static Logger logger = LogManager.getLogger(Page_Register_Student.class);
	public String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 函数名称：navigateToRegister
	 * 函数描述：跳转到注册页面
	 */
	public void navigateToRegister(){
		String url = ParseProperties.getInstance().getProperty("url");
		url = url + "/Student";
		logger.info("navigate to : " + url);
		PageUtils.navigateToURL(url);
	}
	
	//*************************************注册信息*************************************
	/**
	 * 函数说明：输入用户名，如果输入“random”则随机产生一个用户名
	 * @param name
	 * @return
	 */
	public void input_name(String name){
		logger.info("begin to input user name...");
		WebInput input_name = objectFactory.getWebInput("username");
		
		if(name.toLowerCase().equals("random")){
			name = RandomUtils.getInstance().generateUserName();
			Connection con = DBUtils.getNonoConnection();
			String sql = "select count(*) from user_info where user_name='" + name + "'";
			Object o = DBUtils.getOneObject(con, sql);
			DBUtils.closeConnection();
			
			while(!o.toString().equals("0")){
				name = RandomUtils.getInstance().generateUserName();
				sql = "select count(*) from user_info where user_name='" + name + "'";
				o = DBUtils.getOneObject(con, sql);
			}
		}
		
		this.username = name;
		input_name.clear();
		input_name.input(name);
	}
	
	/**
	 * 函数说明：输入qq，如果输入“random”则随机产生一个qq
	 * @param qq
	 */
	public void input_qq(String qq){
		
		logger.info("begin to input qq...");
		
		WebInput input_qq = objectFactory.getWebInput("qq");

		if(qq.toLowerCase().equals("random")){
			qq = RandomUtils.getInstance().generateQQNumber();
			Connection con = DBUtils.getNonoConnection();
			String sql = "select count(*) from user_info_detail where qq='" + qq + "'";
			Object o = DBUtils.getOneObject(con, sql);
			
			while(!o.toString().equals("0")){
				qq = RandomUtils.getInstance().generateQQNumber();
				sql = "select count(*) from user_info_detail where qq='" + qq + "'";
				o = DBUtils.getOneObject(con, sql);
			}
		}
			
		input_qq.clear();
		input_qq.input(qq);
	}
	
	/**
	 * 函数说明：输入手机号，如果输入“random”则随机产生一个手机号
	 * @param mobile
	 */
	public String input_mobile(String mobile){
		logger.info("begin to input mobile...");
		WebInput input_phone = objectFactory.getWebInput("phone");
		
		if(mobile.toLowerCase().equals("random")){
			mobile = RandomUtils.getInstance().generateMobilePhoneNumber();
			Connection con = DBUtils.getNonoConnection();
			String sql = "select count(*) from user_info where mobile_num='" + mobile + "'";
			Object o = DBUtils.getOneObject(con, sql);
			
			while(!o.toString().equals("0")){
				mobile = RandomUtils.getInstance().generateMobilePhoneNumber();
				sql = "select count(*) from user_info where mobile_num='" + mobile + "'";
				o = DBUtils.getOneObject(con, sql);
			}
		}

		input_phone.clear();
		input_phone.input(mobile);
		return mobile;
	}
	
	/**
	 * 函数说明：注册页面安全码，随意输入4位
	 */
	public void input_check_code(){
		if(objectFactory.isElementExists("checkCode1", WebElementType.WebInput)){
			logger.info("begin to input checkCode...");
			WebInput input_check_code = objectFactory.getWebInput("checkCode1");
			if(input_check_code.isDisplayed()){
				input_check_code.clear();
				input_check_code.input("a1b0");
			}
		}
	}
	
	/**
	 * 函数说明：获取短信验证码
	 */
	public void input_sms_code(){
		logger.info("输入短信验证码...");
		WebInput input_sms_code = objectFactory.getWebInput("getValidationCode");
		String sms_code = input_sms_code.getValue();
		
		//重新获取验证计时
		while(!sms_code.equals("重新获取验证码") && sms_code.startsWith("重新获取")){
			sleep(10000);
			input_sms_code = objectFactory.getWebInput("getValidationCode");
			sms_code = input_sms_code.getValue();
		}
		
		//点击获取验证码不生效，尝试点10次
		for(int i=0;i<10;i++){
			if(sms_code.equals("免费获取验证码") || sms_code.equals("发送失败,请重试")  || sms_code.equals("重新获取验证码")){
				input_sms_code.click();
				sleep(500);
				input_sms_code = objectFactory.getWebInput("getValidationCode");
				sms_code = input_sms_code.getValue();
				sleep(500);
			}else if(!sms_code.equals("重新获取验证码") && sms_code.startsWith("重新获取")){
				break;
			}
		}
		
		input_sms_code = objectFactory.getWebInput("validation");
		input_sms_code.clearAndInput("0615");
	}
	
	/*
	public void input_validationCode(){
		logger.info("输入短信验证码...");
		WebInput input_validationCode = objectFactory.getWebInput("getValidationCode");
		String validation = input_validationCode.getValue();
		
		//重新获取验证计时
		while(!validation.equals("重新获取验证码") && validation.startsWith("重新获取")){
			sleep(10000);
			input_validationCode = objectFactory.getWebInput("getValidationCode");
		    validation = input_validationCode.getValue();
		}
		
		if(validation.equals("免费获取验证码") || validation.equals("重新获取验证码")){
			input_validationCode.click();
			sleep(500);
//			while(true){
			for(int i=0;i<10;i++){
				input_validationCode = objectFactory.getWebInput("getValidationCode");
			    validation = input_validationCode.getValue();
			    if(validation.startsWith("重新获取")){
					break;
				}
//				点击获取验证码不生效，尝试点10次
			    if(validation.equals("免费获取验证码")){
			    	input_validationCode = objectFactory.getWebInput("getValidationCode");
					input_validationCode.click();
					sleep(500);
			    }
//			    验证码发送失败
			    if(validation.contains("发送失败")){
			    	input_validationCode = objectFactory.getWebInput("getValidationCode");
					input_validationCode.click();
					sleep(500);
				}
			}
		}
		
		WebInput input_validation = objectFactory.getWebInput("validation");
		input_validation.clearAndInput("0615");
	}*/
	
	/**
	 * 函数说明：输入密码
	 * @param password
	 */
	public void input_password(String password){
		
		logger.info("开始输入密码...");
		WebInput input_password = objectFactory.getWebInput("password");
		
		if(password.toLowerCase().equals("random")){
			password = "it789123";
		}

		input_password.clear();
		input_password.input(password);
	}
	
	/**
	 * 函数说明：再次输入密码
	 * @param password1
	 */
	public void input_password1(String password1){
		logger.info("再次密码...");
		
		if(password1.toLowerCase().equals("random")){
			password1 = "it789123";
		}

		WebInput input_password1 = objectFactory.getWebInput("password1");
		input_password1.clear();
		input_password1.input(password1);
	}
	
	/**
	 * 函数说明：关闭alert页面
	 */
	public void acceptAlert(){
		if(objectFactory.isAlertExists(10000)){
			driver.switchTo().alert().accept();
		}else{
			logger.info("Alert is not exist.");
			Assert.fail("Alert is not exist.");
		}
	}
	
	//*************************************学籍信息*************************************
	
	public boolean isProvinceExists(){
		return objectFactory.isElementExists("province", WebElementType.WebSelect);
	}
	/**
	 * 函数说明：根据下拉框的index选择省份
	 * @param index
	 */
	public void selectProvinceByIxuendex(int index){
		WebSelect select_province = objectFactory.getWebSelect("province");
		select_province.selectByIndex(index);
	}
	
	/**
	 * 函数说明：根据输入的省份名称选择省份
	 * @param province
	 */
	public void selectProvinceByValue(String province){
		WebSelect select_province = objectFactory.getWebSelect("province");
		select_province.selectByVisibleText(province);
	}
	
	/**
	 * 函数说明：根据下拉框的index选择院校
	 * @param index
	 */
	public void selectInstitutionByIndex(int index){
		WebSelect select_institution = objectFactory.getWebSelect("institution");
		select_institution.selectByIndex(index);
	}
	
	/**
	 * 函数说明：根据输入的院校名称选择院校
	 * @param institution
	 */
	public void selectInstitutionByValue(String institution){
		WebSelect select_institution = objectFactory.getWebSelect("institution");
		select_institution.selectByVisibleText(institution);
	}
	
	/**
	 * 函数说明：输入校区
	 * @param schoolArea
	 */
	public void inputSchoolArea(String schoolArea){
		WebInput input_address = objectFactory.getWebInput("address_school");
		if(null == schoolArea){
			input_address.input("test");
		}else{
			input_address.input(schoolArea);
		}
	}
	
	/**
	 * 函数说明：选择入学年份
	 * @param index
	 */
	public void selectIntoSchoolYearByIndex(int index){
		WebSelect select_into_school_time = objectFactory.getWebSelect("into_school_time");
		select_into_school_time.selectByIndex(index);
	}
	
	/**
	 * 函数说明：输入入学年份
	 * @param year
	 */
	public void selectIntoSchoolYearByValue(String year){
		WebSelect select_into_school_time = objectFactory.getWebSelect("into_school_time");
		select_into_school_time.selectByVisibleText(year);
	}
	
	/**
	 * 函数说明：根据下拉框的index选择学历
	 * @param index
	 */
	public void selectEducationByIndex(int index){
		WebSelect select_education = objectFactory.getWebSelect("education");
		select_education.selectByIndex(index);
	}
	
	/**
	 * 函数说明：输入学历
	 * @param education
	 */
	public void selectEducationByValue(String education){
		WebSelect select_education = objectFactory.getWebSelect("education");
		select_education.selectByVisibleText(education);
	}
	
	/**
	 * 函数说明：输入学号
	 * @param number
	 */
	public void inputStudentNum(String number){
		WebInput input_mfd_student_sn = objectFactory.getWebInput("mfd_student_sn");
		
		if(null != number){
			input_mfd_student_sn.input(number);
		}else{
			input_mfd_student_sn.input("test");
		}
	}
	
	/**
	 * 函数说明：输入姓名
	 * @param name
	 */
	public void inputName(String name){
		WebInput input_realname = objectFactory.getWebInput("realname");
		input_realname.input(name);
	}
	
	/**
	 * 函数说明：输入身份证
	 * @param idCard
	 */
	public void inputIdCard(String idCard){
		WebInput input_idnum = objectFactory.getWebInput("idnum");
		input_idnum.input(idCard);
	}
	
	/**
	 * 函数说明：输入身份证,省份证号根据输入年龄生成
	 * @param age
	 */
	public void inputIdCardByAge(int age){
		WebInput input_idnum = objectFactory.getWebInput("idnum");
		String idCard = RandomUtils.getInstance().generateIdCardNumberByAge(age);
		input_idnum.input(idCard);
	}
	
	/**
	 * 函数说明：根据下拉框index选择专业
	 * @param index
	 */
	public void selectMajorByindex(int index){
		WebSelect select_major = objectFactory.getWebSelect("major");
		select_major.selectByIndex(index);
	}
	
	/**
	 * 函数说明：输入专业
	 * @param major
	 */
	public void selectMajorByValue(String major){
		WebSelect select_major = objectFactory.getWebSelect("major");
		select_major.selectByVisibleText(major);
	}
	
	/**
	 * 函数说明：根据下拉框index选择渠道
	 * @param index
	 */
	public void selectChannelByIndex(int index){
		WebSelect select_my_server = objectFactory.getWebSelect("my_server");
		select_my_server.selectByIndex(index);
	}
	
	/**
	 * 函数说明：根据下拉框内容选择渠道
	 * @param channel
	 */
	public void selectChannelByValue(String channel){
		WebSelect select_my_server = objectFactory.getWebSelect("my_server");
		select_my_server.selectByVisibleText(channel);
	}
	
	/**
	 * 函数说明：提交
	 */
	public void submit(){
		WebButton button_submit = objectFactory.getWebButton("btnsubmit");
		button_submit.click();
//		sendValidationCode();
	}
	
	/**
	 * 短信验证码存在输入不正确的情况，最多尝试20次
	 */
	public void sendValidationCode(){
		/**
		 * 短信验证码存在输入不正确的情况，最多尝试20次
		 */
		for(int i=0;i<20;i++)
		{
			if(objectFactory.isAlertExists(5000)){
				logger.info("关闭alter提示框...");
				String text = driver.switchTo().alert().getText();
				logger.info(text);
				driver.switchTo().alert().accept();
				break;
			}
			
			if(objectFactory.isElementExists("close_btn", WebElementType.WebLink)){
				logger.error("关闭短信错误提示框...");
				WebLabel webLabel = objectFactory.getWebLabel("mxderror");
				String mxderror = webLabel.getText();
				
				if(mxderror.equals("短信验证码不正确")){
					WebLink link_close = objectFactory.getWebLink("close_btn");
					link_close.click();
					//重新获取短信验证码
					input_sms_code();
					//重新输入密码
					input_password("random");
					//提交
					sleep(1000);
					WebButton button_submit = objectFactory.getWebButton("btnsubmit");
					button_submit.click();
				}
			}else{
				break;
			}
		}
	}
}
