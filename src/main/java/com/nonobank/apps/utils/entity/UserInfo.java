package com.nonobank.apps.utils.entity;

import java.util.List;

import com.nonobank.apps.utils.data.StringUtils;

public class UserInfo {

	private String userId;
	private String userKey;
	private String userName;
	private String password;
	private String payPassword;
	private String mobileNum;
	private String idNum;
	private String userType;
	private String realName;
	private String gender;
	private String email;
	private String isMobile;
	private String isCard;
	private String cardNeedcheck;
	private String isEducation;
	private String isStudent;
	private String isEmail;
	private String isPhoto;
	private String isDunning;
	private String isCheat;
	private String status;
	private String isSpecial;
	private String customerServicer;
	private String financeAdvisor;
	private String financeAdvisorManager;
	private String recommender;
	private String pcId;
	private String isSync;
	private String isBbsSync;
	private String nonoIdChecked;
	private String registerTime;
	private String createTime;
	private String updateTime;
	private String creater;
	private String updater;
	private String version;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsMobile() {
		return isMobile;
	}

	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}

	public String getIsCard() {
		return isCard;
	}

	public void setIsCard(String isCard) {
		this.isCard = isCard;
	}

	public String getCardNeedcheck() {
		return cardNeedcheck;
	}

	public void setCardNeedcheck(String cardNeedcheck) {
		this.cardNeedcheck = cardNeedcheck;
	}

	public String getIsEducation() {
		return isEducation;
	}

	public void setIsEducation(String isEducation) {
		this.isEducation = isEducation;
	}

	public String getIsStudent() {
		return isStudent;
	}

	public void setIsStudent(String isStudent) {
		this.isStudent = isStudent;
	}

	public String getIsEmail() {
		return isEmail;
	}

	public void setIsEmail(String isEmail) {
		this.isEmail = isEmail;
	}

	public String getIsPhoto() {
		return isPhoto;
	}

	public void setIsPhoto(String isPhoto) {
		this.isPhoto = isPhoto;
	}

	public String getIsDunning() {
		return isDunning;
	}

	public void setIsDunning(String isDunning) {
		this.isDunning = isDunning;
	}

	public String getIsCheat() {
		return isCheat;
	}

	public void setIsCheat(String isCheat) {
		this.isCheat = isCheat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(String isSpecial) {
		this.isSpecial = isSpecial;
	}

	public String getCustomerServicer() {
		return customerServicer;
	}

	public void setCustomerServicer(String customerServicer) {
		this.customerServicer = customerServicer;
	}

	public String getFinanceAdvisor() {
		return financeAdvisor;
	}

	public void setFinanceAdvisor(String financeAdvisor) {
		this.financeAdvisor = financeAdvisor;
	}

	public String getFinanceAdvisorManager() {
		return financeAdvisorManager;
	}

	public void setFinanceAdvisorManager(String financeAdvisorManager) {
		this.financeAdvisorManager = financeAdvisorManager;
	}

	public String getRecommender() {
		return recommender;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public String getIsSync() {
		return isSync;
	}

	public void setIsSync(String isSync) {
		this.isSync = isSync;
	}

	public String getIsBbsSync() {
		return isBbsSync;
	}

	public void setIsBbsSync(String isBbsSync) {
		this.isBbsSync = isBbsSync;
	}

	public String getNonoIdChecked() {
		return nonoIdChecked;
	}

	public void setNonoIdChecked(String nonoIdChecked) {
		this.nonoIdChecked = nonoIdChecked;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
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

	public static void setUserInfoCondition(UserInfo userInfo, StringBuffer sb) {
		// sb.append(" and user_name REGEXP '^[0-9a-zA-Z_]{6,16}$'");
		// sb.append(" and mobile_num REGEXP
		// '^((13[0-9])|(15[^4,\\D])|(18[0-9]))[0-9]{8}$'");
		// sb.append(" and length(id_num) > 0");
		if (userInfo.getUserId() != null) {
			sb.append(" and ui.id= '" + userInfo.getUserId() + "'");
		}
		if (userInfo.getUserKey() != null) {
			sb.append(" and user_key= '" + userInfo.getUserKey() + "'");
		}
		if (userInfo.getUserName() != null) {
			sb.append(" and user_name= '" + userInfo.getUserName() + "'");
		}
		if (userInfo.getPassword() != null) {
			String md5Password = StringUtils.md5(userInfo.getPassword());
			sb.append(" and password= '" + md5Password + "'");
		}
		if (userInfo.getPayPassword() != null) {
			String md5Password = StringUtils.md5(userInfo.getPayPassword());
			sb.append(" and pay_password= '" + md5Password + "'");
		}
		if (userInfo.getMobileNum() != null) {
			sb.append(" and mobile_num= '" + userInfo.getMobileNum() + "'");
		}
		if (userInfo.getIdNum() != null) {
			sb.append(" and id_num= '" + userInfo.getIdNum() + "'");
		}
		if (userInfo.getUserType() != null) {
			sb.append(" and user_type= '" + userInfo.getUserType() + "'");
		}
		if (userInfo.getRealName() != null) {
			sb.append(" and real_name= '" + userInfo.getRealName() + "'");
		}
		if (userInfo.getGender() != null) {
			sb.append(" and gender= '" + userInfo.getGender() + "'");
		}
		if (userInfo.getEmail() != null) {
			sb.append(" and email= '" + userInfo.getEmail() + "'");
		}
		if (userInfo.getIsMobile() != null) {
			sb.append(" and is_mobile= '" + userInfo.getIsMobile() + "'");
		}
		if (userInfo.getIsCard() != null) {
			sb.append(" and is_card= '" + userInfo.getIsCard() + "'");
		}
		if (userInfo.getCardNeedcheck() != null) {
			sb.append(" and card_needcheck= '" + userInfo.getCardNeedcheck() + "'");
		}
		if (userInfo.getIsEducation() != null) {
			sb.append(" and is_education= '" + userInfo.getIsEducation() + "'");
		}
		if (userInfo.getIsStudent() != null) {
			sb.append(" and is_student= '" + userInfo.getIsStudent() + "'");
		}
		if (userInfo.getIsEmail() != null) {
			sb.append(" and is_email= '" + userInfo.getIsEmail() + "'");
		}
		if (userInfo.getIsPhoto() != null) {
			sb.append(" and is_photo= '" + userInfo.getIsPhoto() + "'");
		}
		if (userInfo.getIsDunning() != null) {
			sb.append(" and is_dunning= '" + userInfo.getIsDunning() + "'");
		}
		if (userInfo.getIsCheat() != null) {
			sb.append(" and is_cheat= '" + userInfo.getIsCheat() + "'");
		}
		if (userInfo.getStatus() != null) {
			sb.append(" and status= '" + userInfo.getStatus() + "'");
		}
		if (userInfo.getIsSpecial() != null) {
			sb.append(" and is_special= '" + userInfo.getIsSpecial() + "'");
		}
		if (userInfo.getCustomerServicer() != null) {
			sb.append(" and customer_servicer= '" + userInfo.getCustomerServicer() + "'");
		}
		if (userInfo.getFinanceAdvisor() != null) {
			sb.append(" and finance_advisor= '" + userInfo.getFinanceAdvisor() + "'");
		}
		if (userInfo.getFinanceAdvisorManager() != null) {
			sb.append(" and finance_advisor_manager= '" + userInfo.getFinanceAdvisorManager() + "'");
		}
		if (userInfo.getRecommender() != null) {
			sb.append(" and recommender= '" + userInfo.getRecommender() + "'");
		}
		if (userInfo.getPcId() != null) {
			sb.append(" and pc_id= '" + userInfo.getPcId() + "'");
		}
		if (userInfo.getIsSync() != null) {
			sb.append(" and is_sync= '" + userInfo.getIsSync() + "'");
		}
		if (userInfo.getIsBbsSync() != null) {
			sb.append(" and is_bbs_sync= '" + userInfo.getIsBbsSync() + "'");
		}
		if (userInfo.getNonoIdChecked() != null) {
			sb.append(" and nono_id_checked= '" + userInfo.getNonoIdChecked() + "'");
		}
		if (userInfo.getRegisterTime() != null) {
			sb.append(" and register_time= '" + userInfo.getRegisterTime() + "'");
		}
		if (userInfo.getCreateTime() != null) {
			sb.append(" and create_time= '" + userInfo.getCreateTime() + "'");
		}
		if (userInfo.getUpdateTime() != null) {
			sb.append(" and update_time= '" + userInfo.getUpdateTime() + "'");
		}
		if (userInfo.getCreater() != null) {
			sb.append(" and creater= '" + userInfo.getCreater() + "'");
		}
		if (userInfo.getUpdater() != null) {
			sb.append(" and updater= '" + userInfo.getUpdater() + "'");
		}
		if (userInfo.getVersion() != null) {
			sb.append(" and version= '" + userInfo.getVersion() + "'");
		}
	}

	public static void setUserInfoConditions(List<String> list, StringBuffer sb) {
		StringBuffer newsb = new StringBuffer();
		for (String s : list) {
			newsb.append(s);
			newsb.append(",");
		}
		if (newsb.length() > 0) {
			String userIds = newsb.substring(0, newsb.toString().length() - 1);
			sb.append(" and ui.id in");
			sb.append(" (");
			sb.append(userIds);
			sb.append(" )");
		}
	}

	public static void setUserInfoGroupBy(String fileName, String operatorType, String operatorValue,StringBuffer sb) {
		if (fileName != null && operatorType != null && operatorValue != null) {
			sb.append(" group by  ");
			sb.append(fileName);
			sb.append(" having ");
			sb.append(" count(");
			sb.append(fileName);
			sb.append(") ");
			sb.append(operatorType);
			sb.append(operatorValue);
			sb.append(" order by id asc");
		}
	}

	public static void setLimit(int minLimit, int maxLimit,StringBuffer sb) {
			sb.append(" limit ");
			sb.append(minLimit);
			sb.append(",");
			sb.append(maxLimit);
	}
}
