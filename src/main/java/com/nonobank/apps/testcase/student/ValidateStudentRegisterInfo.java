package com.nonobank.apps.testcase.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.nonobank.apps.business.studentLoan.Biz_Register_Student;
import com.nonobank.apps.testcase.base.BaseCase;

public class ValidateStudentRegisterInfo extends BaseCase {

	public static Logger logger = LogManager.getLogger(ValidateStudentRegisterInfo.class);
	
	@Test
	public void test(){
		logger.info("begin to test...");
		
		Biz_Register_Student biz_Register_Student = new Biz_Register_Student();
		biz_Register_Student.navigateToRegister();
		biz_Register_Student.validateRegisterInfo();
	}
}
