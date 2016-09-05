package com.nonobank.apps.business.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.admin.Page_Admin_Home;

public class Biz_Admin_Home {
	public static Logger logger = LogManager.getLogger(Biz_Admin_Home.class);
	
	Page_Admin_Home page_admin_home = new Page_Admin_Home();
	
	//跳转到初审页面， 初审通过
	public void navigate_to_first_audit(String mobile_no, String username){
		page_admin_home.switch_to_frameSet();
		page_admin_home.click_VideoAuditTsrial();
		page_admin_home.input_mobile(mobile_no);
		page_admin_home.click_query();
		page_admin_home.click_videoAduditView(username, "初审通过");
	}
	
    //跳转到终审页面， 终审通过
	public void navigate_to_last_audit(String mobile_no, String username){
		page_admin_home.switch_to_frameSet();
		page_admin_home.switch_to_lastAuditLists();
		page_admin_home.click_VideoAuditTsrial();
		page_admin_home.input_mobile(mobile_no);
		page_admin_home.click_query();
		page_admin_home.click_videoAduditView(username, "终审通过");
	}
}
