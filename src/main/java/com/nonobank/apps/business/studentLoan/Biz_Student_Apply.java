package com.nonobank.apps.business.studentLoan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.page.student.Page_Student_Apply;

public class Biz_Student_Apply {

	public static Logger logger = LogManager.getLogger(Biz_Student_Apply.class);

	Page_Student_Apply page_Student_Apply = new Page_Student_Apply();

	/**
	 * 借款申请
	 * 
	 * @param purpose：借款目的
	 * @param detailPurpose：详细目的
	 * @param money：借款金额
	 * @param productIndex：1表示普通包（大于1000元），2表示应急包（100~1000元），3表示白领包，4表示专科包（
	 *            大于1000元）
	 * @param pieces：借款期数(普通包12、24、36期，应急包1、2、3期，白领包12、24、36期，专科包12、24、36期)
	 */
	public void loan(String purpose, String detailPurpose, String money, String productIndex, String pieces) {
		logger.info("输入借款信息...");

		int int_money = Double.valueOf(money).intValue();

		int int_productIndex = Double.valueOf(productIndex).intValue();

		int int_pieces = Double.valueOf(pieces).intValue();

		page_Student_Apply.selectPurposeByValue(purpose);

		page_Student_Apply.inputDetailPurpose(detailPurpose);

		page_Student_Apply.inputMoney(int_money);

		page_Student_Apply.inputValidate();

		switch (int_productIndex) {
		case 1:
			page_Student_Apply.selectPieceOfCommonLoan(int_pieces);
			break;
		case 2:
			page_Student_Apply.selectPieceOfEmergencyLoan(int_pieces);
			break;
		case 3:
			page_Student_Apply.selectPieceOfWhileCollarLoan(int_pieces);
			break;
		case 4:
			page_Student_Apply.selectPieceOfJuniorCollegeLoan(int_pieces);
			break;
		default:
			break;
		}

		page_Student_Apply.submit();

		page_Student_Apply.goNext();
	}

	// 判断借款是否成功
	public void isLoanInfoSucceess() {
		logger.info("判断借款是否成功...");
		page_Student_Apply.isGoNextSuccessful();
	}
}
