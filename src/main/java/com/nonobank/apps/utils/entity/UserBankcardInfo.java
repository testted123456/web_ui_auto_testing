package com.nonobank.apps.utils.entity;

import java.util.List;

public class UserBankcardInfo {
	private String bankcardId;
	private String userId;
	private String bankCardNo;
	private String bankCode;
	private String provinceCode;
	private String cityCode;
	private String openBankCode;
	private String lastUsedTime;
	private String inMoney;
	private String outMoney;
	private String isDefault;

	private String isDeleted;
	private String createTime;
	private String updateTime;
	private String creater;
	private String updater;
	private String version;


	public String getBankcardId() {
		return bankcardId;
	}

	public void setBankcardId(String bankcardId) {
		this.bankcardId = bankcardId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getOpenBankCode() {
		return openBankCode;
	}

	public void setOpenBankCode(String openBankCode) {
		this.openBankCode = openBankCode;
	}

	public String getLastUsedTime() {
		return lastUsedTime;
	}

	public void setLastUsedTime(String lastUsedTime) {
		this.lastUsedTime = lastUsedTime;
	}

	public String getInMoney() {
		return inMoney;
	}

	public void setInMoney(String inMoney) {
		this.inMoney = inMoney;
	}

	public String getOutMoney() {
		return outMoney;
	}

	public void setOutMoney(String outMoney) {
		this.outMoney = outMoney;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public static void setUserInfoCondition(UserBankcardInfo userBankcardInfo,StringBuffer sb) {
		if (userBankcardInfo.getBankcardId() != null) {
			sb.append(" and id= '" + userBankcardInfo.getBankcardId() + "'");
		}
		if (userBankcardInfo.getUserId() != null) {
			sb.append(" and user_id= '" + userBankcardInfo.getUserId() + "'");
		}
		if (userBankcardInfo.getBankCardNo() != null) {
			sb.append(" and bank_card_no= '" + userBankcardInfo.getBankCardNo() + "'");
		}
		if (userBankcardInfo.getBankCode() != null) {
			sb.append(" and bank_code= '" + userBankcardInfo.getBankCode() + "'");
		}
		if (userBankcardInfo.getProvinceCode() != null) {
			sb.append(" and province_code= '" + userBankcardInfo.getProvinceCode() + "'");
		}
		if (userBankcardInfo.getCityCode() != null) {
			sb.append(" and city_code= '" + userBankcardInfo.getCityCode() + "'");
		}
		if (userBankcardInfo.getOpenBankCode() != null) {
			sb.append(" and open_bank_code= '" + userBankcardInfo.getOpenBankCode() + "'");
		}
		if (userBankcardInfo.getLastUsedTime() != null) {
			sb.append(" and last_used_time= '" + userBankcardInfo.getLastUsedTime() + "'");
		}
		if (userBankcardInfo.getInMoney() != null) {
			sb.append(" and in_money= '" + userBankcardInfo.getInMoney() + "'");
		}
		if (userBankcardInfo.getOutMoney() != null) {
			sb.append(" and out_money= '" + userBankcardInfo.getOutMoney() + "'");
		}
		if (userBankcardInfo.getIsDefault() != null) {
			sb.append(" and is_default= '" + userBankcardInfo.getIsDefault() + "'");
		}
		if (userBankcardInfo.getIsDefault() != null) {
			sb.append(" and is_deleted= '" + userBankcardInfo.getIsDefault() + "'");
		}
		if (userBankcardInfo.getCreateTime() != null) {
			sb.append(" and create_time= '" + userBankcardInfo.getCreateTime() + "'");
		}
		if (userBankcardInfo.getUpdateTime() != null) {
			sb.append(" and update_time= '" + userBankcardInfo.getUpdateTime() + "'");
		}
		if (userBankcardInfo.getCreater() != null) {
			sb.append(" and creater= '" + userBankcardInfo.getCreater() + "'");
		}
		if (userBankcardInfo.getUpdater() != null) {
			sb.append(" and updater= '" + userBankcardInfo.getUpdater() + "'");
		}
		if (userBankcardInfo.getVersion() != null) {
			sb.append(" and version= '" + userBankcardInfo.getVersion() + "'");
		}
	}

	public static void setLimit(int limit,StringBuffer sb) {
		sb.append(" limit ");
		sb.append(limit);
	}

	public static void setUserLoginInfoConditions(List<String> list,StringBuffer sb) {
		StringBuffer newsb = new StringBuffer();
		for (String s : list) {
			newsb.append(s);
			newsb.append(",");
		}
		String userIds = newsb.substring(0, newsb.toString().length() - 1);
		if (userIds != null) {
			sb.append(" and user_id in");
			sb.append(" (");
			sb.append(userIds);
			sb.append(" )");
		}
	}
}
