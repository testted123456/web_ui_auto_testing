package com.nonobank.apps.business.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import com.nonobank.apps.page.admin.Page_Audit_Video_AuditView;

public class Biz_Audit_VideoAuditView {
	public static Logger logger = LogManager.getLogger(Biz_Audit_VideoAuditView.class);
	
	Page_Audit_Video_AuditView page_Admin_Audit_Video_AuditView = new Page_Audit_Video_AuditView();

	//初审通过
	public void first_audit_pass(){
		page_Admin_Audit_Video_AuditView.audit("初审通过");
		page_Admin_Audit_Video_AuditView.sleep(5000);
		page_Admin_Audit_Video_AuditView.select_CA("CA01:初审无问题");
		page_Admin_Audit_Video_AuditView.sleep(5000);
		page_Admin_Audit_Video_AuditView.click_submit();
		page_Admin_Audit_Video_AuditView.sleep(3000);
		//提示框
		String alertPrompt=page_Admin_Audit_Video_AuditView.getAlertText();
		page_Admin_Audit_Video_AuditView.sleep(2000);
		Assert.assertEquals(alertPrompt, "操作成功！");
		page_Admin_Audit_Video_AuditView.sleep(2000);
		page_Admin_Audit_Video_AuditView.closeAlert();
	}
	
	//初审拒绝
	public void first_audit_reject(){
		page_Admin_Audit_Video_AuditView.audit("初审拒绝");
	}
	
	//初审回退
	public void first_audit_return(){
		page_Admin_Audit_Video_AuditView.audit("初审回退");
	}
	
	//初审取消
	public void first_audit_cancel(){
		page_Admin_Audit_Video_AuditView.audit("初审取消");
	}
	
	//终审通过
	public void last_audit_pass(){
		page_Admin_Audit_Video_AuditView.audit("终审通过");
		page_Admin_Audit_Video_AuditView.sleep(5000);
		page_Admin_Audit_Video_AuditView.select_CA("CA01:终审无问题");
		page_Admin_Audit_Video_AuditView.sleep(5000);
		page_Admin_Audit_Video_AuditView.click_submit();
		page_Admin_Audit_Video_AuditView.sleep(3000);
		//提示框
		String alertPrompt=page_Admin_Audit_Video_AuditView.getAlertText();
		page_Admin_Audit_Video_AuditView.sleep(2000);
		Assert.assertEquals(alertPrompt, "操作成功！");
		page_Admin_Audit_Video_AuditView.sleep(2000);
		page_Admin_Audit_Video_AuditView.closeAlert();
	}

}
