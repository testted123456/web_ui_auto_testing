package com.nonobank.apps.business.admin;

import com.nonobank.apps.page.admin.Page_Debt;

public class Biz_Debt {
	Page_Debt page_Debt = new Page_Debt();

	public void debt(String username, String code, String targetFpid, String targetVip) {
		page_Debt.input_username(username);
		page_Debt.select_fpId(code);
		page_Debt.click_query();
		page_Debt.select_targetFpid(targetFpid);
		page_Debt.select_targetVip(targetVip);
	}

	public void debt(String search_username) {
		
		page_Debt.input_username(search_username);
		page_Debt.click_query();

		
		
	}
}
