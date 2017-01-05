package com.nonobank.apps.business.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.nonobank.apps.page.admin.Page_DfDebt;
import com.nonobank.apps.utils.data.Assertion;
import com.nonobank.apps.utils.db.DBUtils;
import com.nonobank.apps.utils.webintegration.Info;

@Info(name = "Biz_Debt", desc = "债务页面", dependency = "com.nonobank.apps.business.admin.Biz_Home", isDisabled = true)
public class Biz_DfDebt {
	Page_DfDebt page_DfDebt = new Page_DfDebt();
	public static String bo_id = "";

	public void searchDfdDebtList() {
		page_DfDebt.click_dfdDebtList();
		page_DfDebt.select_targetFpid("诺诺精选机构特别期");
	}

	public void debt() {

		List<WebElement> webs = page_DfDebt.getObjectFactory()
				.getWebElements(By.xpath("//table[@id='table_1']/tbody//tr[@align='center']//td[8]//a"));
		for (int i = 0; i < webs.size(); i++) {
			if (webs.get(i).getText().equals("债转")) {
				WebElement webElement = page_DfDebt.getObjectFactory()
						.getWebElement(By.xpath("//table[@id='table_1']/tbody//tr[" + (i + 2) + "]//td[1]"));
				bo_id = webElement.getText();
				break;
			}
		}
		// if (page_DfDebt.isAlertExists(6000)) {
		// page_DfDebt.acceptAlert();
		// }
	}

	public void validate_num() {
		String sql = "SELECT id from invt_borrows_accept_task limit 10";
		List<Object> ids = DBUtils.getMulLineValues("nono", sql);
		Assertion.assertEquals(true, ids.size() > 0, Biz_DfDebt.class, "invt_borrows_accept_task表插入数据执行");
		// for (Object id : ids) {
		// System.out.println("*******************id=" + id);
		// }
	}
}
