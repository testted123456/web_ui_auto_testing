package com.nonobank.apps.page.withdrawal;

import java.sql.Connection;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebFont;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.objectRepository.WebSpan;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.db.DBUtils;
import com.nonobank.apps.utils.file.ParseProperties;

public class Page_User_Withdrawal extends BasePage {
	
	public static Logger logger = LogManager.getLogger(Page_User_Withdrawal.class);
	
	/**
	 * 跳转到提现页面
	 */
	public void navigate_to_withdrawal(){
		logger.info("begin to nagivate to withdrawal page...");
		String url_withdrawal = ParseProperties.getInstance().getProperty("url_withdrawal");
		driver.navigate().to(url_withdrawal);
	}
	
	/**
	 * 从页面获取用户名
	 */
	public String getUsername(){
		WebLink link_username = objectFactory.getWebLink("Account");
		String username = link_username.getText();
		return username;
	}
	
	/**
	 * 获取错误提示信息
	 * @return
	 */
	public String getErrorMsg(){
		WebFont font = objectFactory.getWebFont("error_msg");
		String error_msg = font.getText();
		return error_msg;
	}
	
	/**
	 * 根据user id随机选择一张通过鉴权的卡
	 * @param user_id
	 */
	public void select_random_card(String username){
		logger.info("begin to select a random bank card...");
		
		Connection con = DBUtils.getNonoConnection();
		
		String sql = "SELECT uba.bank_card_id FROM user_bankcard_auth uba, user_info ui WHERE ui.id=uba.user_id AND ui.user_name='"
		+ username		
		+ "' AND uba.auth_status=1 AND uba.short_no IS NOT NULL LIMIT 1";
		
		Object obj_bank_id = DBUtils.getOneObject(con, sql);
		
		String  bank_id = null;
		
		if(null != obj_bank_id){
			bank_id = obj_bank_id.toString();
		}else{
			logger.info("get null from database, and sql is " + sql);
			Assert.fail();
		}
		
		List<WebElement> elements_radio = objectFactory.getWebElements(By.xpath("//input[@name='banks_id']"));
		
		String value = null;
		
		for(WebElement e : elements_radio){
			value = e.getAttribute("value");
			
			if(value.equals(bank_id)){
				e.click();
				break;
			}
		}
	}
	
	/**
	 * 根据银行卡号选择银行卡 
	 * @param cardno
	 */
	public void select_card(String cardno){
		
		logger.info("begin to select bank card...");
		
		Connection con = DBUtils.getNonoConnection();
		
		String sql = "SELECT id FROM user_bankcard_info where bank_card_no='"
				+ cardno + "' limit 1";
		
		Object obj_bank_id = DBUtils.getOneObject(con, sql);
		
		String  bank_id = null;
		
		if(null != obj_bank_id){
			bank_id = obj_bank_id.toString();
		}else{
			logger.info("get null from database, and sql is " + sql);
			Assert.fail();
		}
		
		List<WebElement> elements_radio = objectFactory.getWebElements(By.xpath("//input[@name='banks_id']"));
		
		String value = null;
		
		for(WebElement e : elements_radio){
			value = e.getAttribute("value");
			
			if(value.equals(bank_id)){
				e.click();
				break;
			}
		}
	}
	
	/**
	 * 输入提现金额
	 * @param money
	 */
	public void input_money(String money){
		logger.info("begin to input withdrawal cash...");
		WebInput input_money = objectFactory.getWebInput("banks_price");
		input_money.clearAndInput(money);
	}

	/**
	 * 点击“下一步”按钮
	 */
	public void goNext(){
		logger.info("begin to submit...");
		WebButton button = objectFactory.getWebButton("a_con_btn");
		button.click();
	}
	
	/**
	 * 判断点击“下一步”按钮是否跳转到提现确认页面
	 */
	public void isGoNextSuccess(){
		if(objectFactory.isElementExists("t_con_btn", WebElementType.WebButton)){
			logger.info("跳转到提现确认页面成功.");
		}else{
			logger.info("跳转到提现确认页面失败.");
			Assert.fail("go to withdrawal page failed.");
		}
	}

	/**
	 * 获取到账金额
	 * @return
	 */
	public String get_amount(){
		WebSpan span_amount = objectFactory.getWebSpan("account_amount");
		String amount = span_amount.getText();
		return amount;
	}
	
	/**
	 * 获取手续费
	 * @return
	 */
	public String getFee()	{
		WebFont  font_fee = objectFactory.getWebFont("poundage");
		String fee = font_fee.getText();
		return fee;
	}
	
	/**
	 * 提交后出现的alert
	 */
	public void accept_alert(){
		if(objectFactory.isAlertExists(15000)){
			driver.switchTo().alert().accept();
		}
	}
	
	/**
	 * 获取用户账户余额
	 * @return
	 */
	public String getBalance(){
		WebInput input_blance = objectFactory.getWebInput("userBalance");
		String balance = input_blance.getValue();
		return balance;
	}
	
	/**
	 * 从数据库获取用户余额
	 * @param username
	 * @return
	 */
	public String getBalance(String username){
		Connection con = DBUtils.getNonoConnection();
		String sql =
				"SELECT fa.balance-locking FROM finance_account fa, user_info ui WHERE fa.user_id=ui.id"
				
				+ " AND fa.role_id=7 AND ui.user_name='"
				+ username
				+ "' LIMIT 1";
		Object obj_balance = DBUtils.getOneObject(con, sql);
		
		if(null != obj_balance){
			return obj_balance.toString();
		}else{
			return null;
		}
	}
}
