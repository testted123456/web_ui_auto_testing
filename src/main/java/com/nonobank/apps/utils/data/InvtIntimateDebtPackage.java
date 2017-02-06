package com.nonobank.apps.utils.data;

import java.util.List;

public class InvtIntimateDebtPackage {
	private String iidpId;
	private String packageDate;
	private String fpId;
	private String price;
	private String transferNum;
	private String onePrice;
	private String boId;
	private String vaId;
	private String remainPrice;
	private String remainNum;
	private String lockPrice;
	private String lockNum;
	private String usePrice;
	private String useNum;
	private String isAllMatched;
	private String type;
	private String packageTime;
	private String finishTime;
	private String isEnable;
	private String createTime;
	private String updateTime;
	public static StringBuffer sb = null;

	public static String getCondition() {
		return sb.toString();
	}

	public String getIidpId() {
		return iidpId;
	}

	public void setIidpId(String iidpId) {
		this.iidpId = iidpId;
	}

	public String getPackageDate() {
		return packageDate;
	}

	public void setPackageDate(String packageDate) {
		this.packageDate = packageDate;
	}

	public String getFpId() {
		return fpId;
	}

	public void setFpId(String fpId) {
		this.fpId = fpId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTransferNum() {
		return transferNum;
	}

	public void setTransferNum(String transferNum) {
		this.transferNum = transferNum;
	}

	public String getOnePrice() {
		return onePrice;
	}

	public void setOnePrice(String onePrice) {
		this.onePrice = onePrice;
	}

	public String getBoId() {
		return boId;
	}

	public void setBoId(String boId) {
		this.boId = boId;
	}

	public String getVaId() {
		return vaId;
	}

	public void setVaId(String vaId) {
		this.vaId = vaId;
	}

	public String getRemainPrice() {
		return remainPrice;
	}

	public void setRemainPrice(String remainPrice) {
		this.remainPrice = remainPrice;
	}

	public String getRemainNum() {
		return remainNum;
	}

	public void setRemainNum(String remainNum) {
		this.remainNum = remainNum;
	}

	public String getLockPrice() {
		return lockPrice;
	}

	public void setLockPrice(String lockPrice) {
		this.lockPrice = lockPrice;
	}

	public String getLockNum() {
		return lockNum;
	}

	public void setLockNum(String lockNum) {
		this.lockNum = lockNum;
	}

	public String getUsePrice() {
		return usePrice;
	}

	public void setUsePrice(String usePrice) {
		this.usePrice = usePrice;
	}

	public String getUseNum() {
		return useNum;
	}

	public void setUseNum(String useNum) {
		this.useNum = useNum;
	}

	public String getIsAllMatched() {
		return isAllMatched;
	}

	public void setIsAllMatched(String isAllMatched) {
		this.isAllMatched = isAllMatched;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPackageTime() {
		return packageTime;
	}

	public void setPackageTime(String packageTime) {
		this.packageTime = packageTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public static void setInvtIntimateDebtPackageCondition(InvtIntimateDebtPackage invtIntimateDebtPackage) {
		sb = new StringBuffer();
		sb.append(" where 1=1");
	}

	public static void setInvtIntimateDebtPackageConditions(List<String> list) {
		StringBuffer newsb = new StringBuffer();
		for (String s : list) {
			newsb.append(s);
			newsb.append(",");
		}
		if (newsb.length() > 0) {
			String userIds = newsb.substring(0, newsb.toString().length() - 1);
			sb.append(" and fp_id in");
			sb.append(" (");
			sb.append(userIds);
			sb.append(" )");
		}
	}
}
