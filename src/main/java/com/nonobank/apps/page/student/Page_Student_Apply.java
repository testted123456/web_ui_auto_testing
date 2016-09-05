package com.nonobank.apps.page.student;

import java.sql.Connection;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.objectRepository.WebSelect;
import com.nonobank.apps.objectRepository.WebTextArea;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.db.DBUtils;
import com.nonobank.apps.utils.file.ParseProperties;
import junit.framework.Assert;

/**
 * 类说明：借款申请页面
 * 页面地址：
 */
public class Page_Student_Apply extends BasePage{

	public static Logger logger = LogManager.getLogger(Page_Student_Apply.class);
	public String username;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	//借款用途
	public void selectPurposeByIndex(int index){
		logger.info("begin to select purpose by index...");
		WebSelect select_purpose = objectFactory.getWebSelect("useway");
		select_purpose.selectByIndex(index);
	}
	
	public void selectPurposeByValue(String value){
		logger.info("begin to select purpose by value...");
		WebSelect select_purpose = objectFactory.getWebSelect("useway");
		select_purpose.selectByVisibleText(value);
	}
	
	//详细用途
	public void inputDetailPurpose(String value){
		logger.info("begin to input detail purpose...");
		WebTextArea textArea_detail_purpose = objectFactory.getWebTextArea("useway1");
		textArea_detail_purpose.input(value);
	}
	
	//金额
	public void inputMoney(int money){
		logger.info("begin to input money..." + money);
		WebInput input_money = objectFactory.getWebInput("price");
		input_money.input(String.valueOf(money));
	}
	
	public void inputValidate(){
		if(objectFactory.isElementExists("validate", WebElementType.WebInput)){
			logger.info("输入验证码...");
			WebInput input_validate = objectFactory.getWebInput("validate");
			input_validate.clearAndInput("a1b0");
		}
	}
	
	//名校贷普通包借款期数
	public void selectPieceOfCommonLoan(int index){
		logger.info("begin to select common loan...");
		WebInput input_expect1 = objectFactory.getWebInput("expect1");
		WebInput input_expect2 = objectFactory.getWebInput("expect2");
		WebInput input_expect3 = objectFactory.getWebInput("expect3");
		
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
	public void selectPieceOfEmergencyLoan(int index){
		logger.info("begin to select emergency loan...");
		WebInput input_expect4 = objectFactory.getWebInput("expect4");
		WebInput input_expect5 = objectFactory.getWebInput("expect5");
		WebInput input_expect6 = objectFactory.getWebInput("expect6");
		
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
	
	//名校贷应急包借款期数 
	public void selectPieceOfWhileCollarLoan(int index){
		logger.info("begin to select while collar loan...");
		WebInput input_expect7 = objectFactory.getWebInput("expect7");
		WebInput input_expect8 = objectFactory.getWebInput("expect8");
		WebInput input_expect9 = objectFactory.getWebInput("expect9");
		
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
	public void selectPieceOfJuniorCollegeLoan(int index){
		logger.info("begin to select junior college loan...");
		WebInput input_expect10 = objectFactory.getWebInput("expect10");
		WebInput input_expect11 = objectFactory.getWebInput("expect11");
		WebInput input_expect12 = objectFactory.getWebInput("expect12");
		
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
	
	//提交
	public void submit(){
		logger.info("begin to submit...");
		WebButton button_submit = objectFactory.getWebButton("btnsubmit");
		button_submit.click();
	}
	
	/**
	 * 检测提交是否成功
	 * @return
	 */
	public boolean isGoNextSuccessful(){
		String portal_url = ParseProperties.getInstance().getProperty("url");
		String current_url = driver.getCurrentUrl();
		
		while(!current_url.equals(portal_url + "/Student/Improve")){
			sleep(1000);
			current_url = driver.getCurrentUrl();
		}
		
		checkLoanMoney();
		return objectFactory.isElementExists("btnsubmit", WebElementType.WebButton);
	}
	
	/**
	 * 检测借款金额、期数、还款金额
	 */
	public void checkLoanMoney(){
		List<WebElement> elements = objectFactory.getWebElements(By.xpath("//div[@class='style3']//span[@class='moneyNum']"));

		double totalOfMoney = Double.valueOf(elements.get(0).getText());
		double pieces = Double.valueOf(elements.get(1).getText());
		double monthPerMoney = Double.valueOf(elements.get(2).getText());
		
		String username = getAccount();
		
		Connection conn = DBUtils.getNonoConnection();
		String sql = 
		"SELECT bp.id, bp.bp_price_audit, bp.bp_expect_audit, bp.bp_rate_audit FROM user_info ui, borrows_prepare bp WHERE ui.id=bp.user_id AND ui.user_name='"
		+ username
		+ "' ORDER BY id DESC LIMIT 1";
		Object[] objs = DBUtils.getOneLine(conn, sql);
		DBUtils.closeConnection();
		double auditMoney = Double.valueOf(objs[1].toString());
		double auditPieces = Double.valueOf(objs[2].toString());
		double auditRate = Double.valueOf(objs[3].toString());
		double auditMonthPerMoney = auditMoney/auditPieces + auditRate/1200 * auditMoney;
		
		if(totalOfMoney != auditMoney){
			logger.error("totalOfMoney is " + totalOfMoney + " and auditMoney is " + auditMoney);
			Assert.fail("totalOfMoney does not equal to auditMoney.");
		}
		
		if(pieces!=auditPieces){
			logger.error("pieces is " + pieces + " and auditPieces is " + auditPieces);
			Assert.fail("pieces does not equal to auditPieces.");
		}
		
		if(Math.abs(monthPerMoney-auditMonthPerMoney)>0.01){
			logger.info("totalOfMoney is " + totalOfMoney + " and auditMoney is " + auditMoney);
			logger.info("pieces is " + pieces + " and auditPieces is " + auditPieces);
			logger.error("monthPerMoney is " + monthPerMoney + " and auditMonthPerMoney is " + auditMonthPerMoney);
			Assert.fail("monthPerMoney does not equal to auditMonthPerMoney.");
		}
	}
	
	//下一步
	public void goNext(){
		logger.info("begin to go to next step...");
		WebButton button_next_step = objectFactory.getWebButton("goNext");
		button_next_step.click();	
	}
	
	//返回修改
	public void goBack(){
		logger.info("begin to go to next step...");
		WebButton button_goBack = objectFactory.getWebButton("goBack");
		button_goBack.click();	
	}
	
	//从页面获取用户姓名
	public String getAccount(){
		WebLink link_account = objectFactory.getWebLink("Account");
		String account = link_account.getText();
		return account;
	}
}
