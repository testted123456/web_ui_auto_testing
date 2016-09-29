package com.nonobank.apps.business.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.nonobank.apps.page.student.Page_Apply;
import com.nonobank.apps.utils.page.PageUtils;

public class Biz_Apply {

	public static Logger logger = LogManager.getLogger(Biz_Apply.class);
	Page_Apply page_Apply = new Page_Apply();
	
	public void borrowsUseBus(String purpose_apply,String detailPurpose_apply, int int_money_apply,
			String smsCode_apply){
		logger.info("--------------开始：借款用途、金额----------------");
		page_Apply.select_purposeByValue(purpose_apply);
		page_Apply.input_detailPurpose(detailPurpose_apply);
		page_Apply.input_money(int_money_apply);
		if(page_Apply.isExist_smsCode()){
			page_Apply.input_smsCode(smsCode_apply);
		}
		logger.info("--------------结束：借款用途、金额----------------");
	}
	public void selectBorrowsProductBus(int int_money_apply,int int_productIndex_apply,int int_pieces_apply){
		logger.info("--------------开始：借款产品----------------");
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
		double double_money_apply=int_money_apply;
		double double_pieces_apply=int_pieces_apply;
		double double_expectedMoney=(double_money_apply/double_pieces_apply)+(double_money_apply*0.99/100);
		double double_perMoney=Double.parseDouble(perMoney);
		// 验证每期需还款金额
		double d_value=Math.abs(double_expectedMoney-double_perMoney);
		if(d_value<=0.01){
			logger.info("每期还款金额与预期值一致");
		}else{
			Assert.fail("每期还款金额与预期值不一致，期望值为："+double_expectedMoney+"实际值为："+double_perMoney);
		}
		logger.info("--------------结束：借款产品----------------");
	}
	public void interestrateCutBus(String interestCutCode){
		logger.info("--------------开始：优惠券----------------");
		page_Apply.click_addInterestCut();
		page_Apply.sleep(2000);
		page_Apply.input_InterestCutCode(interestCutCode);
		page_Apply.sleep(2000);
		page_Apply.click_enter();
		logger.info("--------------开始：优惠券----------------");
	}
	public void submitBus(){
		logger.info("--------------开始：提交----------------");
		page_Apply.click_submit();
		page_Apply.sleep(3000);
		logger.info("--------------结束：提交----------------");
	}
	//提交后借款信息验证
	public void submitAfterVerify(int int_productIndex_apply,int int_pieces_apply,int int_money_apply){
		logger.info("--------------开始：提交后借款信息验证----------------");
		// 验证借款信息
		String loanProduct=page_Apply.check_loanProduct();
		String expectProduct="名校贷";
		switch(int_productIndex_apply){
		case 1:
			expectProduct="名校贷";
			Assert.assertEquals(expectProduct,loanProduct);
			break;
		case 2:
			expectProduct="名校贷应急包";
			Assert.assertEquals(expectProduct,loanProduct);
			break;
		case 3:
			expectProduct="白领包";
			Assert.assertEquals(expectProduct,loanProduct);
			break;
		case 4:
			expectProduct="专科包";
			Assert.assertEquals(expectProduct,loanProduct);
			break;
		}
		String loanPeriods=page_Apply.check_loanPeriods();
		int intloanPeriods=Integer.parseInt(loanPeriods);
		Assert.assertEquals(int_pieces_apply,intloanPeriods);
		String loanMoney=page_Apply.check_loanMoney();
		int intloanMoney=Integer.parseInt(loanMoney);
		Assert.assertEquals(int_money_apply,intloanMoney);
		// 点击下一步
		page_Apply.click_goNext();
		logger.info("--------------结束：提交后借款信息验证----------------");
	}
	//镑客码验证提示框
	public void bankCodeVerifyBus(){
		if(page_Apply.isExist_submitBank()){
			page_Apply.click_submitBank();
		}
	}
	//详细用途少于10个字
	public void detailedUseErrorBus(String detailPurpose_apply){
		String detailedUseError=page_Apply.getAlertText();
		Assert.assertEquals(detailedUseError, "详细用途不少于10字");
		page_Apply.closeAlert();
		PageUtils.sleep(2000);
		page_Apply.input_detailPurpose(detailPurpose_apply);	
	}
	//借款金额<1000或者>50000
	public void borrowsMoneyErrorBus(int int_money_apply ){
		String borrowsMoney=page_Apply.getAlertText();
		//提示应该为：借款需介于1百~5万元之间
		Assert.assertEquals(borrowsMoney, "借款需介于1千元~5万元之间");
		page_Apply.closeAlert();
		PageUtils.sleep(2000);
		page_Apply.input_money(int_money_apply);
	}
	//100<=借款金额为<1000，借款产品为普通包，专科包
	public void borrowsProductMoneyDisagreeErrorBus(int int_money_apply){
		String borrowsProductMoneyDisagree=page_Apply.getAlertText();
		Assert.assertEquals(borrowsProductMoneyDisagree, "借款需介于1千元~5万元之间");
		page_Apply.closeAlert();
		PageUtils.sleep(2000);
		page_Apply.input_money(int_money_apply);
	}
	//1000<借款金额,借款产品为应急借
	public void borrowsProductMoneyDisagree2ErrorBus(int int_money_apply){
		String borrowsProductMoneyDisagree=page_Apply.getAlertText();
		Assert.assertEquals(borrowsProductMoneyDisagree, "名校贷应急包金额要在1百元~1千元之间");
		page_Apply.closeAlert();
		PageUtils.sleep(2000);
		page_Apply.input_money(int_money_apply);
	}
	//借款产品为白领包相关提示
	public void whiteCollarPromptBus(){
		String whiteCollarPrompt=page_Apply.getAlertText();
		Assert.assertEquals(whiteCollarPrompt, "很遗憾，您没有名校贷产品的借款记录，暂时不能申请白领包产品，请咨询在线客服！");
		page_Apply.closeAlert();
	}
	
}
