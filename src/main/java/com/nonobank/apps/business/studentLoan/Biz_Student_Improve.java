package com.nonobank.apps.business.studentLoan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.page.student.Page_Student_Improve;

public class Biz_Student_Improve {

	public static Logger logger = LogManager.getLogger(Biz_Student_Improve.class);

	Page_Student_Improve page_Student_Improve = new Page_Student_Improve();

	/**
	 * 类说明:联系人信息、省份证照片、银行卡号
	 * 
	 * @param email：邮箱
	 * @param address：家庭住址
	 * @param income_index：选择家庭收入，1:5万以下；2:5~10万；3:10~20万；4:20万以上
	 * @param parent_name：父母姓名
	 * @param parent_mobile：父母电话
	 * @param counselor_name：辅导员姓名
	 * @param counselor_mobile：辅导员电话
	 * @param friend1_name：朋友一姓名
	 * @param friend1_mobile：朋友一电话
	 * @param friend2_name：朋友二姓名
	 * @param friend2_mobile：朋友二电话
	 * @param friend3_name：朋友三姓名
	 * @param friend3_mobile：朋友三电话
	 * @param file：身份证照片路径
	 * @param bankcard_account：银行卡号
	 */
	public void inputAllInfos(String email, String address, String income_index, String parent_name,
			String parent_mobile, String counselor_name, String counselor_mobile, String friend1_name,
			String friend1_mobile, String friend2_name, String friend2_mobile, String friend3_name,
			String friend3_mobile, String file, String bankcard_account) {

		logger.info("输入联系人、银行卡...");

		page_Student_Improve.input_email(email);

		page_Student_Improve.input_address(address);

		page_Student_Improve.select_income(Double.valueOf(income_index).intValue());

		page_Student_Improve.input_parent_name(parent_name);

		page_Student_Improve.input_parent_mobile(parent_mobile);

		page_Student_Improve.input_counselor_name(counselor_name);

		page_Student_Improve.input_counselor_mobile(counselor_mobile);

		page_Student_Improve.input_friend1_name(friend1_name);

		page_Student_Improve.input_friend1_mobile(friend1_mobile);

		page_Student_Improve.input_friend2_name(friend2_name);

		page_Student_Improve.input_friend2_mobile(friend2_mobile);

		page_Student_Improve.input_friend3_name(friend3_name);

		page_Student_Improve.input_friend3_mobile(friend3_mobile);

		page_Student_Improve.uploadfile(file);

		page_Student_Improve.input_bankcard(bankcard_account);

		page_Student_Improve.submit(email);

		page_Student_Improve.navigate_to_signByVideo();
	}
}
