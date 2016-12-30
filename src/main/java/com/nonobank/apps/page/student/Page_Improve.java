package com.nonobank.apps.page.student;

import java.net.URL;
import java.util.HashMap;
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
import com.nonobank.apps.objectRepository.WebSpan;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.file.ParseXLSX;

/**
 * 类说明：获取额度并完善资料页面 页面地址：http://www.test.nonobank.com/Student/Improve
 *
 */
public class Page_Improve extends BasePage {
	public static Logger logger = LogManager.getLogger(Page_Improve.class);

	// 页面是否加载完成
	public boolean isImprovePageLoaded() {
		return isElementExists("常用邮箱", WebElementType.WebInput, 10);
	}

	// 借款信息检查
	public HashMap<String, String> check_borrows() {
		logger.info("借款信息检查...");
		HashMap<String, String> hashMap = new HashMap<>();
		List<WebElement> list = objectFactory.getWebElements("借款信息金额检查", WebElementType.WebSpan, xmlFile);

		for (int i = 0; i < list.size(); i++) {
			switch (i) {
			case 0:
				hashMap.put("borrowsMoney", list.get(i).getText());
				break;
			case 1:
				hashMap.put("periods", list.get(i).getText());
				break;
			case 2:
				hashMap.put("perMoney", list.get(i).getText());
				break;
			case 3:
				hashMap.put("consultingFees", list.get(i).getText());
				break;
			case 4:
				hashMap.put("creditRewards", list.get(i).getText());
				break;
			}
		}
		return hashMap;
	}

	// 点击获取优先审核权
	public void click_getFirstReviewPower() {
		logger.info("点击获取优先审核权...");
		WebButton click_getFirstReviewPower = objectFactory.getWebButton("点击获得优先审核权");
		click_getFirstReviewPower.click();
	}

	// 点击取消本次申请
	public void click_cancelApply() {
		logger.info("点击取消本次申请...");
		WebLink click_cancelApply = objectFactory.getWebLink("取消本次申请");
		click_cancelApply.click();
	}

	// 输入常用邮箱
	public void input_email(String email) {
		logger.info("输入常用邮箱...");
		WebInput input_email = objectFactory.getWebInput("常用邮箱");
		input_email.clearAndInput(email);
	}

	// 输入家庭地址
	public void input_address(String address) {
		logger.info("输入家庭地址...");
		WebInput input_address = objectFactory.getWebInput("家庭地址");
		input_address.clearAndInput(address);
	}

	// 选择家庭收入，1:5万以下；2:5~10万；3:10~20万；4:20万以上
	public void select_income(int index) {
		logger.info("选择家庭收入...");
		WebInput input_income = null;
		switch (index) {
		case 1:
			input_income = objectFactory.getWebInput("5万以下");
			input_income.click();
			break;
		case 2:
			input_income = objectFactory.getWebInput("5-10万");
			input_income.click();
			break;
		case 3:
			input_income = objectFactory.getWebInput("10-20万");
			input_income.click();
			break;
		case 4:
			input_income = objectFactory.getWebInput("20万以上");
			input_income.click();
			break;
		default:
			break;
		}
	}

	// 输入父母姓名
	public void input_parentName(String name) {
		logger.info("输入父母姓名..........");
		WebInput input_parent_name = objectFactory.getWebInput("父亲或母亲姓名");
		input_parent_name.clearAndInput(name);
	}

	// 输入父母手机
	public void input_parentMobile(String mobile) {
		logger.info("输入父母手机...........");
		WebInput input_parent_mobile = objectFactory.getWebInput("父亲或母亲手机号");
		input_parent_mobile.clearAndInput(mobile);
	}

	// 输入辅导员姓名
	public void input_counselorName(String name) {
		logger.info("输入辅导员姓名...");
		WebInput input_counselor_name = objectFactory.getWebInput("辅导员姓名");
		input_counselor_name.clearAndInput(name);
	}

	// 输入辅导员手机
	public void input_counselorMobile(String mobile) {
		logger.info("输入辅导员手机...");
		WebInput input_counselor_mobile = objectFactory.getWebInput("辅导员手机号");
		input_counselor_mobile.clearAndInput(mobile);
	}

	// 输入好友1姓名
	public void input_friend1Name(String name) {
		logger.info("输入好友1姓名...");
		WebInput input_friend1_name = objectFactory.getWebInput("好友1姓名");
		input_friend1_name.clearAndInput(name);
	}

	// 输入好友1手机
	public void input_friend1Mobile(String mobile) {
		logger.info("输入好友1手机...");
		WebInput input_friend1_mobile = objectFactory.getWebInput("好友1手机号");
		input_friend1_mobile.clearAndInput(mobile);
	}

	// 输入好友2姓名
	public void input_friend2Name(String name) {
		logger.info("输入好友2姓名...");
		WebInput input_friend2_name = objectFactory.getWebInput("好友2姓名");
		input_friend2_name.clearAndInput(name);
	}

	// 输入好友2手机
	public void input_friend2Mobile(String mobile) {
		logger.info("输入好友2手机...");
		WebInput input_friend2_mobile = objectFactory.getWebInput("好友2手机号");
		input_friend2_mobile.clearAndInput(mobile);
	}

	// 输入好友3姓名
	public void input_friend3Name(String name) {
		logger.info("输入好友3姓名...");
		WebInput input_friend3_name = objectFactory.getWebInput("好友3姓名");
		input_friend3_name.clearAndInput(name);
	}

	// 输入好友3手机
	public void input_friend3Mobile(String mobile) {
		logger.info("输入好友3手机...");
		WebInput input_friend3_mobile = objectFactory.getWebInput("好友3手机号");
		input_friend3_mobile.clearAndInput(mobile);
	}

	// 上传文件
	public void uploadfile(String file) {
		logger.info("开始上传身份证...");
		WebElement iFrame = objectFactory.getWebElement(By.xpath("//iframe[@src='/Student/PicUpload']"));
		driver.switchTo().frame(iFrame);
		WebElement input_fileupload = objectFactory.getWebElement(By.xpath("//input[@id='fileupload']"));
		URL url = ParseXLSX.class.getClassLoader().getResource(file);
		String file_path = url.getFile();
		input_fileupload.click();
		input_fileupload.sendKeys(file_path);
		WebInput input_submit = objectFactory.getWebInput("submit");
		input_submit.click();
		driver.switchTo().defaultContent();
	}

	// 点击选择文件
	public void click_selectFile() {
		logger.info("点击选择文件...");
		WebInput click_selectFile = objectFactory.getWebInput("选择文件");
		click_selectFile.click();
	}

	// 点击上传文件
	public void click_uploadFile() {
		logger.info("点击上传文件...");
		WebInput click_uploadFile = objectFactory.getWebInput("上传");
		click_uploadFile.click();
	}

	// 输入账号
	public void input_bankCard(String banks_account) {
		logger.info("输入账号...");
		WebInput input_banks_account = objectFactory.getWebInput("账号");
		input_banks_account.clearAndInput(banks_account);
	}

	// 选择银行类型
	public void select_banksType(String banksType) {
		logger.info("选择银行类型.....");
		WebSelect select_banksType = objectFactory.getWebSelect("银行类型");
		select_banksType.selectByExactValue(banksType);
	}

	// 输入银行预留手机号
	public void input_bankMobile(String bankMobile) {
		logger.info("输入银行预留手机号.....");
		WebInput input_bankMobile = objectFactory.getWebInput("银行预留手机号");
		input_bankMobile.clearAndInput(bankMobile);
	}

	// 点击获取验证码
	public void click_getSmsCode() {
		logger.info("点击获取验证码.....");
		WebLink click_getSmsCode = objectFactory.getWebLink("获取验证码");
		click_getSmsCode.click();
	}

	// 输入验证码
	public void input_SmsCode(String smsCode) {
		logger.info("输入验证码.....");
		WebInput input_SmsCode = objectFactory.getWebInput("验证码");
		input_SmsCode.clearAndInput(smsCode);
	}

	// 点击同意并提交
	public void submit() {
		logger.info("开始提交...");
		WebButton button_submit = objectFactory.getWebButton("同意并提交");
		button_submit.click();
	}

	// 银行卡提示信息
	public String getText_bankCardPrompt() {
		logger.info("获取银行卡提示信息...");
		WebSpan getText_bankCardPrompt = objectFactory.getWebSpan("银行卡提示信息");
		String bankCardPrompt = getText_bankCardPrompt.getText();
		return bankCardPrompt;
	}

}
