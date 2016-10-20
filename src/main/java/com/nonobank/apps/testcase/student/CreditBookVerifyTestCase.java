package com.nonobank.apps.testcase.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.nonobank.apps.business.common.Biz_Common;
import com.nonobank.apps.business.student.Biz_CreditBook;
import com.nonobank.apps.testcase.base.BaseCase;

public class CreditBookVerifyTestCase extends BaseCase {
	Biz_Common biz_Common;
	Biz_CreditBook biz_CreditBook;
	public static Logger logger = LogManager.getLogger(BorrowsTestCase.class);

	@Test(dataProvider = "dataSource")
	public void test(String creditCode_CreditBook) {
		logger.info("开始进行信用证书查询........");
		// 点击信用证书查询
		biz_Common.click_creditBookSearchBus();
		// 信用证书查询
		biz_CreditBook.creditBookVerifyBus(creditCode_CreditBook);

	}
}
