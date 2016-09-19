package com.nonobank.apps.testcase.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.nonobank.apps.business.student.Biz_Apply;
import com.nonobank.apps.business.student.Biz_Improve;
import com.nonobank.apps.business.student.Biz_Register;

public class BorrowsTestCase {
	
	public static Logger logger=LogManager.getLogger(BorrowsTestCase.class);
	@Test(dataProvider = "dataSource")
	public void test(String userName_register,String qq_register,String mobile_register,
			String checkCode_register,String password_register,String confirmPassword_register,
			String province_register,String institution_register,String schoolArea_register,
			String year_register,String education_register,String studentNumber_register,
			String realName_register,String idCard_register,String major_register,
			String channel_register,String smsCode_register,
			String purpose_apply,String detailPurpose_apply,String money_apply,
			String smsCode_apply,int int_productIndex_apply,int int_pieces_apply,
			String email_improve,String address_improve,String income_index_improve,
			String parentName_improve,String parentMobile_improve,String counselorName_improve,
			String counselorMobile_improve,String friend1Name_improve,
			String friend1Mobile_improve,String friend2Name_improve,String friend2Mobile_improve,
			String friend3Name_improve,String friend3Mobile_improve,String file_improve,
			String bankcardAccount_improve,String banksType_improve,String bankMobile_improve,
			String smsCode_improve
			){
		logger.info("begin to test...");
		Biz_Register biz_register=new Biz_Register();
		Biz_Apply biz_Apply=new Biz_Apply();
		Biz_Improve biz_Improve=new Biz_Improve();
		
		biz_register.registerBus(userName_register, qq_register, mobile_register, checkCode_register, 
				password_register, confirmPassword_register, province_register, institution_register, 
				schoolArea_register, year_register, education_register, studentNumber_register, 
				realName_register, idCard_register, major_register, 
				channel_register, smsCode_register);
		biz_Apply.applyBus(purpose_apply, detailPurpose_apply, money_apply, smsCode_apply, 
				int_productIndex_apply, int_pieces_apply);
		biz_Improve.improveBus(email_improve, address_improve, income_index_improve, parentName_improve,
				parentMobile_improve, counselorName_improve, counselorMobile_improve, friend1Name_improve, 
				friend1Mobile_improve, friend2Name_improve, friend2Mobile_improve, friend3Name_improve, 
				friend3Mobile_improve, file_improve, bankcardAccount_improve, banksType_improve, 
				bankMobile_improve, smsCode_improve);	
		
		
	}
	

}
