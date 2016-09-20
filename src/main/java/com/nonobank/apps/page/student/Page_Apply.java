package com.nonobank.apps.page.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebCommon;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebSelect;
import com.nonobank.apps.objectRepository.WebTextArea;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.page.PageUtils;

/**
 * 类说明：借款申请页面
 * 页面地址：
 */
public class Page_Apply extends BasePage{

	public static Logger logger = LogManager.getLogger(Page_Apply.class);
	public String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	//根据index选择借款用途
	public void select_purposeByIndex(int index){
		logger.info("根据index选择借款用途...");
		WebSelect select_purpose = objectFactory.getWebSelect("借款用途");
		select_purpose.selectByIndex(index);
	}
	//根据用途选择借款用途
	public void select_purposeByValue(String value){
		logger.info("根据用途选择借款用途...");
		WebSelect select_purpose = objectFactory.getWebSelect("借款用途");
		select_purpose.selectByVisibleText(value);
	}
	//详细用途
	public void input_detailPurpose(String value){
		logger.info("详细用途...");
		WebTextArea textArea_detail_purpose = objectFactory.getWebTextArea("详细用途");
		textArea_detail_purpose.input(value);
	}
	//金额
	public void input_money(String money){
		logger.info("借款金额..." + money);
		WebInput input_money = objectFactory.getWebInput("借款金额");
		input_money.input(String.valueOf(money));
	}
	//输入验证码
	public void input_smsCode(String smsCode){
		logger.info("输入验证码...");
		WebInput input_smsCode = objectFactory.getWebInput("验证码");
		input_smsCode.input(smsCode);
	}
	//检查验证码输入框是否存在
	public void isExist_smsCode(){
		logger.info("检查验证码输入框是否存在...");
		WebInput input_smsCode = objectFactory.getWebInput("验证码");
		
	}
	//名校贷普通包借款期数
	public void select_pieceOfCommonLoan(int index){
		logger.info("名校贷普通包借款期数...");
		WebInput input_expect1 = objectFactory.getWebInput("普通包12期");
		WebInput input_expect2 = objectFactory.getWebInput("普通包24期");
		WebInput input_expect3 = objectFactory.getWebInput("普通包36期");
		
		switch (index) {
		case 12:
			input_expect1.click();
			break;
		case 24:
			input_expect2.click();
			break;
		case 36:
			input_expect3.click();
			break;
		default:
			break;
		}
	}
	//名校贷应急包借款期数 
	public void select_pieceOfEmergencyLoan(int index){
		logger.info("名校贷应急包借款期数...");
		WebInput input_expect4 = objectFactory.getWebInput("应急包1期");
		WebInput input_expect5 = objectFactory.getWebInput("应急包2期");
		WebInput input_expect6 = objectFactory.getWebInput("应急包3期");
		
		switch (index) {
		case 1:
			input_expect4.click();
			break;
		case 2:
			input_expect5.click();
			break;
		case 3:
			input_expect6.click();
			break;
		default:
			break;
		}
	}
	
	//名校贷白领包借款期数 
	public void select_pieceOfWhileCollarLoan(int index){
		logger.info("名校贷白领包借款期数...");
		WebInput input_expect7 = objectFactory.getWebInput("白领包12期");
		WebInput input_expect8 = objectFactory.getWebInput("白领包24期");
		WebInput input_expect9 = objectFactory.getWebInput("白领包36期");
		
		switch (index) {
		case 12:
			input_expect7.click();
			break;
		case 24:
			input_expect8.click();
			break;
		case 36:
			input_expect9.click();
			break;
		default:
			break;
		}
	}
	
	//名校贷专科包
	public void select_pieceOfJuniorCollegeLoan(int index){
		logger.info("名校贷专科包借款期数...");
		WebInput input_expect10 = objectFactory.getWebInput("专科包12期");
		WebInput input_expect11 = objectFactory.getWebInput("专科包24期");
		WebInput input_expect12 = objectFactory.getWebInput("专科包36期");
		
		switch (index) {
		case 12:
			input_expect10.click();
			break;
		case 24:
			input_expect11.click();
			break;
		case 36:
			input_expect12.click();
			break;
		default:
			break;
		}
	}
	// 获取每期需还金额
	public String check_perMoney(){
		logger.info("检查每期需还金额...");
		WebCommon check_PerMoney=objectFactory.getWebCommon("每期需还金额");
		String moneyPer=check_PerMoney.getText();
		return moneyPer;
	}
	//提交
	public void click_submit(){
		logger.info("提交...");
		WebButton button_submit = objectFactory.getWebButton("提交");
		button_submit.click();
	}
	// 检查借款产品
	public String check_loanProduct(){
		logger.info("检查借款产品...");
		WebCommon check_LoanProduct=objectFactory.getWebCommon("借款产品");
		String LoanProduct=check_LoanProduct.getText();
		return LoanProduct;
	}
	// 检查借款期数
	public String check_loanPeriods(){
		logger.info("检查借款产品...");
		WebCommon check_LoanPeriods=objectFactory.getWebCommon("借款期数");
		String LoanPeriods=check_LoanPeriods.getText();
		return LoanPeriods;
	}
	// 检查借款金额
	public String check_loanMoney(){
		logger.info("检查借款产品...");
		WebCommon check_LoanMoney=objectFactory.getWebCommon("借款期数");
		String LoanMoney=check_LoanMoney.getText();
		return LoanMoney;
	}
	//下一步
	public void click_goNext(){
		logger.info("下一步...");
		WebButton button_next_step = objectFactory.getWebButton("下一步");
		button_next_step.click();	
	}
	//返回修改
	public void click_goBack(){
		logger.info("返回修改...");
		WebButton button_goBack = objectFactory.getWebButton("返回修改");
		button_goBack.click();	
	}
}
