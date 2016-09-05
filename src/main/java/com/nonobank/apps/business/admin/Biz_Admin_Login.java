package com.nonobank.apps.business.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.page.admin.Page_Admin_Login;

public class Biz_Admin_Login {

	public static Logger logger = LogManager.getLogger(Biz_Admin_Login.class);
	
	Page_Admin_Login page_Admin_Login = new Page_Admin_Login();
	
	public void login(String username, String password){
		page_Admin_Login.nagivate_to_adminpage();
		page_Admin_Login.input_username(username);
		page_Admin_Login.input_password(password);
		page_Admin_Login.login();
	}
}
