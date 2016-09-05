package com.nonobank.apps.page.student;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.objectRepository.WebSelect;
import com.nonobank.apps.objectRepository.WebSpan;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.data.BankCardUtils;
import com.nonobank.apps.utils.db.DBUtils;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.file.ParseXLSX;
import com.nonobank.apps.utils.page.PageUtils;

import junit.framework.Assert;

/**
 * 类说明：获取额度并完善资料页面
 * 页面地址：http://www.test.nonobank.com/Student/Improve
 *
 */
public class Page_Student_Improve extends BasePage{
	public static Logger logger = LogManager.getLogger(Page_Student_Improve.class);
	public String getUsername(){
		WebLink link_account = objectFactory.getWebLink("account");
		String username = link_account.getText();
		return username;
	}

	/**
	 * 邮箱地址
	 * @param email
	 */
	public void input_email(String email){
		logger.info("begin to input email...");
		if(objectFactory.isElementExists("email",WebElementType.WebInput)){
			WebInput input_email = objectFactory.getWebInput("email");
			input_email.clearAndInput(email);
		}else{
			logger.info("现在不需要填email了...");
		}
		
	}
	
	/**
	 * 家庭地址
	 * @param address
	 */
	public void input_address(String address){
		logger.info("begin to input address...");
		WebInput input_address = objectFactory.getWebInput("home_address");
		input_address.clearAndInput(address);
	}
	
	/*
	 * 选择家庭收入，1:5万以下；2:5~10万；3:10~20万；4:20万以上
	 */
	public void select_income(int index){
		logger.info("begin to select income...");
		WebInput input_income = null;
		
		switch (index) {
		case 1:
			input_income = objectFactory.getWebInput("income1");
			input_income.click();
			break;
		case 2:
			input_income = objectFactory.getWebInput("income2");
			input_income.click();
			break;
		case 3:
			input_income = objectFactory.getWebInput("income3");
			input_income.click();
			break;
		case 4:
			input_income = objectFactory.getWebInput("income4");
			input_income.click();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 父母姓名
	 * @param name
	 */
	public void input_parent_name(String name){
		logger.info("begin to input parent name...");
		WebInput input_parent_name = objectFactory.getWebInput("parent_name");
		input_parent_name.clearAndInput(name);
	}
	
	/**
	 * 父母手机
	 * @param mobile
	 */
	public void input_parent_mobile(String mobile){
		logger.info("begin to input parent mobile...");
		WebInput input_parent_mobile = objectFactory.getWebInput("parent_mobile");
		input_parent_mobile.clearAndInput(mobile);
	}
	
	/**
	 * 辅导员姓名
	 * @param name
	 */
	public void input_counselor_name(String name){
		logger.info("begin to input counselor name...");
		WebInput input_counselor_name = objectFactory.getWebInput("counselor_name");
		input_counselor_name.clearAndInput(name);
	}
	
	/**
	 * 辅导员手机
	 * @param mobile
	 */
	public void input_counselor_mobile(String mobile){
		logger.info("begin to input counselor mobile...");
		WebInput input_counselor_mobile = objectFactory.getWebInput("counselor_mobile");
		input_counselor_mobile.clearAndInput(mobile);
	}
	
	/**
	 * 朋友1姓名
	 * @param name
	 */
	public void input_friend1_name(String name){
		logger.info("begin to input friend1 name...");
		WebInput input_friend1_name = objectFactory.getWebInput("friend1_name");
		input_friend1_name.clearAndInput(name);
	}
	
	/**
	 * 朋友1手机
	 * @param mobile
	 */
	public void input_friend1_mobile(String mobile){
		logger.info("begin to input friend1 mobile...");
		WebInput input_friend1_mobile = objectFactory.getWebInput("friend1_mobile");
		input_friend1_mobile.clearAndInput(mobile);
	}
	
	/**
	 * 朋友2姓名
	 * @param name
	 */
	public void input_friend2_name(String name){
		logger.info("begin to input friend2 name...");
		WebInput input_friend2_name = objectFactory.getWebInput("friend2_name");
		input_friend2_name.clearAndInput(name);
	}
	
	/**
	 * 朋友2手机
	 * @param mobile
	 */
	public void input_friend2_mobile(String mobile){
		logger.info("begin to input friend2 mobile...");
		WebInput input_friend2_mobile = objectFactory.getWebInput("friend2_mobile");
		input_friend2_mobile.clearAndInput(mobile);
	}
	
	/**
	 * 朋友3姓名
	 * @param name
	 */
	public void input_friend3_name(String name){
		logger.info("begin to input friend3 name...");
		WebInput input_friend3_name = objectFactory.getWebInput("friend3_name");
		input_friend3_name.clearAndInput(name);
	}
	
	/**
	 * 朋友3手机
	 * @param mobile
	 */
	public void input_friend3_mobile(String mobile){
		logger.info("begin to input friend3 mobile...");
		WebInput input_friend3_mobile = objectFactory.getWebInput("friend3_mobile");
		input_friend3_mobile.clearAndInput(mobile);
	}
	
	/**
	 * 上传文件
	 * @param file
	 */
	public void uploadfile(String file){
		logger.info("开始上传身份证...");
		WebElement iFrame = 
				objectFactory.getWebElement(By.xpath("//iframe[@src='/Student/PicUpload']"));
		driver.switchTo().frame(iFrame);
		WebElement input_fileupload = objectFactory.getWebElement(By.xpath("//input[@id='fileupload']"));
		URL url = ParseXLSX.class.getClassLoader().getResource(file);
		String file_path = url.getFile();
		input_fileupload.sendKeys(file_path);
		WebInput input_submit = objectFactory.getWebInput("submit");
		input_submit.click();
	    driver.switchTo().defaultContent();
	}
	
	/**
	 * 银行卡号有误的提示信息
	 * @return
	 */
	public String getBankCardErrorMsg(){
		if(objectFactory.isElementExists("loadingText", WebElementType.WebSpan)){
			WebSpan span_msg = objectFactory.getWebSpan("loadingText");
			String msg = span_msg.getText();
			return msg;
		}
		return null;
	}
	
	/**
	 * 银行卡号
	 * @param banks_account
	 */
	public void input_bankcard(String banks_account){
		logger.info("begin to input bank account...");
		String banksAccount = banks_account;
		WebInput input_banks_account = objectFactory.getWebInput("banks_account");
		
		if(banks_account.toLowerCase().equals("random")){
			banks_account = BankCardUtils.getBankCard();
		}
		
		input_banks_account.clearAndInput(banks_account);
		PageUtils.sendKeys(Keys.TAB);
		
		String bankName = null;
		
		if(banks_account.startsWith("436742")){
			bankName = "中国建设银行";
		}
		if(banks_account.startsWith("622202")){
			bankName = "中国工商银行";
		}
		
		WebSelect select_bank = objectFactory.getWebSelect("banks_cat");
		
		select_bank.selectByVisibleText(bankName);
		
		String bankCardErrorMsg = getBankCardErrorMsg();
		
		while(null != bankCardErrorMsg && bankCardErrorMsg.equals("银行卡号码有误")){
			if(banksAccount.equals("random")){
				banks_account = BankCardUtils.getBankCard();
				input_banks_account = objectFactory.getWebInput("banks_account");
				input_banks_account.clearAndInput(banks_account);
				PageUtils.sendKeys(Keys.TAB);
				select_bank = objectFactory.getWebSelect("banks_cat");
				select_bank.selectByVisibleText(bankName);
				bankCardErrorMsg = getBankCardErrorMsg();
			}
		}
		
		/*
		if(banks_account.startsWith("436742")){
			bankName = "中国建设银行";
		}
		if(banks_account.startsWith("622202")){
			bankName = "中国工商银行";
		}
		
		
		String select_value = null;
		
		while(true){
			select_value = select_bank.getSelectedText();
			if(select_value.equals(bankName)){
				break;
			}
		}
		*/
		
		String username = getUsername();
		String sql = 
		"SELECT uid.education,ui.mobile_num FROM user_info ui,user_info_detail uid WHERE ui.id=uid.user_id AND ui.user_name='"
				+ username
				+ "'";
		DBUtils.closeConnection();
		Connection con = DBUtils.getNonoConnection();
		Object [] objs = DBUtils.getOneLine(con, sql);
		String education = objs[0].toString();
		String mobilenum = objs[1].toString();
		logger.info("education is " + education + ".");
		
		if(education.equals("专科")){
			//专科生需要手机号码验证
			if(objectFactory.isElementExists("mobilenum", WebElementType.WebInput)){
				//输入银行预留手机号码
				WebInput input_mobile = objectFactory.getWebInput("mobilenum");
				input_mobile.input(mobilenum);
				//获取验证码
				WebLink link_getValidate = objectFactory.getWebLink("getValidate");
				link_getValidate.click();
				sleep(1000);
				link_getValidate = objectFactory.getWebLink("getValidate");
				String text = link_getValidate.getText();
				while(text.equals("获取验证码")){
					link_getValidate = objectFactory.getWebLink("getValidate");
					link_getValidate.click();
					sleep(1000);
					link_getValidate = objectFactory.getWebLink("getValidate");
					text = link_getValidate.getText();
				}
				//输入验证码
				WebInput input_validNo = objectFactory.getWebInput("validNo");
				input_validNo.input("0615");
				String validNo = input_validNo.getValue();
				logger.info("valid no is " + validNo);
				sleep(1000);
			}else{
				logger.info("专科需要手机号码鉴权。");
				Assert.fail();
			}
		}
	}
	
	/**
	 * 提交
	 */
	public void submit(String email){
		logger.info("开始提交...");
		
		//提交可能没有响应，尝试10次
		for(int i=0;i<10;i++){
			if(objectFactory.isElementExists("btnsubmit",WebElementType.WebButton)){
				WebButton button_submit = objectFactory.getWebButton("btnsubmit");
				button_submit.click();
				
				//提示照片不符合
				if(objectFactory.isAlertExists(2000)){
					String text = objectFactory.getAlertText();
					if(text.contains("照片检验不合格")){
						logger.warn("照片校验不合格...");
						objectFactory.closeAlert();
						input_email(email);
					}else{
						logger.warn(text);
					}
				}
			}else{
				break;
			}
		}
	}
	
	//判断提交是否成功
	public void submitSuccess(){
		boolean isSubmitSuccess = false;
		
		for(int i=0;i<10;i++){
			List<WebElement> lists = objectFactory.getWebElements(By.xpath("//span"));
			
			for(WebElement e : lists){
				String text = e.getText();
				if(text.equals("点击获得优先审核权")){
					isSubmitSuccess = true;
					break;
				}
			}
			
			if(isSubmitSuccess == true){
				break;
			}
		}
		
		if(isSubmitSuccess == true){
			logger.info("提交成功...");
		}else{
			logger.error("提交失败...");
			Assert.fail("提交失败...");
		}
	}
	
	/**
	 * 跳转到免视频录制页面
	 */
	public void navigate_to_signByVideo(){
		logger.info("开始录制视频...");
		Properties prop = ParseProperties.getInstance();
		String url_video = prop.getProperty("url_video");
		driver.get(url_video);
		if(objectFactory.isAlertExists(120000)){
			logger.info("录制视频成功...");
			objectFactory.closeAlert();
			sleep(500);
		}
	}
}


