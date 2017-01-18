package com.nonobank.apps.utils.entity;

import java.util.List;

public class UserLoginInfo {
	private String loginId;
	private String userId;
	private String errorCount;
	private String currErrorCount;
	private String ulIp;
	private String loginErrorTime;
	private String loginCount;
	private String createTime;
	private String creater;
	private String updateTime;
	private String updater;
	private String version;
	public static StringBuffer sb = null;

	public static String getCondition() {
		return sb.toString();
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(String errorCount) {
		this.errorCount = errorCount;
	}

	public String getCurrErrorCount() {
		return currErrorCount;
	}

	public void setCurrErrorCount(String currErrorCount) {
		this.currErrorCount = currErrorCount;
	}

	public String getUlIp() {
		return ulIp;
	}

	public void setUlIp(String ulIp) {
		this.ulIp = ulIp;
	}

	public String getLoginErrorTime() {
		return loginErrorTime;
	}

	public void setLoginErrorTime(String loginErrorTime) {
		this.loginErrorTime = loginErrorTime;
	}

	public String getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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

	public static void setUserLoginInfoCondition(UserLoginInfo userLoginInfo) {
		sb = new StringBuffer();
		sb.append(" where 1=1");
		if (userLoginInfo.getLoginId() != null) {
			sb.append(" and id= '" + userLoginInfo.getLoginId() + "'");
		}
		if (userLoginInfo.getUserId() != null) {
			sb.append(" and user_id= '" + userLoginInfo.getUserId() + "'");
		}
		if (userLoginInfo.getErrorCount() != null) {
			sb.append(" and error_count= '" + userLoginInfo.getErrorCount() + "'");
		}
		if (userLoginInfo.getCurrErrorCount() != null) {
			sb.append(" and curr_error_count= '" + userLoginInfo.getCurrErrorCount() + "'");
		}
		if (userLoginInfo.getUlIp() != null) {
			sb.append(" and ul_ip= '" + userLoginInfo.getUlIp() + "'");
		}
		if (userLoginInfo.getLoginErrorTime() != null) {
			sb.append(" and login_error_time= '" + userLoginInfo.getLoginErrorTime() + "'");
		}
		if (userLoginInfo.getLoginCount() != null) {
			sb.append(" and login_count= '" + userLoginInfo.getLoginCount() + "'");
		}
		if (userLoginInfo.getCreateTime() != null) {
			sb.append(" and create_time= '" + userLoginInfo.getCreateTime() + "'");
		}
		if (userLoginInfo.getCreater() != null) {
			sb.append(" and creater= '" + userLoginInfo.getCreater() + "'");
		}
		if (userLoginInfo.getUpdateTime() != null) {
			sb.append(" and update_time= '" + userLoginInfo.getUpdateTime() + "'");
		}
		if (userLoginInfo.getUpdater() != null) {
			sb.append(" and updater= '" + userLoginInfo.getUpdater() + "'");
		}
		if (userLoginInfo.getVersion() != null) {
			sb.append(" and version='" + userLoginInfo.getVersion() + "'");
		}
	}

	public static void setLimit(int limit) {
		sb.append(" limit ");
		sb.append(limit);
	}

	public static void setUserLoginInfoConditions(List<String> list) {
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
