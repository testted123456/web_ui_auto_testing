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
		String text = page_Admin_Audit_Video_AuditView.accept_alert();
		if(text.contains("确定通过") && text.contains("初审么")){
			text = page_Admin_Audit_Video_AuditView.accept_alert();
			if(!text.contains("操作成功")){
				Assert.fail("初审失败...");
			}
		}
		page_Admin_Audit_Video_AuditView.close_this_page();
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
		String text = page_Admin_Audit_Video_AuditView.accept_alert();
		if(text.contains("确定通过") && text.contains("终审么")){
			text = page_Admin_Audit_Video_AuditView.accept_alert();
			if(!text.contains("操作成功")){
				Assert.fail("初审失败...");
			}
		}
		page_Admin_Audit_Video_AuditView.close_this_page();
	}
}
