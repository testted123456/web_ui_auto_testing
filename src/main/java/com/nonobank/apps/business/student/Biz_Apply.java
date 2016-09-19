package com.nonobank.apps.business.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.nonobank.apps.page.student.Page_Apply;

public class Biz_Apply {

	public static Logger logger = LogManager.getLogger(Biz_Apply.class);
	Page_Apply page_Apply = new Page_Apply();
	//申请流程
	public void applyBus(String purpose_apply,String detailPurpose_apply,String money_apply,
			String smsCode_apply,int int_productIndex_apply,int int_pieces_apply){
		logger.info("--------------开始：借款申请----------------");
		page_Apply.select_purposeByValue(purpose_apply);
		page_Apply.input_detailPurpose(detailPurpose_apply);
		page_Apply.input_money(money_apply);
		page_Apply.input_smsCode(smsCode_apply);
		switch (int_productIndex_apply) {
		// 1:普通包，2：应急包，3：白领包，4：专科包
		case 1:
			page_Apply.select_pieceOfCommonLoan(int_pieces_apply);
			break;
		case 2:
			page_Apply.select_pieceOfEmergencyLoan(int_pieces_apply);
			break;
		case 3:
			page_Apply.select_pieceOfWhileCollarLoan(int_pieces_apply);
			break;
		case 4:
			page_Apply.select_pieceOfJuniorCollegeLoan(int_pieces_apply);
			break;
		default:
			break;
		}
		// 获取每期需还款金额
		String perMoney=page_Apply.check_perMoney();
		// 验证每期需还款金额
		
		
		page_Apply.click_submit();
		page_Apply.sleep(3000);
		// 验证借款信息
		String loanProduct=page_Apply.check_loanProduct();
		String expectProduct="大额借";
		switch(int_productIndex_apply){
		case 1:
			expectProduct="大额借";
			Assert.assertEquals(expectProduct,loanProduct);
		case 2:
			expectProduct="应急借";
			Assert.assertEquals(expectProduct,loanProduct);
		case 3:
			expectProduct="白领包";
			Assert.assertEquals(expectProduct,loanProduct);
		case 4:
			expectProduct="专科包";
			Assert.assertEquals(expectProduct,loanProduct);
		}
		String loanPeriods=page_Apply.check_loanPeriods();
		Assert.assertEquals(int_pieces_apply,loanPeriods+"期");
		String loanMoney=page_Apply.check_loanMoney();
		Assert.assertEquals(money_apply,loanMoney+"元");
		// 点击下一步
		page_Apply.click_goNext();
		logger.info("--------------结束：借款申请----------------");
	}

}
