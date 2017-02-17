package com.nonobank.apps.utils.entity;

import java.util.List;

public class FinanceAccount {
	private String faId;
	private String roleId;
	private String userId;
	private String ownerId;
	private String balance;
	private String locking;
	private String version;
	private String createTime;
	private String updateTime;


	public String getFaId() {
		return faId;
	}

	public void setFaId(String faId) {
		this.faId = faId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getLocking() {
		return locking;
	}

	public void setLocking(String locking) {
		this.locking = locking;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	public static void setFinanceAccountCondition(FinanceAccount financeAccount,StringBuffer sb) {
		if (financeAccount.getFaId() != null) {
			sb.append(" and id= '" + financeAccount.getFaId() + "'");
		}
		if (financeAccount.getRoleId() != null) {
			sb.append(" and role_id= '" + financeAccount.getRoleId() + "'");
		}
		if (financeAccount.getUserId() != null) {
			sb.append(" and user_id= '" + financeAccount.getUserId() + "'");
		}
		if (financeAccount.getOwnerId() != null) {
			sb.append(" and owner_id= '" + financeAccount.getOwnerId() + "'");
		}
		if (financeAccount.getBalance() != null) {
			sb.append(" and balance= '" + financeAccount.getBalance() + "'");
		}
		if (financeAccount.getLocking() != null) {
			sb.append(" and locking= '" + financeAccount.getLocking() + "'");
		}
		if (financeAccount.getVersion() != null) {
			sb.append(" and version= '" + financeAccount.getVersion() + "'");
		}
		if (financeAccount.getCreateTime() != null) {
			sb.append(" and create_time= '" + financeAccount.getCreateTime() + "'");
		}
		if (financeAccount.getUpdateTime() != null) {
			sb.append(" and update_time= '" + financeAccount.getUpdateTime() + "'");
		}

	}

	public static void setFinanceAccountConditions(List<String> list,StringBuffer sb) {
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
