package com.nonobank.apps.business.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.nonobank.apps.page.student.Page_Improve;
import com.nonobank.apps.page.student.Page_VideoSign;

public class Biz_VideoSign {
	public static Logger logger = LogManager.getLogger(Biz_Improve.class);

	Page_VideoSign page_VideoSign=new Page_VideoSign();
	
	public void videoSignInformationCheckBus(String realName_register,String idCard_register,
			int int_money_apply){
		logger.info("--------------开始：视频签约信息检查----------------");
		String realName=page_VideoSign.getText_name();
		String idCard_origin=page_VideoSign.getText_idCard();
		String idCard=idCard_origin.replaceAll(" ", "");
		String borrowsMoney=page_VideoSign.getText_borrowsMoney();
		float float_borrowsMoney=Float.parseFloat(borrowsMoney);
		int int_borrowsMoney=(int) float_borrowsMoney;
		String consultingFee=page_VideoSign.getText_consultingFee();
		float float_consultingFee=Float.parseFloat(consultingFee);
		int int_consultingFee=(int) float_consultingFee;
		Assert.assertEquals(realName_register, realName);
		Assert.assertEquals(idCard_register, idCard);
//		Assert.assertEquals(int_money_apply, int_borrowsMoney);
//		int int_consultingFee_value=(int) (int_money_apply*0.2);
//		Assert.assertEquals(int_consultingFee_value, int_consultingFee);
		logger.info("--------------结束：视频签约信息检查----------------");
	}
	public void checkVideoSignSuccessBus(){
		logger.info("---------------开始：检查视频签约是否完成------------------");
		page_VideoSign.exist_getFirstAuditRight();
		logger.info("---------------结束：检查视频签约是否完成------------------");	
	}
	
	
}
