package com.nonobank.apps.business.student;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.nonobank.apps.page.student.Page_Improve;
import com.nonobank.apps.utils.page.PageUtils;


public class Biz_Improve {

	public static Logger logger = LogManager.getLogger(Biz_Improve.class);

	Page_Improve page_Improve = new Page_Improve();
	
	public void borrowsInformationVerifyBus(int int_money_apply,int int_pieces_apply){
		logger.info("------------开始：借款信息检查-----------------");
		page_Improve.isImprovePageLoaded();
		HashMap<String, String> hashMap=page_Improve.check_borrows();
		String borrowsMoney_value=hashMap.get("borrowsMoney");
		String periods_value=hashMap.get("periods");
		String perMoney_value=hashMap.get("perMoney");
		String consultingFees_value=hashMap.get("consultingFees");
		String creditRewards_value=hashMap.get("creditRewards");
		//借款金额
		double double_borrowsMoney_value=Double.parseDouble(borrowsMoney_value);
		double double_money_apply=int_money_apply;
		double d_value_applyMoney=double_borrowsMoney_value-double_money_apply;
		if(int_money_apply>1000){
			if(d_value_applyMoney==0){
				logger.info("借款金额与预期一致");
			}else{
				Assert.fail("借款金额与预期不一致，期望值为："+double_money_apply+"实际值为："+double_borrowsMoney_value);
			}
		}
		//期数
		double double_periods_value=Double.parseDouble(periods_value);
		double double_pieces_apply=int_pieces_apply;
		if(double_periods_value-double_pieces_apply==0){
			logger.info("借款期数与预期一致");
		}else{
			Assert.fail("借款期数与预期不一致，期望值为："+double_pieces_apply+"实际值为："+double_periods_value);
		}
		//每月还款金额
		double float_perMoney_value=Double.parseDouble(perMoney_value);
		double float_perMoney_apply=(double_money_apply/double_pieces_apply)+(double_money_apply*0.99/100);
		double d_value=Math.abs(float_perMoney_value-float_perMoney_apply);
		if(int_money_apply>1000){
			if(d_value<0.01){
				logger.info("每期还款金额与预期值一致");
			}else{
				Assert.fail("每期还款金额与预期值不一致，期望值为："+float_perMoney_apply+"实际值为："+float_perMoney_value);
			}	
		}	
		logger.info("------------结束：借款信息检查-----------------");
	}
	public void personalInformationBus(String email_improve, String address_improve, 
			String income_index_improve,String parentName_improve,String parentMobile_improve, 
			String counselorName_improve, String counselorMobile_improve, String friend1Name_improve,
			String friend1Mobile_improve,
			String friend2Name_improve, String friend2Mobile_improve, String friend3Name_improve,
			String friend3Mobile_improve){
		logger.info("------------开始：联系人-----------------");
		int int_income_index_improve=Integer.parseInt(income_index_improve);
		page_Improve.input_email(email_improve);
		PageUtils.sleep(2000);
		page_Improve.input_address(address_improve);
		PageUtils.sleep(2000);
		page_Improve.select_income(int_income_index_improve);
		PageUtils.sleep(2000);
		page_Improve.input_parentName(parentName_improve);
		PageUtils.sleep(2000);
		page_Improve.input_parentMobile(parentMobile_improve);
		PageUtils.sleep(2000);
		page_Improve.input_counselorName(counselorName_improve);
		PageUtils.sleep(2000);
		page_Improve.input_counselorMobile(counselorMobile_improve);
		PageUtils.sleep(2000);
		page_Improve.input_friend1Name(friend1Name_improve);
		PageUtils.sleep(2000);
		page_Improve.input_friend1Mobile(friend1Mobile_improve);
		PageUtils.sleep(2000);
		page_Improve.input_friend2Name(friend2Name_improve);
		PageUtils.sleep(2000);
		page_Improve.input_friend2Mobile(friend2Mobile_improve);
		PageUtils.sleep(2000);
		page_Improve.input_friend3Name(friend3Name_improve);
		PageUtils.sleep(2000);
		page_Improve.input_friend3Mobile(friend3Mobile_improve);
		PageUtils.sleep(2000);
		logger.info("------------结束：联系人-----------------");
	}
	public void uploadingBus(String file_improve){
		logger.info("------------开始：资料上传-----------------");
		page_Improve.uploadfile(file_improve);
		logger.info("------------结束：资料上传-----------------");
	}
	public void bankCardBus(String bankcardAccount_improve,
			String banksType_improve,String bankMobile_improve,String smsCode_improve){
		logger.info("------------开始：银行卡信息-----------------");
		page_Improve.input_bankCard(bankcardAccount_improve);
		page_Improve.sleep(3000);
		page_Improve.select_banksType(banksType_improve);
		page_Improve.sleep(3000);
		page_Improve.input_bankMobile(bankMobile_improve);
		page_Improve.sleep(3000);
		page_Improve.click_getSmsCode();
		page_Improve.sleep(3000);
		page_Improve.input_SmsCode(smsCode_improve);
		logger.info("------------结束：银行卡信息-----------------");
	}
	public void submitBus(){
		logger.info("------------开始：提交-----------------");
		page_Improve.submit();
		logger.info("------------结束：提交-----------------");
	}
	public void getPriorityReviewBus(){
		logger.info("------------开始：点击获得优先审核权-----------------");
		page_Improve.click_getFirstReviewPower();
		logger.info("------------结束：点击获得优先审核权-----------------");
	}
	public void cancelApplyBus(){
		logger.info("------------开始：点击取消本次申请-----------------");
		page_Improve.click_cancelApply();
		logger.info("------------结束：点击取消本次申请-----------------");
	}
	//银行卡信息已存在
	public void bankCardExistBus(String bankcardAccount_improve,String banksType_improve,
			String bankMobile_improve){
		logger.info("------------开始：银行卡信息已存在-----------------");
		page_Improve.input_bankCard(bankcardAccount_improve);
		page_Improve.select_banksType(banksType_improve);
		page_Improve.input_bankMobile(bankMobile_improve);
		page_Improve.sleep(3000);
		page_Improve.click_getSmsCode();
		page_Improve.sleep(5000);
		String bankCardExistPrompt=page_Improve.getAlertText();
		Assert.assertEquals(bankCardExistPrompt, "银行卡已存在！");
		page_Improve.closeAlert();
		logger.info("------------结束：银行卡信息已存在-----------------");
	}
	//银行卡号码有误
	public void bankCardErrorBus(){
		logger.info("------------开始：银行卡号码有误-----------------");
		String bankCardPrompt=page_Improve.getText_bankCardPrompt();
		Assert.assertEquals(bankCardPrompt, "银行卡号码有误");
		logger.info("------------结束：银行卡号码有误-----------------");
	}
	//联系人信息重复
	public void linkedListRepeatBus(){
		logger.info("------------开始：联系人信息重复-----------------");
		
		logger.info("------------结束：联系人信息重复-----------------");
	}
	//联系人手机号码格式错误
	public void linkedListErrorBus(){
		logger.info("------------开始：联系人手机号码格式错误-----------------");
		String linkedListError=page_Improve.getAlertText();
		Assert.assertEquals(linkedListError, "手机号位数不对！");
		page_Improve.closeAlert();
		logger.info("------------结束：联系人手机号码格式错误-----------------");
	}
	//获取照片检验不合格，请重新提交
	public void photoNoQualifiedPromptBus(String email){
		if(page_Improve.isAlertExists(3000)){
			logger.info("------------开始：照片检验不合格-----------------");
			String getText_photoSubmit=page_Improve.getAlertText();
			Assert.assertEquals(getText_photoSubmit, "照片检验不合格，请重新上传。务必：将证件放平后垂直拍摄；使照片清晰文字可辨识。");
			PageUtils.sleep(3000);
			page_Improve.closeAlert();
			logger.info("------------开始：照片检验不合格-----------------");
			PageUtils.sleep(3000);
			page_Improve.input_email(email);
			PageUtils.sleep(3000);
			page_Improve.submit();
		}
	}
}
