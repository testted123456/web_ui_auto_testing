package com.nonobank.apps.business.student;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.nonobank.apps.business.admin.Biz_Debt;
import com.nonobank.apps.page.student.Page_VideoSign;
import com.nonobank.apps.utils.data.Assertion;
import com.nonobank.apps.utils.db.DBUtils;

public class Biz_VideoSign {
	public static Logger logger = LogManager.getLogger(Biz_VideoSign.class);

	Page_VideoSign page_VideoSign = new Page_VideoSign();

	public void videoSignInformationCheckBus(String realName_register, String idCard_register, int int_money_apply) {
		logger.info("--------------开始：视频签约信息检查----------------");
		String realName = page_VideoSign.getText_name();
		String idCard_origin = page_VideoSign.getText_idCard();
		String idCard = idCard_origin.replaceAll(" ", "");
		Assert.assertEquals(realName_register, realName);
		Assert.assertEquals(idCard_register, idCard);
		logger.info("--------------结束：视频签约信息检查----------------");
	}

	public void checkVideoSignSuccessBus() {
		logger.info("---------------开始：检查视频签约是否完成------------------");
		if (!page_VideoSign.exist_getFirstAuditRight()) {
			Assert.fail("(获得优先审核权—视频签约完成)按钮存在.....");
		}
		logger.info("---------------结束：检查视频签约是否完成------------------");
	}

	public void checkLastAuditPassBus(String realName) {
		logger.info("---------------开始：检查终审是否通过------------------");
		String lastAuditPassPrompt = page_VideoSign.getText_lastViewPrompt();
		Assert.assertEquals(lastAuditPassPrompt, "恭喜" + realName + "同学，您的视频签约认证已通过审核！");
		logger.info("---------------结束：检查终审是否通过------------------");
	}

	public void checkSuccess(String mobile) {
		String sql = "SELECT id from invt_debt_sale_task where mobile_num=" + mobile;
		String ibat_sql = "SELECT id from invt_borrows_accept_task where user_id =(" + sql + ")";
		String ba_sql = "SELECT id from borrows_accept where is_pay=0 and user_id =(" + sql + ")";
		try {
			List<Object> ibat_ids = DBUtils.getMulLineValues("nono", ibat_sql);
			List<Object> ba_ids = DBUtils.getMulLineValues("nono", ba_sql);
			Assertion.assertEquals(ibat_ids.size() > 0, true, Biz_VideoSign.class, "校验invt_borrows_accept_task表中插入数据");
			Assertion.assertEquals(ba_ids.size() > 0, true, Biz_VideoSign.class, "校验invt_borrows_accept_task表中插入数据");
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

}
