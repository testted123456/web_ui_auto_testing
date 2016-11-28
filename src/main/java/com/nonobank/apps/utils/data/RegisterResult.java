package com.nonobank.apps.utils.data;

public enum RegisterResult {
	SUCCESS(1, null, "正例-检查注册成功", ConstantUtils.CORRECT_CHECK_CODE, ConstantUtils.CORRECT_VALIDATION), MOBLIE_ERROR(2,
			"请输入有效的手机号码，以便找回密码", "反例-检查手机号输入错误", null,
			null), MOBLIE_EXIST(3, "该手机号码已存在，登录或者查看帮助", "反例-检查手机号已存在", null, null), USERNAME_ERROR(4, "只能使用字母、数字或下划线",
					"反例-检查用户名输入错误", null, null), USERNAME_LENGTH(5, "6-16位字符，可以是字母、数字、下划线的组合", "反例-检查用户名输入长度错误", null,
							null), USERNAME_EXIST(6, "该用户名已存在，登录", "反例-检查用户名已存在", null, null), PASSWORD_ERROR(7,
									"应至少包含字母、数字、下划线中的两种", "反例-检查密码输入错误", null, null), PASSWORD2_ERROR(8, "两次输入的密码不一致",
											"反例-检查两次密码不一致", null, null), CHECK_CODE_NULL(9, "请输入安全码！", "反例-檢查安全碼为空",
													ConstantUtils.NULL_CHECK_CODE, null);
	private int code;
	private String message;
	private String comment;
	private String checkCode;
	private String validation;

	private RegisterResult(int code, String message, String comment, String checkCode, String validation) {
		this.code = code;
		this.message = message;
		this.comment = comment;
		this.checkCode = checkCode;
		this.validation = validation;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

}
