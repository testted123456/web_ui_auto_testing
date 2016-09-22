package com.nonobank.apps.business.student;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.nonobank.apps.page.student.Page_Improve;


public class Biz_Improve {

	public static Logger logger = LogManager.getLogger(Biz_Improve.class);

	Page_Improve page_Improve = new Page_Improve();

	
	public void borrowsInformationVerifyBus(int int_money_apply,int int_pieces_apply){
		logger.info("------------开始：借款信息检查-----------------");
		HashMap<String, String> hashMap=page_Improve.check_borrows();
		String borrowsMoney_value=hashMap.get("borrowsMoney");
		String periods_value=hashMap.get("periods");
		String perMoney_value=hashMap.get("perMoney");
		String consultingFees_value=hashMap.get("consultingFees");
		String creditRewards_value=hashMap.get("creditRewards");
		
		int int_borrowsMoney_value=Integer.parseInt(borrowsMoney_value);
		int int_periods_value=Integer.parseInt(periods_value);
	
		int int_consultingFees_value=Integer.parseInt(consultingFees_value);
		int int_creditRewards_value=Integer.parseInt(creditRewards_value);
		
		Assert.assertEquals(int_borrowsMoney_value,int_money_apply);
		Assert.assertEquals(int_periods_value,int_pieces_apply);
		double expectedMoney=(int_money_apply/int_pieces_apply)+(int_money_apply*0.99/100);
		String expectedMoneyValue=Double.toString(expectedMoney);
		Assert.assertEquals(perMoney_value,expectedMoneyValue);
		int consultingFees=(int) (int_money_apply*0.2);
		Assert.assertEquals(int_consultingFees_value, consultingFees);
		int creditRewards=(int)(int_money_apply*0.2);
		Assert.assertEquals(int_creditRewards_value, creditRewards);
		
		logger.info("------------结束：借款信息检查-----------------");
	}
	public void personalInformationBus(String email_improve, String address_improve, 
			String income_index_improve,String parentName_improve,String parentMobile_improve, 
			String counselorName_improve, String counselorMobile_improve, String friend1Name_improve,
			String friend1Mobile_improve,
			String friend2Name_improve, String friend2Mobile_improve, String friend3Name_improve,
			String friend3Mobile_improve){
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
	}
	public void uploadingBus(String file_improve){
		logger.info("------------开始：资料上传-----------------");
		page_Improve.uploadfile(file_improve);
		logger.info("------------结束：资料上传-----------------");
	}
	public void bankCardBus(String bankcardAccount_improve,
			String banksType_improve,String bankMobile_improve,String smsCode_improve){
		logger.info("------------开始：银行卡信息-----------------");
		page_Improve.input_bankCard(bankcardAccount_improve);
		page_Improve.select_banksType(banksType_improve);
		page_Improve.input_bankMobile(bankMobile_improve);
		page_Improve.click_getSmsCode();
		page_Improve.sleep(3000);
		page_Improve.input_SmsCode(smsCode_improve);
		logger.info("------------结束：银行卡信息-----------------");
	}
	public void submitBus(){
		logger.info("------------开始：提交-----------------");
		page_Improve.submit();
		logger.info("------------结束：提交-----------------");
	}
	public void getPriorityReview(){
		logger.info("------------开始：点击获得优先审核权-----------------");
		page_Improve.click_getFirstReviewPower();
		logger.info("------------结束：点击获得优先审核权-----------------");
	}
	public void cancelApply(){
		logger.info("------------开始：点击取消本次申请-----------------");
		page_Improve.click_cancelApply();
		logger.info("------------结束：点击取消本次申请-----------------");
	}
}
