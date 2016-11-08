package com.nonobank.apps.testcase.portal;

import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Register;
import com.nonobank.apps.testcase.base.BaseCase;

public class RegisterValidatePassword2Format extends BaseCase {

	Biz_Register biz_Register = new Biz_Register();

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String user_name, String password, String password2) {
		try{
			biz_Register.register_pwd_unconsistent(mobile, user_name, password, password2);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
