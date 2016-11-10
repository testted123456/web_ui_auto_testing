package com.nonobank.apps.utils.data;

import com.nonobank.apps.utils.db.DBUtils;

public class DebtUtils {
	public static String getFpId() {
		String search_fpId = DBUtils.getOneLineValues("nono",
				"SELECT DISTINCT concat(fp.id,'：',fp.title) FROM borrows_accept ba LEFT JOIN vip_account va on va.id = ba.va_id LEFT JOIN finance_plan fp on fp.id = va.fp_id WHERE  va.is_cash =0  AND ba.is_pay = 0 and ba.price_principal>0 AND ba.va_id>0 and fp.title is not NULL AND fp.id not in (6,86,140) LIMIT 10");
		return search_fpId;
	}

	public static String getFailTargetFpid() {
		String targetFpid = DBUtils.getOneLineValues("nono",
				"SELECT DISTINCT concat(fp.id,'：',fp.title) title FROM  vip_account va  LEFT JOIN  finance_plan fp on fp.id = va.fp_id WHERE  va.is_cash =1 and fp.title is not NULL ORDER BY  fp.id LIMIT  1");
		return targetFpid;
	}
}
