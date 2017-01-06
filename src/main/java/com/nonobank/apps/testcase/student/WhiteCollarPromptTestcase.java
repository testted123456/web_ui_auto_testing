package com.nonobank.apps.testcase.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.nonobank.apps.business.student.Biz_Apply;
import com.nonobank.apps.business.student.Biz_Register;
import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.utils.page.PageUtils;

public class WhiteCollarPromptTestcase extends BaseCase {
	Biz_Register biz_register;
	Biz_Apply biz_Apply;
	public static Logger logger = LogManager.getLogger(BorrowsTestCase.class);

	//白领贷
	@Test(dataProvider = "dataSource")
	public void test(String userName_register, String qq_register, String mobile_register, String checkCode_register,
			String password_register, String confirmPassword_register, String province_register,
			String institution_register, String schoolArea_register, String year_register, String education_register,
			String studentNumber_register, String realName_register, String idCard_register, String major_register,
			String channel_register, String smsCode_register, String purpose_apply, String detailPurpose_apply,
			String money_apply, String smsCode_apply, String productIndex_apply, String pieces_apply) {
		logger.info("开始进行白领包提示........");
		int int_money_apply = Integer.parseInt(money_apply);
		int int_productIndex_apply = Integer.parseInt(productIndex_apply);
		int int_pieces_apply = Integer.parseInt(pieces_apply);

		// 注册流程--注册信息
		biz_register.registerInformationBus(userName_register, qq_register, mobile_register, checkCode_register,
				smsCode_register, password_register, confirmPassword_register);
		// 注册流程--信息验证
		biz_register.informationVerifyBus(province_register, institution_register, schoolArea_register, year_register,
				education_register, studentNumber_register, realName_register, idCard_register, major_register);
		// 注册流程-了解渠道
		biz_register.channelBus(channel_register);
		// 注册流程-提交
		biz_register.submitBus();
		PageUtils.sleep(10000);
		// 注册成功信息验证
		biz_register.registerPromptBus();
		PageUtils.sleep(10000);

		// 申请流程--借款用途、金额
		biz_Apply.borrowsUseBus(purpose_apply, detailPurpose_apply, int_money_apply, smsCode_apply);
		// 申请流程--借款产品
		biz_Apply.selectBorrowsProductBus(int_money_apply, int_productIndex_apply, int_pieces_apply);
		// 申请流程--提交
		biz_Apply.submitBus();
		biz_Apply.submitAfterVerify(int_productIndex_apply, int_pieces_apply, int_money_apply);
		PageUtils.sleep(5000);
		// 白领包提示
		biz_Apply.whiteCollarPromptBus();
	}
}
