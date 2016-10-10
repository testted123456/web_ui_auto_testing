package com.nonobank.apps.testcase.repayment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.nonobank.apps.business.account.Biz_Account;
import com.nonobank.apps.business.admin.Biz_Audit_VideoAuditView;
import com.nonobank.apps.business.admin.Biz_Home;
import com.nonobank.apps.business.admin.Biz_Login;
import com.nonobank.apps.business.common.Biz_Common;
import com.nonobank.apps.business.repayment.Biz_Repayment;
import com.nonobank.apps.business.student.Biz_Apply;
import com.nonobank.apps.business.student.Biz_Improve;
import com.nonobank.apps.business.student.Biz_Register;
import com.nonobank.apps.business.student.Biz_VideoSign;
import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.testcase.student.BorrowsTestCase;
import com.nonobank.apps.utils.page.PageUtils;
import com.nonobank.apps.utils.sql.SqlUtils;

public class RepaymentTestCase extends BaseCase{
	Biz_Register biz_register;
	Biz_Apply biz_Apply;
	Biz_Improve biz_Improve;
	Biz_VideoSign biz_VideoSign;
	Biz_Common biz_Common;
	Biz_Account biz_Account;
	Biz_Repayment biz_Repayment;
	Biz_Audit_VideoAuditView biz_Audit_VideoAuditView;
	Biz_Home biz_Home;
	Biz_Login biz_Login;
	public static Logger logger=LogManager.getLogger(RepaymentTestCase.class);
	@Test(dataProvider = "dataSource")
	public void test(String userName_register,String qq_register,String mobile_register,
			String checkCode_register,String password_register,String confirmPassword_register,
			String province_register,String institution_register,String schoolArea_register,
			String year_register,String education_register,String studentNumber_register,
			String realName_register,String idCard_register,String major_register,
			String channel_register,String smsCode_register,
			String purpose_apply,String detailPurpose_apply,String money_apply,
			String smsCode_apply,String productIndex_apply,String pieces_apply,
			String email_improve,String address_improve,String income_index_improve,
			String parentName_improve,String parentMobile_improve,String counselorName_improve,
			String counselorMobile_improve,String friend1Name_improve,
			String friend1Mobile_improve,String friend2Name_improve,String friend2Mobile_improve,
			String friend3Name_improve,String friend3Mobile_improve,String file_improve,
			String bankcardAccount_improve,String banksType_improve,String bankMobile_improve,
			String smsCode_improve,String username_admin,String password_admin
			){
		logger.info("开始进行借款流程测试........");
		int int_money_apply=Integer.parseInt(money_apply);
		int int_productIndex_apply=Integer.parseInt(productIndex_apply);
		int int_pieces_apply=Integer.parseInt(pieces_apply);
		
		// 注册流程--注册信息
		biz_register.registerInformationBus(userName_register, qq_register, mobile_register,
				checkCode_register, smsCode_register, password_register, confirmPassword_register);
		// 注册流程--信息验证
		biz_register.informationVerifyBus(province_register, institution_register, schoolArea_register,
				year_register, education_register, studentNumber_register, realName_register, 
				idCard_register, major_register);
		//注册流程-了解渠道
		biz_register.channelBus(channel_register);
		//注册流程-提交
		biz_register.submitBus();
		PageUtils.sleep(10000);
		//注册成功信息验证
		biz_register.registerPromptBus();
		PageUtils.sleep(10000);
		
		//申请流程--借款用途、金额
		biz_Apply.borrowsUseBus(purpose_apply, detailPurpose_apply, int_money_apply, smsCode_apply);
		PageUtils.sleep(2000);
		//申请流程--借款产品
		biz_Apply.selectBorrowsProductBus(int_money_apply, int_productIndex_apply, int_pieces_apply);
		PageUtils.sleep(2000);
		//申请流程--提交
		biz_Apply.submitBus();
		PageUtils.sleep(3000);
		biz_Apply.submitAfterVerify(int_productIndex_apply, int_pieces_apply, int_money_apply);
		PageUtils.sleep(3000);
		//申请流程--镑客码验证框存在通过
		biz_Apply.bankCodeVerifyBus();
		PageUtils.sleep(10000);
		
		//完善资料--借款信息检查
		biz_Improve.borrowsInformationVerifyBus(int_money_apply, int_pieces_apply);
		//完善资料--完善联系人信息
		biz_Improve.personalInformationBus(email_improve, address_improve, income_index_improve,
				parentName_improve, parentMobile_improve, counselorName_improve, counselorMobile_improve, 
				friend1Name_improve, friend1Mobile_improve, friend2Name_improve, friend2Mobile_improve, 
				friend3Name_improve, friend3Mobile_improve);
		//完善资料--上传身份证照片
		biz_Improve.uploadingBus(file_improve);
		//完善资料--完善银行卡信息
		biz_Improve.bankCardBus(bankcardAccount_improve, banksType_improve, bankMobile_improve, smsCode_improve);
		//完善资料--提交
		biz_Improve.submitBus();
		PageUtils.sleep(10000);
		// 照片检验不合格提示
		biz_Improve.photoNoQualifiedPromptBus(email_improve);
		PageUtils.sleep(10000);	
		//视频录制--借款信息检查
		biz_VideoSign.videoSignInformationCheckBus(realName_register, idCard_register, int_money_apply);
		PageUtils.sleep(3000);
		//视频录制--上传视频信息
		SqlUtils.recordVideo(mobile_register);
		PageUtils.sleep(5000);
		PageUtils.refreshPage();
		PageUtils.sleep(5000);
		//视频录制--视频录制完成检查
		biz_VideoSign.checkVideoSignSuccessBus();
		PageUtils.sleep(5000);
		
		//后台登录
		biz_Login.login(username_admin, password_admin);
		PageUtils.sleep(10000);
		//后台进行初审
		biz_Home.navigate_to_firstAudit(mobile_register, userName_register);
		PageUtils.sleep(200000);
		biz_Audit_VideoAuditView.first_audit_pass();
		PageUtils.sleep(60000);
		//初审结束后，需要切换到后台管理系统页面
		biz_Home.switch_adminHome();
		PageUtils.sleep(5000);
		//后台进行终审
		biz_Home.navigate_to_lastAudit(mobile_register, userName_register);
		PageUtils.sleep(10000);
		biz_Audit_VideoAuditView.last_audit_pass();
		PageUtils.sleep(60000);
		//初审结束后，需要切换到后台管理系统页面
		biz_Home.switch_adminHome();
		PageUtils.sleep(5000);
		//切换到后台管理系统主菜单页面
		biz_Home.switch_adminMenu();
		//执行名校贷非V3
		biz_Home.navigate_to_v3();
		PageUtils.sleep(10000);

		//点击用户名
		biz_Common.click_userNameBus();
		PageUtils.sleep(3000);	
		//还款流程
		biz_Repayment.repaymentBus();
		
	}
}
