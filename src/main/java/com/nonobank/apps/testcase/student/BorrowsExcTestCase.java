package com.nonobank.apps.testcase.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.runtime.directive.Parse;
import org.apache.velocity.test.BaseTestCase;
import org.testng.annotations.Test;

import com.nonobank.apps.business.student.Biz_Apply;
import com.nonobank.apps.business.student.Biz_Improve;
import com.nonobank.apps.business.student.Biz_Register;
import com.nonobank.apps.business.student.Biz_VideoSign;
import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.utils.page.PageUtils;

public class BorrowsExcTestCase extends BaseCase{
	Biz_Register biz_register;
	Biz_Apply biz_Apply;
	Biz_Improve biz_Improve;
	Biz_VideoSign biz_VideoSign;
	public static Logger logger = LogManager.getLogger(BorrowsTestCase.class);

	@Test(dataProvider = "dataSource")
	public void test(String userName_exist_register, String userName_register, String qq_exist_register,
			String qq_register, String mobile_exist_register, String mobile_register, String checkCode_error_register,
			String checkCode_register, String smsCode_register, String password_error_register,
			String confirmPassword_error_register,String province_register,String institution_register,
			String schoolArea_register,String year_register,String education_register,
			String studentNumber_register,String realName_register,String idCard_exist_register,
			String major_register,String channel_register,String idCard_error_register,
			String idCard_register,String password_different_register,
			String confirmassword_different_register,
			String password_register,String confirmassword_register,
			String purpose_apply,String detailPurpose_error_apply,
			String money_error_apply,String smsCode_apply,String detailPurpose_apply,
			String money_Emergency_apply,String productIndex_large_apply,String pieces_large_apply,
			String money_large_apply,String productIndex_emergency_apply,String pieces_emergency_apply,
			String money_apply,String productIndex_apply,String pieces_apply,
			String email_improve,String address_improve,String income_index_improve,
			String parentName_improve,String parentMobile_error_improve,String counselorName_improve,
			String counselorMobile_improve,String friend1Name_improve,String friend1Mobile_improve,
			String friend2Name_improve,String friend2Mobile_improve,String friend3Name_improve,
			String friend3Mobile_improve,String file_improve,String bankcardAccount_error_improve,
			String banksType_improve,String bankMobile_improve,String smsCode_improve,
			String bankcardAccount_exist_improve,String bankcardAccount_improve,
			String parentMobile_improve) {
		logger.info("开始进行异常借款流程测试...");
		// 注册流程--注册信息异常
		biz_register.registerInformationExcBus(userName_exist_register, userName_register, qq_exist_register,
				qq_register, mobile_exist_register, mobile_register, checkCode_error_register, checkCode_register,
				smsCode_register, password_error_register, confirmPassword_error_register);
		// 注册流程--信息验证
		biz_register.informationVerifyBus(province_register, institution_register, schoolArea_register, year_register,
				education_register, studentNumber_register, realName_register, idCard_exist_register, major_register);
		//注册流程-了解渠道
		biz_register.channelBus(channel_register);
		//注册流程-提交
		biz_register.submitBus();
		PageUtils.sleep(3000);
		//提示：身份证号码已经存在
		biz_register.idCardExistBus(idCard_error_register);
		PageUtils.sleep(3000);
		biz_register.submitBus();
		PageUtils.sleep(3000);
		//提示：身份证号码格式错误
		biz_register.idCardFormatErrorBus(idCard_register);
		PageUtils.sleep(3000);
		biz_register.submitBus();
		//提示：密码格式错误
		biz_register.passwordErrorBus(password_different_register, confirmassword_different_register);
		PageUtils.sleep(3000);
		biz_register.submitBus();
		//提示密码不一致
		biz_register.passwordDifferentBus(password_register, confirmassword_register);
		PageUtils.sleep(3000);
		biz_register.submitBus();
		PageUtils.sleep(5000);
		//注册成功信息验证
		biz_register.registerPromptBus();
		PageUtils.sleep(10000);
		
		//申请流程--详细理由少于10个字且借款金额不在100-50000之间
		int int_money_error_apply=Integer.parseInt(money_error_apply);
		biz_Apply.borrowsUseBus(purpose_apply, detailPurpose_error_apply, int_money_error_apply, smsCode_apply);
		//申请流程--提交
		biz_Apply.submitBus();
		//提交：详细借款用途不少于10个字
		biz_Apply.detailedUseErrorBus(detailPurpose_apply);
		//申请流程--提交
		biz_Apply.submitBus();
		//提示：借款需介于1千元~5万元之间(bug)
		int int_money_Emergency_apply=Integer.parseInt(money_Emergency_apply);
		biz_Apply.borrowsMoneyErrorBus(int_money_Emergency_apply);
		//申请流程--借款产品为大额借
		int int_productIndex_large_apply=Integer.parseInt(productIndex_large_apply);
		int int_pieces_large_apply=Integer.parseInt(pieces_large_apply);
		biz_Apply.selectBorrowsProductBus(int_money_Emergency_apply, int_productIndex_large_apply, int_pieces_large_apply);
		//申请流程--提交
		biz_Apply.submitBus();
		//提交：借款需介于1千元~5万元之间
		int int_money_large_apply=Integer.parseInt(money_large_apply);
		biz_Apply.borrowsProductMoneyDisagreeErrorBus(int_money_large_apply);
		//申请流程--借款产品为应急借
		int int_productIndex_emergency_apply=Integer.parseInt(productIndex_emergency_apply);
		int int_pieces_emergency_apply=Integer.parseInt(pieces_emergency_apply);
		biz_Apply.selectBorrowsProductBus(int_money_large_apply, int_productIndex_emergency_apply, int_pieces_emergency_apply);
		//申请流程--提交
		biz_Apply.submitBus();
		//提交：名校贷应急包金额要在1百元~1千元之间
		int int_money_apply=Integer.parseInt(money_apply);
		biz_Apply.borrowsProductMoneyDisagree2ErrorBus(int_money_apply);
		//申请流程--选择借款产品
		int int_productIndex_apply=Integer.parseInt(productIndex_apply);
		int int_pieces_apply=Integer.parseInt(pieces_apply);
		biz_Apply.selectBorrowsProductBus(int_money_apply, int_productIndex_apply, int_pieces_apply);
		//申请流程--提交
		biz_Apply.submitBus();
		biz_Apply.submitAfterVerify(int_productIndex_apply, int_pieces_apply, int_money_apply);
		PageUtils.sleep(5000);
		//申请流程--镑客码验证框存在通过
		biz_Apply.bankCodeVerifyBus();
		PageUtils.sleep(10000);
		
		//完善资料--借款信息检查
		biz_Improve.borrowsInformationVerifyBus(int_money_apply, int_pieces_apply);
		//完善资料--完善联系人信息：联系人手机号码格式错误
		biz_Improve.personalInformationBus(email_improve, address_improve, 
				income_index_improve, parentName_improve, parentMobile_error_improve,
				counselorName_improve, counselorMobile_improve, friend1Name_improve, 
				friend1Mobile_improve, friend2Name_improve, friend2Mobile_improve,
				friend3Name_improve, friend3Mobile_improve);
		//完善资料--上传身份证照片
		biz_Improve.uploadingBus(file_improve);
		PageUtils.sleep(3000);
		//完善资料--银行卡号码错误
		biz_Improve.bankCardBus(bankcardAccount_error_improve, banksType_improve, bankMobile_improve, smsCode_improve);
		PageUtils.sleep(3000);
		//提示：银行卡号码有误
		biz_Improve.bankCardErrorBus();
		PageUtils.sleep(3000);
		
		//提示：银行卡号码已存在
		biz_Improve.bankCardExistBus(bankcardAccount_exist_improve, banksType_improve, bankMobile_improve);
		PageUtils.sleep(3000);
		//完善资料--完善银行卡信息
		biz_Improve.bankCardBus(bankcardAccount_improve, banksType_improve, bankMobile_improve, smsCode_improve);
		PageUtils.sleep(3000);
		//完善资料--提交
		biz_Improve.submitBus();
		PageUtils.sleep(3000);
		//提示---联系人手机号码错误
		biz_Improve.linkedListErrorBus();
		PageUtils.sleep(3000);
		//完善资料--完善联系人信息
		biz_Improve.personalInformationBus(email_improve, address_improve, income_index_improve, 
				parentName_improve, parentMobile_improve, counselorName_improve, 
				counselorMobile_improve, friend1Name_improve, friend1Mobile_improve, 
				friend2Name_improve, friend2Mobile_improve, friend3Name_improve, friend3Mobile_improve);
		//完善资料--提交
		biz_Improve.submitBus();
		PageUtils.sleep(10000);	
	}
}
