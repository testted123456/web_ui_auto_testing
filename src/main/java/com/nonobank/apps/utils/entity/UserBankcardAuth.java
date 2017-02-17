package com.nonobank.apps.utils.entity;

public class UserBankcardAuth {
	private String ubaId;
	private String userId;
	private String bankCardId;
	private String authType;
	private String authStatus;
	private String authTime;
	private String shortNo;
	private String returnCode;
	private String returnDetail;
	private String createTime;
	private String updateTime;
	private String creater;
	private String updater;
	private String version;
	private String idCardNo;
	private String mobile;
	private String name;

	public String getUbaId() {
		return ubaId;
	}

	public void setUbaId(String ubaId) {
		this.ubaId = ubaId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBankCardId() {
		return bankCardId;
	}

	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getAuthTime() {
		return authTime;
	}

	public void setAuthTime(String authTime) {
		this.authTime = authTime;
	}

	public String getShortNo() {
		return shortNo;
	}

	public void setShortNo(String shortNo) {
		this.shortNo = shortNo;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnDetail() {
		return returnDetail;
	}

	public void setReturnDetail(String returnDetail) {
		this.returnDetail = returnDetail;
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

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void setUserBankcardAuthCondition(UserBankcardAuth userBankcardAuth, StringBuffer sb) {
		if (userBankcardAuth.getUbaId() != null) {
			sb.append(" and id= '" + userBankcardAuth.getUbaId() + "'");
		}
		if (userBankcardAuth.getUserId() != null) {
			sb.append(" and user_id= '" + userBankcardAuth.getUserId() + "'");
		}
		if (userBankcardAuth.getBankCardId() != null) {
			sb.append(" and bank_card_id= '" + userBankcardAuth.getBankCardId() + "'");
		}
		if (userBankcardAuth.getAuthType() != null) {
			sb.append(" and auth_type= '" + userBankcardAuth.getAuthType() + "'");
		}
		if (userBankcardAuth.getAuthStatus() != null) {
			sb.append(" and auth_status= '" + userBankcardAuth.getAuthStatus() + "'");
		}
		if (userBankcardAuth.getAuthTime() != null) {
			sb.append(" and auth_time= '" + userBankcardAuth.getAuthTime() + "'");
		}
		if (userBankcardAuth.getShortNo() != null) {
			sb.append(" and short_no= '" + userBankcardAuth.getShortNo() + "'");
		}
		if (userBankcardAuth.getReturnCode() != null) {
			sb.append(" and return_code= '" + userBankcardAuth.getReturnCode() + "'");
		}
		if (userBankcardAuth.getReturnDetail() != null) {
			sb.append(" and return_detail= '" + userBankcardAuth.getReturnDetail() + "'");
		}
		if (userBankcardAuth.getCreateTime() != null) {
			sb.append(" and create_time= '" + userBankcardAuth.getCreateTime() + "'");
		}

		if (userBankcardAuth.getUpdateTime() != null) {
			sb.append(" and update_time= '" + userBankcardAuth.getUpdateTime() + "'");
		}
		if (userBankcardAuth.getCreater() != null) {
			sb.append(" and creater= '" + userBankcardAuth.getCreater() + "'");
		}
		if (userBankcardAuth.getUpdater() != null) {
			sb.append(" and updater= '" + userBankcardAuth.getUpdater() + "'");
		}
		if (userBankcardAuth.getVersion() != null) {
			sb.append(" and version= '" + userBankcardAuth.getVersion() + "'");
		}
		if (userBankcardAuth.getIdCardNo() != null) {
			sb.append(" and id_card_no= '" + userBankcardAuth.getIdCardNo() + "'");
		}
		if (userBankcardAuth.getMobile() != null) {
			sb.append(" and mobile= '" + userBankcardAuth.getMobile() + "'");
		}
		if (userBankcardAuth.getName() != null) {
			sb.append(" and name= '" + userBankcardAuth.getName() + "'");
		}
	}
}
