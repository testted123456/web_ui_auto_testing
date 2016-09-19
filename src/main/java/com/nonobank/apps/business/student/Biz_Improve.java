package com.nonobank.apps.business.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.page.student.Page_Improve;


public class Biz_Improve {

	public static Logger logger = LogManager.getLogger(Biz_Improve.class);

	Page_Improve page_Improve = new Page_Improve();

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
	public void improveBus(String email_improve, String address_improve, String income_index_improve,
			String parentName_improve,String parentMobile_improve, String counselorName_improve, 
			String counselorMobile_improve, String friend1Name_improve,String friend1Mobile_improve,
			String friend2Name_improve, String friend2Mobile_improve, String friend3Name_improve,
			String friend3Mobile_improve, String file_improve, String bankcardAccount_improve,
			String banksType_improve,String bankMobile_improve,String smsCode_improve) {

		logger.info("------------开始：联系人-----------------");
		page_Improve.input_email(email_improve);
		page_Improve.input_address(address_improve);
		page_Improve.select_income(Double.valueOf(income_index_improve).intValue());
		page_Improve.input_parentName(parentName_improve);
		page_Improve.input_parentMobile(parentMobile_improve);
		page_Improve.input_counselorName(counselorName_improve);
		page_Improve.input_counselorMobile(counselorMobile_improve);
		page_Improve.input_friend1Name(friend1Name_improve);
		page_Improve.input_friend1Mobile(friend1Mobile_improve);
		page_Improve.input_friend2Name(friend2Name_improve);
		page_Improve.input_friend2Mobile(friend2Mobile_improve);
		page_Improve.input_friend3Name(friend3Name_improve);
		page_Improve.input_friend3Mobile(friend3Mobile_improve);
		logger.info("------------结束：联系人-----------------");
		logger.info("------------开始：上传图片-----------------");
		page_Improve.uploadfile(file_improve);
		logger.info("------------结束：上传图片-----------------");
		logger.info("------------开始：银行卡信息-----------------");
		page_Improve.input_bankCard(bankcardAccount_improve);
		page_Improve.select_banksType(banksType_improve);
		page_Improve.input_bankMobile(bankMobile_improve);
		page_Improve.click_getSmsCode();
		page_Improve.sleep(3000);
		page_Improve.input_SmsCode(smsCode_improve);
		logger.info("------------结束：银行卡信息-----------------");
		page_Improve.submit();

	}
}
