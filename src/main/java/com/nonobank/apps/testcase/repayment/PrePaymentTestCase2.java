package com.nonobank.apps.testcase.repayment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.nonobank.apps.business.account.Biz_Account;
import com.nonobank.apps.business.common.Biz_Common;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.recharge.Biz_User_Recharge;
import com.nonobank.apps.business.recharge.Biz_User_RechargeConfirm;
import com.nonobank.apps.business.repayment.Biz_PrePayment;
import com.nonobank.apps.business.student.Biz_Apply;
import com.nonobank.apps.business.student.Biz_Improve;
import com.nonobank.apps.business.student.Biz_Register;
import com.nonobank.apps.business.student.Biz_VideoSign;
import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.testcase.student.BorrowsTestCase;
import com.nonobank.apps.utils.page.PageUtils;

public class PrePaymentTestCase2 extends BaseCase {
	Biz_Login biz_Login;
	Biz_Portal biz_Portal;

	Biz_Register biz_register;
	Biz_Apply biz_Apply;
	Biz_Improve biz_Improve;
	Biz_VideoSign biz_VideoSign;
	Biz_Common biz_Common;
	Biz_Account biz_Account;
	Biz_PrePayment biz_PrePayment;
	Biz_User_Recharge biz_User_Recharge;
	Biz_User_RechargeConfirm biz_User_RechargeConfirm;
	// FAILED: test("VVV480sb", "77618", "18852027529", "a1b0", "it789123",
	// "it789123", "上海", "复旦大学", "张江校区", "2015", "本科", "230281199303290993",
	// "韩诺", "410181199300072622", "管理学", "微信", "0615", "消费购物", "消费购物消费购物消费购物",
	// "2000", "a1b0", "1", "12", "'838388@192.com", "上海市浦东新区张东路1158弄", "4",
	// "一二三", "13100000123", "二三四", "13100001234", "四五六", "13100001003", "五六七",
	// "13100001004", "六七八", "13100001005", "png/id.png", "6222021001128155709",
	// "中国工商银行", "18663049557", "0615")

	public static Logger logger = LogManager.getLogger(BorrowsTestCase.class);

	@Test
	public void test() {
		biz_Portal.navigate_to_login();
		biz_Login.login("18808721102", "it789123", "8888", "成功");
		// biz_Account.navigate_to_recharge();
		// biz_User_Recharge.recharge();
		// biz_User_RechargeConfirm.rechargeConfirm("10000000000", "it7891234",
		// "成功");
		// 点击用户名
		biz_Common.click_userNameBus();
		PageUtils.sleep(3000);
		biz_Account.recharge("18808721102");
		biz_PrePayment.prePaymentBus();
	}

}
