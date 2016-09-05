package com.nonobank.apps.business.studentLoan;

import java.sql.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;

import com.nonobank.apps.page.student.Page_Register_Student;
import com.nonobank.apps.utils.db.DBUtils;
import com.nonobank.apps.utils.page.PageUtils;

public class Biz_Register_Student {

	public static Logger logger = LogManager.getLogger(Biz_Register_Student.class);

	Page_Register_Student page_Register_Student = new Page_Register_Student();

	public void navigateToRegister() {
		logger.info("跳转名校贷注册页面...");
		page_Register_Student.navigateToRegister();
	}

	/**
	 * 函数说明:验证输入的注册信息，用户名、手机号码、qq不能重复
	 */
	public void validateRegisterInfo() {
		logger.info("校验用户名、手机号码、qq...");

		Connection con = DBUtils.getNonoConnection();

		String sql = "SELECT ui.user_name,ui.mobile_num,uid.qq " + "FROM user_info ui, user_info_detail uid "
				+ "WHERE ui.id=uid.user_id AND " + "ui.user_name IS NOT NULL AND " + "ui.mobile_num IS NOT NULL AND "
				+ "uid.qq IS NOT NULL AND qq <> '' limit 1";

		Object[] objs = DBUtils.getOneLine(con, sql);
		String username = objs[0].toString();
		String qq = objs[2].toString();
		String mobilephone = objs[1].toString();

		// 输入相同的用户名
		page_Register_Student.input_name(username);

		// 模拟tab键
		PageUtils.sendKeys(Keys.TAB);

		// 取消alert窗口
		page_Register_Student.acceptAlert();

		// 输入不同的用户名
		page_Register_Student.input_name("random");

		// 输入相同qq号
		page_Register_Student.input_qq(qq);

		// 模拟tab键
		PageUtils.sendKeys(Keys.TAB);

		// 取消alert窗口
		page_Register_Student.acceptAlert();

		// 输入不同的qq
		page_Register_Student.input_qq("random");

		// 输入相同电话
		page_Register_Student.input_mobile(mobilephone);

		// 模拟tab键
		PageUtils.sendKeys(Keys.TAB);

		// 取消alert窗口
		page_Register_Student.acceptAlert();

		// 输入不同的mobile
		page_Register_Student.input_mobile("random");
	}

	/**
	 * 函数说明:注册及信息验证
	 * 
	 * @param name:如果传入“random”，则自动取随机数
	 * @param qq:如果传入“random”，则自动取随机数
	 * @param mobile:如果传入“random”，则自动取随机数
	 * @param password:如果传入“random”，则自动取“it789123”
	 */
	public String registerInfo(String name, String qq, String mobile, String password, String password1) {
		logger.info("输入学生信息、qq、电话号码、密码...");
		// 用户名
		page_Register_Student.input_name(name);
		// qq号
		page_Register_Student.input_qq(qq);
		// 电话
		String user_mobile = page_Register_Student.input_mobile(mobile);
		// 安全码
		page_Register_Student.input_check_code();
		// 获取验证码
		page_Register_Student.input_sms_code();
		// 输入密码
		page_Register_Student.input_password(password);
		// 再次输入密码
		page_Register_Student.input_password1(password1);

		return user_mobile;
	}

	/**
	 * 函数说明:学籍信息
	 * 
	 * @param province：省份
	 * @param school：学校
	 * @param schoolArea：学区
	 * @param year：入学时间
	 * @param eduCode：学历
	 * @param studentNo：学号
	 * @param studentName：姓名
	 * @param idCard：身份证号码，如果输入“random”，则系统自动生成
	 * @param major：专业
	 */
	public void validationInfo(String province, String school, String schoolArea, String year, String eduCode,
			String studentNo, String studentName, String idCard, String major) {

		if (page_Register_Student.isProvinceExists()) {// 信息验证
			logger.info("输入学籍信息...");
			page_Register_Student.selectProvinceByValue(province);
			page_Register_Student.selectInstitutionByValue(school);
			page_Register_Student.inputSchoolArea(schoolArea);
			page_Register_Student.selectIntoSchoolYearByValue(year);
			page_Register_Student.selectEducationByValue(eduCode);
			page_Register_Student.inputStudentNum(studentNo);
			page_Register_Student.inputName(studentName);
			page_Register_Student.inputIdCardByAge(20);
			page_Register_Student.selectMajorByValue(major);
			page_Register_Student.selectChannelByIndex(1);
			page_Register_Student.submit();
			page_Register_Student.sendValidationCode();
		}
	}
}
